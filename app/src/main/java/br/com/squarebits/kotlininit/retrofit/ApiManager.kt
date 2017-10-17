package br.com.squarebits.kotlininit.retrofit

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Luan on 17/10/17.
 */
class ApiManager {

    var endpoint = "https://api.myjson.com/bins/"
    var context : Context? = null
    var retrofit: Retrofit? = null
    var okHttpClient: OkHttpClient? = null



    constructor(context: Context){
        this.context = context

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY


        okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout((30000 * 6).toLong(), TimeUnit.MILLISECONDS)
                .readTimeout((30000 * 6).toLong(), TimeUnit.MILLISECONDS)
                .writeTimeout((30000 * 6).toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(logInterceptor)
                .build()


        val gson = GsonBuilder()
                .create()


        retrofit = Retrofit.Builder()
                .baseUrl(endpoint)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

    }


    constructor(retrofit: Retrofit, okHttpClient: OkHttpClient){
        this.retrofit = retrofit
        this.okHttpClient = okHttpClient
    }



    constructor(context: Context,retrofit: Retrofit, okHttpClient: OkHttpClient){
        this.context = context
        this.retrofit = retrofit
        this.okHttpClient = okHttpClient
    }

}