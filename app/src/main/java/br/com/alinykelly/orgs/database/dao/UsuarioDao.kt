package br.com.alinykelly.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.alinykelly.orgs.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun salvar(usuario: Usuario)
}