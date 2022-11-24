package br.com.alinykelly.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.com.alinykelly.orgs.dao.ProdutosDao
import br.com.alinykelly.orgs.database.AppDatabase
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
    }

    private fun configuraBotaoSalvar() {
        //Processo de binding utilizando lambda
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar

        val db = AppDatabase.instancia(this)

        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            val produtoDao = db.produtoDao()
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
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }
}