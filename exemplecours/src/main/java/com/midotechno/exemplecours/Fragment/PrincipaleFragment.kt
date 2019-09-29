package com.midotechno.exemplecours.Fragment


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.midotechno.exemplecours.Adapter.ArticleAdapter
import com.midotechno.exemplecours.Entities.Article

import com.midotechno.exemplecours.R

class PrincipaleFragment : Fragment() {

    init {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {

        // Inflate the layout for this fragment
        var v:View = inflater.inflate(R.layout.fragment_principale, container, false)


        //recupérer une liste de string depuis les ressources
        var planetes:Array<*> = resources.getStringArray(R.array.planetes)
        //recupérer l'instance du spinner dans la vue
        var spinner:Spinner = v.findViewById(R.id.spinner)

        //instancier l'adapteur
        var adapter:ArrayAdapter<*> = ArrayAdapter(v.context, android.R.layout.simple_spinner_item,planetes)

        //associer l'adapter au spinner

        spinner.adapter = adapter


        // verifier comment appeler un evenement hors du on create

        //Listener quand l'utilisateur selectionne un élément
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(v.context, "Vous n'avez rien selectionné", Toast.LENGTH_LONG).show()
            }
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(v.context, "Vous avez selectionné ${planetes[position]}", Toast.LENGTH_LONG).show()
            }
        }


        var A = Article()
        var liste:List<Article> = A.CreateArticle()   // convertir arraylist to list

        // partie recycler view
        //instance du recycler
        var recyclerView : RecyclerView = v.findViewById(R.id.reycler_view)
        //créer une liste d'articles
        val articles = liste      // listOf<Article>()
        //créer une instance de l'adapteur
        var adapterRecycler = ArticleAdapter(articles)
        //définir l'orientation des élements (vertical)
        recyclerView.layoutManager = LinearLayoutManager(v.context)
        //associer l'adapter à la recyclerview
        recyclerView.adapter = adapterRecycler


        return v
    }


}
