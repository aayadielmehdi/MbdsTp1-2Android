package com.midotechno.tpcalculette

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.text.method.ScrollingMovementMethod

class MainActivity : AppCompatActivity() {

    var num1  = 0f
    var num2 = 0f
    var operation : Op_enum = Op_enum.nothing
    var result = 0f
    var encours : Boolean = false // pour connaitre si une opératin est en cours

    lateinit var historiqueText:TextView
    lateinit var saisieText:TextView
    lateinit var btnReset:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        historiqueText = findViewById<EditText>(R.id.textView3)

        // cette methode ne permet pas de rendre le textview scrollable ??
        historiqueText.movementMethod = ScrollingMovementMethod()


        saisieText = findViewById<EditText>(R.id.editText2)
        btnReset = findViewById<Button>(R.id.button16)
        saisieText.text = "0"

    }


    fun onClick(v:View){
        val element = v as? Button ?: return
        if (element.text.equals("0") or element.text.equals("1")
            or element.text.equals("2") or element.text.equals("6")
            or element.text.equals("3") or element.text.equals("7")
            or element.text.equals("4") or element.text.equals("8")
            or element.text.equals("5") or element.text.equals("9")){
            saisieText.text = (saisieText.text.toString() + element.text.toString())
            btnReset.text = "C"   // au traiment changement du text btn reset ( afin de faire la modif du champs saisie seulement )
        }
    }

    fun onClickOperation(v:View){
        val element = v as? Button ?: return

        if (encours.equals(false)) {
            var result = element.text.toString()
            when (result) {
                "+" -> operation = Op_enum.addition
                "*" -> operation = Op_enum.multiplication
                "/" -> operation = Op_enum.division
                "-" -> operation = Op_enum.soustraction
            }
            encours=true
            num1 = saisieText.text.toString().toFloat()
            saisieText.text = ("0".toString())
        }

    }

    fun onClickResult(v:View){
        // dans ce cas on sait qu'on utilise cette fonction que pour un seul bouton
        // faut il la faire ou pas


        // faire encore le test si pc est bien en cliquant sur C AC puis une opération de plus

        val element = v as? Button ?: return
        if (encours.equals(true) and element.text.equals("=")){
            if (operation.equals(Op_enum.division) and saisieText.text.equals("0") ){

                // saisietext.text.tostring.tofloat = 0 ?? c'est la verification qu'il faut faire au lieu de 0

                // afficher infinity au lieu d'error
                saisieText.text = ("Error")
            }else{
                num2 = saisieText.text.toString().toFloat()
                val op = Operation(operation,num1,num2)
                saisieText.text = ""
                result = op.result()
                historiqueText.text = historiqueText.text.toString() +"\n->$num1"+operationSymbole(operation)+"$num2=$result"
                operation=Op_enum.nothing
                encours = false
            }
        }
    }

    fun operationSymbole(op:Op_enum):String{
        when(op) {
            Op_enum.addition -> return "+"
            Op_enum.division -> return "/"
            Op_enum.soustraction -> return "-"
            Op_enum.multiplication -> return "*"
            else-> return ""
        }
    }

    fun onClickClear(v:View){
        // traitement du modification ou reset global
        // historique reste tjr visible
        val element = v as? Button ?: return
        // le cas de modification
        if (element.text.equals("AC")){
            encours = false
            saisieText.text="0"
            num1 = 0f
            num2= 0f
            result=0f
            operation=Op_enum.nothing
        }else{
            saisieText.text="0"
            num2= 0f
            result=0f
            btnReset.text="AC"
        }
    }



}