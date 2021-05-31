package com.example.kbk.ui.dashboard.dialogs

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import com.example.kbk.EventsAdapter
import com.example.kbk.model.KBKEvent
import com.example.kbk.model.KBKEvents
import com.example.kbk.model.Teacher
import com.example.kbk.model.Teachers
import com.example.kbk.network.Api
import com.example.kbk.network.ServiceBuilder
import com.example.kbk.ui.menu.teachers.TeachersAdapter
import kotlinx.android.synthetic.main.dialog_search_custom.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchDialogFragment: DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
/*        return activity?.let {
            val searchDialogFragment = SearchDialogFragment()
            val manager = requireFragmentManager()
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Поиск по")
                .setCancelable(true)
                .setPositiveButton("Группе") { dialog, id ->
                  //  GroupSDialog.show(manager, "groupSDialog")
                }
                .setNegativeButton("Преподавателю",
                    DialogInterface.OnClickListener { dialog, id ->
                //        TeacherSDialog.show(manager, "teacherSDialog")
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")*/

        val builder = AlertDialog.Builder(
            activity
        )
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(com.example.kbk.R.layout.dialog_search_custom, null)
        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceBuilder.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: Api = retrofit.create(Api::class.java)

        val call: Call<Teachers> = service.allteachers()

        call.enqueue(object : Callback<Teachers> {
            override fun onResponse(call: Call<Teachers>, response: Response<Teachers>)
            {
                var list:ArrayList<Teacher> = response.body()!!.teachers
                rec.adapter = TeachersAdapter(list)
/*                adapter = EventsAdapter(response.body()!!.events)
                rec.setAdapter(adapter)*/
            }

            override fun onFailure(call: Call<Teachers>, t: Throwable?) {
                Log.d("adapter",t.toString())
            }
        })

        builder.setView(view)
        builder.setTitle("Поиск по")
        val tspinner:Spinner=view.findViewById(com.example.kbk.R.id.spinnerTeacher)
        val gspinner:Spinner=view.findViewById(com.example.kbk.R.id.spinnerGroup)
        val tadapter:ArrayAdapter<String>()
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            tspinner.adapter = adapter
        }


        // Остальной код
        return builder.create()
    }
}