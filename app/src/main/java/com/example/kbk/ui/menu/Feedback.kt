package com.example.kbk.ui.menu

import android.app.TimePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.kbk.R
import com.example.kbk.activities.Bnv
import com.example.kbk.model.LoginResponse
import com.example.kbk.model.SendQuestion
import com.example.kbk.network.Api
import com.example.kbk.network.ServiceBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.enterst.*
import kotlinx.android.synthetic.main.menu_feedback.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*


class Feedback : AppCompatActivity(), View.OnClickListener {

    lateinit var usname: TextInputEditText
    lateinit var usphone: TextInputEditText
    lateinit var usquestion: TextInputEditText
    lateinit var ustime: TextInputEditText
    lateinit var timetextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_feedback)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed() // возврат на предыдущий activity
        }
        //ustime = findViewById(R.id.ustime)
        ussend.setOnClickListener(this)

        time.setOnClickListener {
            openTimePiker()
        }
    }

    fun sendForm() {
        usname = findViewById(R.id.usname)
        usphone = findViewById(R.id.usphone)
        usquestion = findViewById(R.id.usquestion)
        timetextView = findViewById(R.id.timeTv)
        val uname: String = usname.text.toString().trim()
        val uphone: String = usphone.text.toString().trim()
        val uquestion: String = usquestion.text.toString().trim()
        val utime: String = timetextView.text.toString().trim()
//        ustime = findViewById(R.id.ustime)
        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceBuilder.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: Api = retrofit.create(Api::class.java)

        if (uname.isEmpty()) {
            usname.setError("Введите свое имя")
            usname.requestFocus()
            return
        }

        if (uphone.isEmpty()) {
            usphone.setError("Введите номер телефона")
            usphone.requestFocus()
            return
        }
        if (uquestion.isEmpty()) {
            usquestion.setError("Введите свое имя")
            usquestion.requestFocus()
            return
        }

        if (utime.isEmpty()) {

            Toast.makeText(applicationContext, "Укажите время для звонка", Toast.LENGTH_LONG).show()
//            timetextView.setError("Выберите время")
//            timetextView.requestFocus()
            return
        }

        var call: Call<SendQuestion> = service.sendQuestion(uname, uphone, uquestion, utime)

        call.enqueue(object : Callback<SendQuestion> {
            override fun onResponse(call: Call<SendQuestion>, response: Response<SendQuestion>) {
                Toast.makeText(applicationContext, "Форма отправлена", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<SendQuestion>, t: Throwable) {
                //Log.e(TAG, "Failed to fetch photos", t)
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onClick(v: View?) {
        sendForm()
    }

    fun openTimePiker(){
        timetextView = findViewById(R.id.timeTv)
        val cal = Calendar.getInstance()
        val timeSetListener=TimePickerDialog.OnTimeSetListener{view: TimePicker?, hourOfDay: Int, minute: Int ->  cal.set(Calendar.HOUR_OF_DAY,hourOfDay)
            cal.set(Calendar.MINUTE, minute)
            timetextView.text=SimpleDateFormat("HH:mm").format(cal.time)}


        TimePickerDialog(this,timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }


}