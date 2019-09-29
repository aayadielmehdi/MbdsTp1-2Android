package com.midotechno.exemplecours

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.midotechno.exemplecours.Fragment.PrincipaleFragment
import com.midotechno.exemplecours.Fragment.ArticlesFragment


class MainActivity : AppCompatActivity() {


    lateinit var fragment:Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragement1,PrincipaleFragment()).commit()

    }


    fun pagePrincipale(view: View) {

        //créer une instance du fragment
        fragment = PrincipaleFragment()

        //créer un transaction sur le fragment manager
        supportFragmentManager.beginTransaction().apply {
            //replacer le précédent fragment, s'il existe
            replace(R.id.fragement1, fragment)
            //ajouter la transaction dans la stack
            addToBackStack(null)
        }.commit()
        //finalement, on valide la transaction

    }


    fun secondPage(view: View) {

        //créer une instance du fragment
        fragment = ArticlesFragment()

        //créer un transaction sur le fragment manager
        supportFragmentManager.beginTransaction().apply {
            //replacer le précédent fragment, s'il existe
            replace(R.id.fragement1, fragment)
            //ajouter la transaction dans la stack
            addToBackStack(null)
        }.commit()
        //finalement, on valide la transaction

    }
}
