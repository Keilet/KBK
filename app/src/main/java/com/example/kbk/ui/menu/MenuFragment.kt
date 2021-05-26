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
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

//    private lateinit var contextThis: Context
    private lateinit var menuViewModel: MenuViewModel
    private lateinit var ex:Button
    private lateinit var gradeb:Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        menuViewModel =
            ViewModelProvider(this).get(MenuViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_menu, container, false)
//            contextThis= requireActivity()
            ex=root.findViewById(R.id.exit)
            ex.setOnClickListener{
                val settings: SharedPreferences =
                    requireActivity()!!.getSharedPreferences("Account", Context.MODE_PRIVATE)
                val editor = settings.edit()
                editor.clear()
                editor.apply()
                requireActivity().finish()
            }
        gradeb=root.findViewById(R.id.gradebook)
        gradeb.setOnClickListener{
            startActivity(Intent(context, GradeBActivity::class.java))
        }
        return root
    }
}