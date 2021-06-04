package com.example.kbk.ui.menu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kbk.R
import com.example.kbk.activities.Bnv
import com.example.kbk.activities.MainActivity
import com.example.kbk.ui.menu.gradebook.GradeBActivity
import com.example.kbk.ui.menu.teachers.AllTeachers
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

//    private lateinit var contextThis: Context
    private lateinit var menuViewModel: MenuViewModel
    private lateinit var ex:Button
    private lateinit var gradeb:Button
    private lateinit var allteach:Button
    private lateinit var feedb:Button
    private lateinit var docs:Button
    private lateinit var uname:TextView
    private lateinit var ugroup:TextView
    private lateinit var stgroup: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        menuViewModel =
            ViewModelProvider(this).get(MenuViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_menu, container, false)
        val settings: SharedPreferences =
            requireActivity()!!.getSharedPreferences("Account", Context.MODE_PRIVATE)
        val idu: Int = settings.getInt("idu", 0)
        val ids: Int = settings.getInt("ids", 0)
//            contextThis= requireActivity()
            ex=root.findViewById(R.id.exit)
        if(ids==0 && idu==0){
            ex.text="Войти в аккаунт"
            ex.setOnClickListener{
                startActivity(Intent(context, MainActivity::class.java))
            }
        }
        else {
            ex.setOnClickListener {
                val settings: SharedPreferences =
                    requireActivity()!!.getSharedPreferences("Account", Context.MODE_PRIVATE)
                val editor = settings.edit()
                editor.clear()
                editor.apply()
                requireActivity().finish()
            }
        }

        gradeb=root.findViewById(R.id.gradebook)
        if(ids==0){
            gradeb.setVisibility(View.GONE)
        }
        gradeb.setOnClickListener{
            startActivity(Intent(context, GradeBActivity::class.java))
        }
        allteach=root.findViewById(R.id.teachers)
        allteach.setOnClickListener{
            startActivity(Intent(context, AllTeachers::class.java))
        }
        feedb=root.findViewById(R.id.feedback)
        feedb.setOnClickListener{
            startActivity(Intent(context, Feedback::class.java))
        }

        docs=root.findViewById(R.id.documents)
        if(ids!=0 || idu!=0){
            docs.setVisibility(View.GONE)
        }
        docs.setOnClickListener{
            startActivity(Intent(context, Documents::class.java))
        }
        uname=root.findViewById(R.id.textViewName)
        val name: String = settings.getString("firstname", "")+" "+settings.getString("lastname", "")
        uname.text = name
        ugroup=root.findViewById(R.id.textViewGroup)
        when {
            idu != 0 -> {
                ugroup.text = "Преподаватель"
                uname.text = name}
            ids != 0 -> {
                var year_group:Int=settings.getInt("year_group", 0)
                year_group=year_group%100
                stgroup=year_group.toString()+"-"+settings.getString("shortname", "")+"-"+settings.getInt("num_course", 0)
                ugroup.text =stgroup
                uname.text = name
            }
            else -> ugroup.text ="Войдите в аккаунт"
        }
        return root
    }
}