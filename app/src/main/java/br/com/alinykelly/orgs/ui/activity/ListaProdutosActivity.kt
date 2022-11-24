package br.com.alinykelly.orgs.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.com.alinykelly.orgs.dao.ProdutosDao
import br.com.alinykelly.orgs.database.AppDatabase
import br.com.alinykelly.orgs.databinding.ActivityListaProdutosBinding
import br.com.alinykelly.orgs.model.Produto
import br.com.alinykelly.orgs.ui.recyclerView.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class ListaProdutosActivity : AppCompatActivity() {

    private val dao = ProdutosDao()
    private val adapter = ListaProdutosAdapter(
        context = this, produtos = dao.buscaTodos()
    )
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()

    }

    override fun onResume() {
        super.onResume()

        val db = AppDatabase.instancia(this)
        val produtoDao = db.produtoDao()
        adapter.atualiza(produtoDao.buscarTodos())
    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutoFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutoRecyclerView
        recyclerView.adapter = adapter

        // implementação do listener para abrir a Activity de detalhes do produto
        // com o produto clicado
        adapter.quandoClicarNoItem = {
            val intent = Intent(
                this,
                DetalhesProdutoActivity::class.java
            ).apply {
                // envio do produto por meio do extra
                putExtra(CHAVE_PRODUTO, it)
            }
            startActivity(intent)
        }

    }

}