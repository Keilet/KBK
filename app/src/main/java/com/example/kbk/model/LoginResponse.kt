package com.example.kbk.model


data class LoginResponse(
        val error: Boolean,
        val message: String,
        val user: User
) {
}