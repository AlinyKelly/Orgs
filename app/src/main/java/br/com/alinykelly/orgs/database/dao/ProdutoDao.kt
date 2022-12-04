package br.com.alinykelly.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.alinykelly.orgs.model.Produto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscarTodos(): Flow<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvar(vararg produto: Produto)

    @Delete
    suspend fun remover(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun buscarPorId(id: Long): Flow<Produto?>

    //Ordenacao da lista de produtos
    @Query("SELECT * FROM Produto ORDER BY nome ASC")
    fun buscarTodosOrdenadorPorNomeAsc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER BY nome DESC")
    fun buscarTodosOrdenadorPorNomeDesc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER BY descricao ASC")
    fun buscarTodosOrdenadorPorDescricaoAsc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER BY descricao DESC")
    fun buscarTodosOrdenadorPorDescricaoDesc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER BY valor ASC")
    fun buscarTodosOrdenadorPorValorAsc(): Flow<List<Produto>>

    @Query("SELECT * FROM Produto ORDER BY valor DESC")
    fun buscarTodosOrdenadorPorValorDesc(): Flow<List<Produto>>
}