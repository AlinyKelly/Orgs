package br.com.alinykelly.orgs.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alinykelly.orgs.R
import br.com.alinykelly.orgs.model.Produto
import br.com.alinykelly.orgs.ui.recyclerView.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : Activity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        //recyclerView.layoutManager = LinearLayoutManager(this)
    }

}