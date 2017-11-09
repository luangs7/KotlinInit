package br.com.squarebits.kotlininit.room

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.DatabaseConfiguration
import android.arch.persistence.room.InvalidationTracker
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


/**
 * Created by Luan on 09/11/17.
 */

//@Database(entities = {User.class}, version = 1)

abstract class BaseDados: RoomDatabase {

    private lateinit var INSTANCE: BaseDados

    constructor(){

    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getDatabase(context: Context): BaseDados {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    BaseDados::class.java,
                    "arch_components_db")
                    .build()
        }
        return INSTANCE
    }

    abstract fun tarefaDao(): UserDao

}