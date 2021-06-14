package com.example.kbk

class Values {
    private lateinit var firstOperand:String
    private var secondOperand:Int = 0

    fun equalsToValues(values: Values): Boolean {
        return firstOperand == values.firstOperand && secondOperand == values.secondOperand
    }
    fun getFirstOperand(): String {
        return firstOperand
    }
    fun getSecondOperand(): Int {
        return secondOperand
    }
    fun setFirstOperand(st:String)
    {
        firstOperand=st
    }
    fun setSecondOperand(st: Int)
    {
        secondOperand=st
    }
}