package br.com.alinykelly.orgs.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.alinykelly.orgs.R
import br.com.alinykelly.orgs.database.AppDatabase
import br.com.alinykelly.orgs.databinding.ActivityListaProdutosBinding
import br.com.alinykelly.orgs.model.Produto
import br.com.alinykelly.orgs.ui.recyclerView.adapter.ListaProdutosAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

private const val TAG = "ListaProdutosActivity"

class ListaProdutosActivity : AppCompatActivity() {
    private val adapter = ListaProdutosAdapter(context = this)
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }
    private val dao by lazy {
        val db = AppDatabase.instancia(this)
        db.produtoDao()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
        lifecycleScope.launch {
            dao.buscarTodos().collect {
                produtos -> adapter.atualiza(produtos)
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//        adapter.atualiza(dao.buscarTodos())
//    }
//
//    //inflar o menu de ordenacao de itens
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_lista_produtos, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    //Ordenar itens
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val produtosOrdenado: Flow<List<Produto>>? = when (item.itemId) {
//            R.id.menu_lista_produtos_ordenar_nome_asc -> dao.buscarTodosOrdenadorPorNomeAsc()
//            R.id.menu_lista_produtos_ordenar_nome_desc -> dao.buscarTodosOrdenadorPorNomeDesc()
//            R.id.menu_lista_produtos_ordenar_descricao_asc -> dao.buscarTodosOrdenadorPorDescricaoAsc()
//            R.id.menu_lista_produtos_ordenar_descricao_desc -> dao.buscarTodosOrdenadorPorDescricaoDesc()
//            R.id.menu_lista_produtos_ordenar_valor_asc -> dao.buscarTodosOrdenadorPorValorAsc()
//            R.id.menu_lista_produtos_ordenar_valor_desc -> dao.buscarTodosOrdenadorPorValorDesc()
//            R.id.menu_lista_produtos_ordenar_sem_ordem -> dao.buscarTodos()
//            else -> null
//        }
////        produtosOrdenado?.let {
////            adapter.atualiza(it)
////        }
//        return super.onOptionsItemSelected(item)
//    }

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
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }
        //Menu popup
        adapter.quandoClicarEmEditar = {
            Log.i(TAG, "configuraRecyclerView: Editar $it")
        }
        adapter.quandoClicarEmRemover = {
            Log.i(TAG, "configuraRecyclerView: Remover $it")
        }

    }

}