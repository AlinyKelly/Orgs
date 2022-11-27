package br.com.alinykelly.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alinykelly.orgs.database.AppDatabase
import br.com.alinykelly.orgs.database.dao.ProdutoDao
import br.com.alinykelly.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alinykelly.orgs.extensions.tentarCarregarImagem
import br.com.alinykelly.orgs.model.Produto
import br.com.alinykelly.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private var url: String? = null
    private var produtoId = 0L
    private val produtoDao: ProdutoDao by lazy {
        val db = AppDatabase.instancia(this)
        db.produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar produto"
        configuraBotaoSalvar()

        binding.activityFormularioProdutoImagem.setOnClickListener {
            FormularioImagemDialog(this)
                .mostrar(url) { imagem ->
                    url = imagem
                    binding.activityFormularioProdutoImagem.tentarCarregarImagem(url)
                }
        }
        //Verifica se o produto é novo ou é uma edição e carrega as informações já cadastradas
        tentarCarregarProduto()
    }

    private fun tentarCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    override fun onResume() {
        super.onResume()
        tentarBuscarProduto()
    }

    private fun tentarBuscarProduto() {
        produtoDao.buscarPorId(produtoId)?.let {
            title = "Alterar Produto"
            preencherCampos(it)
        }
    }

    private fun preencherCampos(produto: Produto) {

        url = produto.imagem
        binding.activityFormularioProdutoImagem.tentarCarregarImagem(produto.imagem)
        binding.activityFormularioProdutoNome.setText(produto.nome)
        binding.activityFormularioProdutoDescricao.setText(produto.descricao)
        binding.activityFormularioProdutoValor.setText(produto.valor.toPlainString())
    }

    private fun configuraBotaoSalvar() {
        //Processo de binding utilizando lambda
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar

        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
//            if (produtoId > 0) {
//                produtoDao.alterar(produtoNovo)
//            } else {
//                produtoDao.salvar(produtoNovo)
//            }
            produtoDao.salvar(produtoNovo)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()

        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()

        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            id = produtoId,
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }
}