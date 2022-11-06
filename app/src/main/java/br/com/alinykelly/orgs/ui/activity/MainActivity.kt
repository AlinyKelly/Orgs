package br.com.alinykelly.orgs.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.alinykelly.orgs.R
import br.com.alinykelly.orgs.model.Produto
import br.com.alinykelly.orgs.ui.recyclerView.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListaProdutosAdapter(
            context = this, produtos = listOf(
                Produto(
                    nome = "teste Produto 1",
                    descricao = "teste descricao",
                    valor = BigDecimal("19.99")
                ),
                Produto(
                    nome = "teste Produto 2",
                    descricao = "teste descricao 2",
                    valor = BigDecimal("10.88")
                ),
                Produto(
                    nome = "teste Produto 3",
                    descricao = "teste descricao 3",
                    valor = BigDecimal("1.88")
                ),

                )
        )
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java)
            startActivity(intent)
        }
    }

}