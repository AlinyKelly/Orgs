package br.com.alinykelly.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.alinykelly.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscarTodos(): List<Produto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvar(vararg produto: Produto)

    @Delete
    fun remover(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun buscarPorId(id: Long?): Produto?

    //Ordenacao da lista de produtos
    @Query("SELECT * FROM Produto ORDER BY nome ASC")
    fun buscarTodosOrdenadorPorNomeAsc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY nome DESC")
    fun buscarTodosOrdenadorPorNomeDesc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY descricao ASC")
    fun buscarTodosOrdenadorPorDescricaoAsc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY descricao DESC")
    fun buscarTodosOrdenadorPorDescricaoDesc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY valor ASC")
    fun buscarTodosOrdenadorPorValorAsc(): List<Produto>

    @Query("SELECT * FROM Produto ORDER BY valor DESC")
    fun buscarTodosOrdenadorPorValorDesc(): List<Produto>
}