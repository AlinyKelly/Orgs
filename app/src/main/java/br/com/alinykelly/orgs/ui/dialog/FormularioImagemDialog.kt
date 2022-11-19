package br.com.alinykelly.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alinykelly.orgs.databinding.FormularioImagemBinding
import br.com.alinykelly.orgs.extensions.tentarCarregarImagem

class FormularioImagemDialog(private val context: Context) {
    fun mostrar(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit
    ){
        FormularioImagemBinding
            .inflate(LayoutInflater.from(context)).apply {
                urlPadrao?.let {
                    formularioImagemImageview.tentarCarregarImagem(it)
                    formularioImagemUrl.setText(it)
                }

                formularioImagemBotaocarregar.setOnClickListener{
                    val url = formularioImagemUrl.text.toString()
                    formularioImagemImageview.tentarCarregarImagem(url)
                }

                AlertDialog.Builder(context)
                    .setView(root)
                    .setPositiveButton("Confirmar") {
                            _, _ ->
                        val url = formularioImagemUrl.text.toString()
                        quandoImagemCarregada(url)
                    }
                    .setNegativeButton("Cancelar") {
                            _, _ ->
                    }
                    .show()
            }


    }

}