package com.midotechno.exemplecours.Entities

import com.midotechno.exemplecours.R

data class Article(var title:String ="", var description:String ="" , var image :Int = R.mipmap.mbds_foreground){

    public fun CreateArticle():ArrayList<Article>{

        var liste : ArrayList<Article> = ArrayList()

        for (i in 0 .. 3){
            var A : Article = Article()
            A.title = "Titre ${i+1}"
            A.description="Ici c'est la description de mon article ${i+1}"
            liste.add(A)
        }

        return  liste
    }

}