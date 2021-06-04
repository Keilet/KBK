package com.example.kbk.model

import retrofit2.http.Field

data class SendQuestion(
    val enrollee_name: String,
    val enrollee_phone: String,
    val question: String,
    val enrollee_time: String
)
