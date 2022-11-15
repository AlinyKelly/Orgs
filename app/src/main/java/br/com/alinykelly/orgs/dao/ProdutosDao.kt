package br.com.alinykelly.orgs.dao

import br.com.alinykelly.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    fun adiciona(produto: Produto){
        produtos.add(produto)
    }

    fun buscaTodos() : List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(nome = "Salada de Frutas",
            descricao = "Laranja, uva, mirtilo",
            valor = BigDecimal("19.55")
            )
        )
    }
}