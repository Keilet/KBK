package com.example.kbk

data class LoginResponse(
        var error: Boolean,
        var message: String,
        var user: User) {

}