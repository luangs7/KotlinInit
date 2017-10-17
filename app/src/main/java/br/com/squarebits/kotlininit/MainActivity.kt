package br.com.squarebits.kotlininit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.squarebits.kotlininit.model.Machine
import br.com.squarebits.kotlininit.retrofit.ApiManager
import br.com.squarebits.kotlininit.retrofit.CustomCallback
import br.com.squarebits.kotlininit.retrofit.RequestInterface

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ApiManager(this)
                .retrofit?.create(RequestInterface::class.java)?.
                getData()?.
                enqueue(CustomCallback<List<Machine>>(this, onResponse = object : CustomCallback.OnResponse<List<Machine>> {

            override fun onResponse(response: List<Machine>) {
                Log.d("teste","teste");

            }

            override fun onFailure(t: Throwable) {
                Log.d("teste","teste");

            }

            override fun onRetry(t: Throwable) {
                Log.d("teste","teste");

            }
        }))


    }



}
