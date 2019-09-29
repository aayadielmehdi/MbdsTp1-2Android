package com.midotechno.tpcalculette

data class  Operation(private val operation:Op_enum ,private  val number1:Float ,private val number2:Float){

    public fun result():Float{
        when(operation) {
            Op_enum.addition -> return (number1 + number2)
            Op_enum.division -> return (number1 / number2)
            Op_enum.soustraction -> return (number1 - number2)
            Op_enum.multiplication -> return (number1 * number2)
            else-> return 0f
        }
    }
}