package com.example.kbk.ui.dashboard.dialogs

import android.app.AlertDialog
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.R
import com.example.kbk.model.AllGroup
import com.example.kbk.model.AllGroups
import com.example.kbk.model.Teacher
import com.example.kbk.model.Teachers
import com.example.kbk.network.Api
import com.example.kbk.network.ServiceBuilder
import kotlinx.android.synthetic.main.dialog_search_custom.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.Provider


class SearchDialogFragment: DialogFragment() {

    private lateinit var radio_t:RadioButton
    private lateinit var radio_g:RadioButton



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val settings: SharedPreferences =
            requireActivity().getSharedPreferences("Account", Context.MODE_PRIVATE)
        val builder = AlertDialog.Builder(
            activity
        )
        val inflater:LayoutInflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(com.example.kbk.R.layout.dialog_search_custom, null)
        builder.setView(view)

        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceBuilder.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: Api = retrofit.create(Api::class.java)
        val rec: RecyclerView = view.findViewById(R.id.searchd_recycler_view)
        rec.setHasFixedSize(true)
        rec.setLayoutManager(LinearLayoutManager(requireActivity()))


        val stateClickListener: GroupSDialogAdapter.OnClickListener = object : GroupSDialogAdapter.OnClickListener {


            override fun onClick(allgr: AllGroup, position: Int) {
                var id_searchgroup: SharedPreferences.Editor=settings.edit()
                id_searchgroup.putInt("id_searchgroup",allgr.id_group)
                id_searchgroup.apply()
                Toast.makeText(
                    context!!.getApplicationContext(),
                    "Был выбран пункт " + settings.getInt("id_searchgroup", 0),
                    Toast.LENGTH_SHORT
                ).show()

                dismiss()
            }

        }

        val teacherstateClickListener: TeacherSDialogAdapter.OnClickListener = object : TeacherSDialogAdapter.OnClickListener {


            override fun onClick(allth: Teacher, position: Int) {
                var id_searchteacher: SharedPreferences.Editor=settings.edit()
                id_searchteacher.putInt("id_searchteacher",allth.idu)
                id_searchteacher.apply()
                Toast.makeText(
                    context!!.getApplicationContext(),
                    "Был выбран пункт " + settings.getInt("id_searchteacher", 0),
                    Toast.LENGTH_SHORT
                ).show()

                dismiss()
            }

        }


        radio_t=view.findViewById(R.id.radio_steachers)
        radio_g=view.findViewById(R.id.radio_sgroups)
        radio_t.setOnClickListener{
            val call: Call<Teachers> = service.allteachers()

            call.enqueue(object : Callback<Teachers> {
                override fun onResponse(call: Call<Teachers>, response: Response<Teachers>) {
                    var list: ArrayList<Teacher> = response.body()!!.teachers
                    rec.adapter = TeacherSDialogAdapter(list,requireContext(),teacherstateClickListener)
                }

                override fun onFailure(call: Call<Teachers>, t: Throwable?) {
                    Log.d("adapter", t.toString())
                }
            })
        }
        radio_g.setOnClickListener{
            val call: Call<AllGroups> = service.allgroups()

            call.enqueue(object : Callback<AllGroups> {
                override fun onResponse(call: Call<AllGroups>, response: Response<AllGroups>) {
                    var list: ArrayList<AllGroup> = response.body()!!.groups
                    rec.adapter = GroupSDialogAdapter(list,requireContext(),stateClickListener)
                }

                override fun onFailure(call: Call<AllGroups>, t: Throwable?) {
                    Log.d("adapter", t.toString())
                }
            })
        }

        return builder.create()
    }
    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height =  LinearLayout.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }
}