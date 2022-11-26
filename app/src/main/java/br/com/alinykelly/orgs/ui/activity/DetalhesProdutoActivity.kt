package br.com.alinykelly.orgs.ui.activity

import android.os.Bundle
import android.util.Log
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

    private lateinit var produto: Produto
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::produto.isInitialized) {
            val db = AppDatabase.instancia(this)
            val produtoDao = db.produtoDao()
            when (item.itemId) {
                R.id.menu_detalhes_produto_remover -> {
                    produtoDao.remove(produto)
                    finish()
                }
                R.id.menu_detalhes_produto_editar -> {
                    Log.i("TAG", "onOptionsItemSelected: editar")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        // tentativa de buscar o produto se ele existir,
        // caso contrário, finalizar a Activity
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            produto = produtoCarregado
            preencheCampos(produtoCarregado)
        } ?: finish()
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityDetalhesProdutoImagem.tentarCarregarImagem(produtoCarregado.imagem)
            activityDetalhesProdutoNome.text = produtoCarregado.nome
            activityDetalhesProdutoDescricao.text = produtoCarregado.descricao
            activityDetalhesProdutoValor.text =
                produtoCarregado.valor.formatarParaMoedaBrasileira()
        }
    }

}