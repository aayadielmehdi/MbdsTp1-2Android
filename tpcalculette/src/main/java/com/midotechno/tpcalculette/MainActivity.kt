package com.midotechno.tpcalculette

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var num1  = 0f
    var num2 = 0f
    var operation : Op_enum = Op_enum.nothing
    var result = 0f
    var encours : Boolean = false // pour connaitre si une opératin est en cours

    lateinit var historiqueText:TextView
    lateinit var saisieText:TextView
    lateinit var btnReset:Button


    // le scroll view affiche les historique avec un scroll

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        historiqueText = findViewById<EditText>(R.id.textViewHist2)
        saisieText = findViewById<EditText>(R.id.editTextSaisie2)
        btnReset = findViewById<Button>(R.id.buttonac)
        saisieText.text = ""

    }


    fun onClick(v:View){
        val element = v as? Button ?: return
        if ((element.text.equals("0") or element.text.equals("1")
            or element.text.equals("2") or element.text.equals("6")
            or element.text.equals("3") or element.text.equals("7")
            or element.text.equals("4") or element.text.equals("8")
            or element.text.equals("5") or element.text.equals("9")) or (element.text.equals(".") and !(saisieText.text.contains(".")))){

            // verification aussi sur les virgules

            saisieText.text = (saisieText.text.toString() + element.text.toString())
            btnReset.text = "C"   // le changement de ac vers c afin de modifier just l'operation en cours comme en calculatrice scientifique
        }
    }

    // fonction sur les opérations pour définir quel opération on a choisie
    fun onClickOperation(v:View){
        val element = v as? Button ?: return

        if (encours.equals(false) and  !(saisieText.text.equals("")) ) {
            var result = element.text.toString()
            when (result) {
                "+" -> operation = Op_enum.addition
                "*" -> operation = Op_enum.multiplication
                "/" -> operation = Op_enum.division
                "-" -> operation = Op_enum.soustraction
            }
            encours=true
            num1 = saisieText.text.toString().toFloat()
            saisieText.text = ("".toString())
        }

    }

    fun onClickResult(v:View){
        // dans ce cas on sait qu'on utilise cette fonction que pour un seul bouton
        // faut il la faire ou pas


        // faire encore le test si pc est bien en cliquant sur C AC puis une opération de plus

        val element = v as? Button ?: return
        if (encours.equals(true) and element.text.equals("=")){
            if (saisieText.text.equals("")){

                // on choisissant une opération et click directe sur result sans saisir un nouveau element
                // return error

                saisieText.text = "Error"

            }else{
                num2 = saisieText.text.toString().toFloat()
                val op = Operation(operation,num1,num2)
                saisieText.text = op.result().toString()
                result = op.result()
                historiqueText.text = historiqueText.text.toString() +"\n->$num1"+operationSymbole(operation)+"$num2=$result"

            }
            btnReset.text="AC"
            operation=Op_enum.nothing
            encours = false

        }
    }

    // je utilise cette fonction pour l'affichage en historique
    fun operationSymbole(op:Op_enum):String{
        when(op) {
            Op_enum.addition -> return "+"
            Op_enum.division -> return "/"
            Op_enum.soustraction -> return "-"
            Op_enum.multiplication -> return "*"
            else-> return ""
        }
    }


    // le switch entre ac et c clear et clearall
    fun onClickClear(v:View){
        // traitement du modification ou reset global
        // historique reste tjr visible
        val element = v as? Button ?: return
        // le cas de modification
        if (element.text.equals("AC")){
            encours = false
            saisieText.text=""
            num1 = 0f
            num2= 0f
            result=0f
            operation=Op_enum.nothing
        }else{
            saisieText.text=""
            num2= 0f
            result=0f
            btnReset.text="AC"
        }
    }



}