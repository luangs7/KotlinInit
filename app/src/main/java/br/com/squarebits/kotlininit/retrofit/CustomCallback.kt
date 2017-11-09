package br.com.squarebits.kotlininit.retrofit

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.view.View
import br.com.squarebits.kotlininit.R
import br.com.squarebits.kotlininit.model.BaseRequest
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Luan on 17/10/17.
 */
class CustomCallback<T> : Callback<T> {

    lateinit var context: Context
    lateinit var  dialog: ProgressDialog
    lateinit var onResponse: OnResponse<T>
    lateinit var viewLayout: View
    
    constructor(context: Context){
        this.context = context
        dialog = ProgressDialog(context, R.style.AppTheme_AlertDialog)
        dialog.setCancelable(false)
        dialog.setMessage("Buscando dados...")
        dialog.show()

        this.onResponse = onResponse
    }

    constructor(context: Context, onResponse: OnResponse<T>){
        this.context = context
        dialog = ProgressDialog(context, R.style.AppTheme_AlertDialog)
        dialog.setCancelable(false)
        dialog.setMessage("Buscando dados...")
        dialog.show()
        this.onResponse = onResponse
    }


    constructor(context: Context, viewLayout: View, onResponse: OnResponse<T>) {
        this.context = context
        dialog = ProgressDialog(context, R.style.AppTheme_AlertDialog)
        dialog.setCancelable(false)
        dialog.setMessage("Buscando dados...")
        dialog.show()
        this.onResponse = onResponse
        this.viewLayout = viewLayout
    }

    constructor(context: Activity, onResponse: OnResponse<T>, viewLayout: View) {
        this(context, onResponse, true)
        this.viewLayout = viewLayout
    }

    constructor(context: Activity, onResponse: OnResponse<T>, loadDialog: Boolean){
        this.context = context
        if (loadDialog) {
            try {
                dialog = ProgressDialog(context, R.style.AppTheme_AlertDialog)
                dialog.setCancelable(false)
                dialog.setMessage("Buscando dados...")
                dialog.show()
            } catch (ex: Throwable) {
                Log.e(" dialog!!", "CustomCallback: ", ex)
            }

        } else {

        }

        this.onResponse = onResponse
    }

    constructor(context: Context, onResponse: OnResponse<T>, loadDialog: Boolean){
        this.context = context
        if (loadDialog) {
            try {
                dialog = ProgressDialog(context, R.style.AppTheme_AlertDialog)
                dialog.setCancelable(false)
                dialog.setMessage("Buscando dados...")
                dialog.show()
            } catch (ex: Throwable) {
                Log.e(" dialog!!", "CustomCallback: ", ex)
            }

        } else {

        }

        this.onResponse = onResponse
    }


    constructor(context: Context,  Text: String, onResponse: OnResponse<T>)  {
        this.context = context
        dialog = ProgressDialog(context, R.style.AppTheme_AlertDialog)
        dialog.setCancelable(false)
        dialog.setMessage(Text)
        dialog.show()
        this.onResponse = onResponse
    }

    constructor(context: Activity,  Text: String, onResponse: OnResponse<T>)  {
        this.context = context
        dialog = ProgressDialog(context, R.style.AppTheme_AlertDialog)
        dialog.setCancelable(false)
        dialog.setMessage(Text)
        dialog.show()
        this.onResponse = onResponse
    }


    constructor(context: Context,  Text: String, viewLayout: View, onResponse: OnResponse<T>)  {
        this.context = context
        dialog = ProgressDialog(context, R.style.AppTheme_AlertDialog)
        dialog.setCancelable(false)
        dialog.setMessage( Text)
        dialog.show()
        this.onResponse = onResponse
        this.viewLayout = viewLayout
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        var error = ""
        try {
            dialog.dismiss()
        } catch (e: Exception) {

        }

        if (response!!.isSuccessful())
            onResponse.onResponse(response!!.body())
        else {
            if (response.code() == 202) {
                try {
                    onResponse.onResponse(response.body())
                } catch (ex: Exception) {
                    try {
                        onResponse.onFailure(Throwable(Gson().fromJson<BaseRequest>(error, BaseRequest::class.java).getMessage()))
                    } catch (ex2: Exception) {
                        onResponse.onFailure(Throwable(context.getResources().getString(R.string.error401)))
                    }

                }

            }
            if (response.code() == 401) {
                try {
                    error = response.errorBody().string()
                    onResponse.onFailure(Throwable(Gson().fromJson<BaseRequest>(error, BaseRequest::class.java).getMessage()))
                } catch (ex: Exception) {
                    try {
                        onResponse.onFailure(Throwable(Gson().fromJson<BaseRequest>(error, BaseRequest::class.java).getMessage()))
                    } catch (ex2: Exception) {
                        onResponse.onFailure(Throwable(context.getResources().getString(R.string.error401)))
                    }

                }

            } else if (response.code() == 404) {
                try {
                    error = response.errorBody().string()
                    onResponse.onResponse(Gson().fromJson<BaseRequest>(error, BaseRequest::class.java) as T)
                } catch (ex: Exception) {
                    try {
                        onResponse.onFailure(Throwable(Gson().fromJson<BaseRequest>(error, BaseRequest::class.java).getMessage()))
                    } catch (ex2: Exception) {
                        onResponse.onFailure(Throwable(context.getResources().getString(R.string.error404)))
                    }

                }

            } else if (response.code() == 422) {
                try {
                    error = response.errorBody().string()
                    var message = ""

                    val jsnobject = JSONObject(error)
                    val jsonArray = jsnobject.getJSONArray("reason")
                    for (i in 0..jsonArray.length() - 1) {
                        message = jsonArray.get(i).toString()
                    }
                    onResponse.onFailure(Throwable(message))
                } catch (ex: Exception) {
                    try {
                        onResponse.onFailure(Throwable(Gson().fromJson<BaseRequest>(error, BaseRequest::class.java).getMessage()))
                    } catch (ex2: Exception) {
                        onResponse.onFailure(Throwable("Ocorreu um erro"))
                    }

                }

            } else if (response.code() == 500) {
                try {
                    onResponse.onFailure(Throwable(context.getResources().getString(R.string.error500)))
                } catch (ex: Exception) {
                    try {
                        onResponse.onFailure(Throwable(Gson().fromJson<BaseRequest>(error, BaseRequest::class.java).getMessage()))
                    } catch (ex2: Exception) {
                        onResponse.onFailure(Throwable("Ocorreu um erro"))
                    }

                }

            } else if (response.code() == 429) {
                try {
                    onResponse.onRetry(Throwable(context.getResources().getString(R.string.error401)))
                } catch (ex: Exception) {
                    try {
                        onResponse.onFailure(Throwable(Gson().fromJson<BaseRequest>(error, BaseRequest::class.java).getMessage()))
                    } catch (ex2: Exception) {
                        onResponse.onFailure(Throwable("Ocorreu um erro"))
                    }

                }

            } else {
                try {
                    error = response.errorBody().string()
                    val errorBody = Gson().fromJson<BaseRequest>(error, BaseRequest::class.java)
                    onResponse.onFailure(Throwable(errorBody.getMessage()))
                } catch (ex: Exception) {
                    onResponse.onFailure(Throwable("Ocorreu um erro"))
                }

            }
        }
    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    interface OnResponse<T> {
        fun onResponse(response: T)
        fun onFailure(t: Throwable)
        fun onRetry(t: Throwable)
    }
}

private operator fun  <T> CustomCallback<T>.invoke(context: Activity, onResponse: Any, b: Boolean) {}
