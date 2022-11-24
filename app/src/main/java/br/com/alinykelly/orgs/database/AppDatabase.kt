package br.com.alinykelly.orgs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.alinykelly.orgs.database.converter.Converters
import br.com.alinykelly.orgs.database.dao.ProdutoDao
import br.com.alinykelly.orgs.model.Produto

@Database(entities = [Produto::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao() : ProdutoDao
}