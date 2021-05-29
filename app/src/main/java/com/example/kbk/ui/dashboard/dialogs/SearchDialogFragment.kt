package com.example.kbk.ui.dashboard.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog.show
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class SearchDialogFragment: DialogFragment() {

    private lateinit var searchd: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val searchDialogFragment = SearchDialogFragment()
            val manager = requireFragmentManager()
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Поиск по")
                .setCancelable(true)
                .setPositiveButton("Группе") { dialog, id ->
                    GroupSDialog.show(manager, "groupSDialog")
                }
                .setNegativeButton("Преподавателю",
                    DialogInterface.OnClickListener { dialog, id ->
                        TeacherSDialog.show(manager, "teacherSDialog")
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}