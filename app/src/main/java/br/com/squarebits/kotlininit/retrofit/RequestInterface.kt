package br.com.squarebits.kotlininit.retrofit

import br.com.squarebits.kotlininit.model.Machine
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Luan on 17/10/17.
 */
interface RequestInterface {

    @GET("s8cml")
    fun getData(): Call<List<Machine>>

}