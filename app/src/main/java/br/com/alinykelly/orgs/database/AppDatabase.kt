package br.com.alinykelly.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.alinykelly.orgs.database.converter.Converters
import br.com.alinykelly.orgs.database.dao.ProdutoDao
import br.com.alinykelly.orgs.database.dao.UsuarioDao
import br.com.alinykelly.orgs.model.Produto
import br.com.alinykelly.orgs.model.Usuario


@Database(entities = [Produto::class, Usuario::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {

        @Volatile
        private var db: AppDatabase? = null

        fun instancia(context: Context): AppDatabase {
            return db ?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            ).allowMainThreadQueries()
                .build().also {
                    db = it
                }
        }
    }
}