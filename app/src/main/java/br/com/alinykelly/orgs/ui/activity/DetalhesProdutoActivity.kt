package br.com.alinykelly.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alinykelly.orgs.R
import br.com.alinykelly.orgs.database.AppDatabase
import br.com.alinykelly.orgs.databinding.ActivityDetalhesProdutoBinding
import br.com.alinykelly.orgs.extensions.formatarParaMoedaBrasileira
import br.com.alinykelly.orgs.extensions.tentarCarregarImagem
import br.com.alinykelly.orgs.model.Produto

class DetalhesProdutoActivity : AppCompatActivity() {

    private var produtoId: Long? = null
    private var produto: Produto? = null
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onResume() {
        super.onResume()
        produtoId.let { id ->
            produto = produtoDao.buscarPorId(id)
        }
        produto?.let {
            preencherCampos(it)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.menu_detalhes_produto_remover -> {
                    produto?.let { produtoDao.remover(it) }
                    finish()
                }
                R.id.menu_detalhes_produto_editar -> {
                    //Abrir o formulario Cadastrar Produto
                    Intent(this, FormularioProdutoActivity::class.java).apply {
                        putExtra(CHAVE_PRODUTO, produto)
                        startActivity(this)
                    }
                }
            }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        // tentativa de buscar o produto se ele existir,
        // caso contrário, finalizar a Activity
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            produtoId = produtoCarregado.id
        } ?: finish()
    }

    private fun preencherCampos(produtoCarregado: Produto) {
        with(binding) {
            activityDetalhesProdutoImagem.tentarCarregarImagem(produtoCarregado.imagem)
            activityDetalhesProdutoNome.text = produtoCarregado.nome
            activityDetalhesProdutoDescricao.text = produtoCarregado.descricao
            activityDetalhesProdutoValor.text =
                produtoCarregado.valor.formatarParaMoedaBrasileira()
        }
    }

}