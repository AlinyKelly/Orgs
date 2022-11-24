package br.com.alinykelly.orgs.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.alinykelly.orgs.model.Produto

@Database(entities = [Produto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao() : ProdutoDao
}