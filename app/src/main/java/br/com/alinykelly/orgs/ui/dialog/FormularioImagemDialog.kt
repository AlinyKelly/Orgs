package br.com.alinykelly.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import br.com.alinykelly.orgs.databinding.FormularioImagemBinding
import br.com.alinykelly.orgs.extensions.tentarCarregarImagem

class FormularioImagemDialog(private val context: Context) {
    fun mostrar(quandoImagemCarregada: (imagem: String) -> Unit){
        val binding = FormularioImagemBinding
            .inflate(LayoutInflater.from(context))
        binding.formularioImagemBotaocarregar.setOnClickListener{
            val url = binding.formularioImagemUrl.text.toString()
            binding.formularioImagemImageview.tentarCarregarImagem(url)
        }

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") {
                    _, _ ->
                val url = binding.formularioImagemUrl.text.toString()
                quandoImagemCarregada(url)

            }
            .setNegativeButton("Cancelar") {
                    _, _ ->
            }
            .show()
    }

}