package br.com.squarebits.kotlininit.room

import android.arch.persistence.room.*
import br.com.squarebits.kotlininit.model.User


/**
 * Created by Luan on 09/11/17.
 */

@Dao
interface UserDao {

    @Insert
    fun criarUser(mObject: User)

    @Query("SELECT * FROM Tarefa")
    fun lerUserAll(): ArrayList<User>

    @Query("SELECT * FROM Tarefa WHERE id = :id")
    fun lerUserPeloId(id: Int): User

    @Update
    fun actualizarUser(mObject: User)

    @Delete
    fun eliminarUser(mObject: User)

}