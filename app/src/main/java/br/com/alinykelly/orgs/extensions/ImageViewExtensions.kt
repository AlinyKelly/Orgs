package br.com.alinykelly.orgs.extensions

import android.widget.ImageView
import br.com.alinykelly.orgs.R
import coil.load

fun ImageView.tentarCarregarImagem(url: String? = null){
    load(url) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}