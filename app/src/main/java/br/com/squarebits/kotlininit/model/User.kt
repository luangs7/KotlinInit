package br.com.squarebits.kotlininit.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Luan on 09/11/17.
 */

@Entity
class User {


    @PrimaryKey(autoGenerate = true)
    var id:Int? = 0
    var nome: String = ""
    var cargo: String = ""

    constructor(){

    }

    constructor(id: Int, nome: String, cargo: String){
        this.id = id
        this.nome = nome
        this.cargo = cargo

    }

    override fun toString(): String {
        return "Tarefa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                '}'
    }



}