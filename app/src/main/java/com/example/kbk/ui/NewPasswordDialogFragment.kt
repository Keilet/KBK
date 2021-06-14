package com.example.kbk.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.kbk.R
import com.example.kbk.model.LoginResponse
import com.example.kbk.model.SendQuestion
import com.example.kbk.model.User
import com.example.kbk.network.Api
import com.example.kbk.network.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewPasswordDialogFragment: DialogFragment() {

    lateinit var newpass: TextInputEditText
    lateinit var newpass2: TextInputEditText
    lateinit var bcancel:Button
    lateinit var bsave:Button


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val settings: SharedPreferences =
            requireActivity().getSharedPreferences("Account", Context.MODE_PRIVATE)
        val builder = AlertDialog.Builder(
            activity
        )
        val inflater: LayoutInflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(com.example.kbk.R.layout.dialog_password, null)
        builder.setView(view)

        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceBuilder.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: Api = retrofit.create(Api::class.java)

        newpass=view.findViewById(R.id.newpass)
        newpass2=view.findViewById(R.id.newpass2)
        bcancel=view.findViewById(R.id.cancel)
        bsave=view.findViewById(R.id.save)

       // if(pass1.isNotEmpty() && pass2.isNotEmpty() && pass1==pass2) bsave.setTextColor(Color.parseColor("#fd961f"))

        fun sendForm() {
            val pass1: String = newpass.text.toString()
            val pass2: String = newpass2.text.toString()
               if (pass1.isEmpty()){
                    newpass.setError("Введите новый пароль")
                    newpass.requestFocus()
                    return
                }
               if (pass2.isEmpty()) {
                    newpass2.setError("Повторите новый пароль")
                    newpass2.requestFocus()
                    return
                }
               if (pass1!=pass2){
                    newpass2.setError("Пароли не совпадают")
                    newpass2.requestFocus()
                    return
                }
                val ids: Int = settings.getInt("ids", 0)
            if (ids!=0) {
                var call: Call<LoginResponse> = service.updatepassword(ids,pass1)
                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        Toast.makeText(requireContext(), "Пароль изменен", Toast.LENGTH_LONG).show()
                        dialog!!.dismiss()
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        //Log.e(TAG, "Failed to fetch photos", t)
                        Toast.makeText(requireContext(), "Пароль изменен", Toast.LENGTH_LONG).show()
                        dialog!!.dismiss()
                    }
                })

            }

            }

        bsave.setOnClickListener {
            sendForm()
        }
        bcancel.setOnClickListener {
            dialog!!.dismiss()
        }

        return builder.create()
    }
}