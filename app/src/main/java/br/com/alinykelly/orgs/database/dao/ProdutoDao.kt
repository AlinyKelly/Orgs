package br.com.alinykelly.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.alinykelly.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscarTodos() : List<Produto>

    @Insert
    fun salvar(vararg produto: Produto)

    @Delete
    fun remove(produto: Produto)


}