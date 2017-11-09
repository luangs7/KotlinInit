package br.com.squarebits.kotlininit


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import br.com.squarebits.kotlininit.presenter.GeralPresenterImpl
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.share


open class BaseActivity : AppCompatActivity(), GeralPresenterImpl.ActivityPresenterImpl {

    lateinit internal var mListener: GeralPresenterImpl.ActivityPresenterImpl
    lateinit internal var mViewProgress: View
    lateinit internal var mViewButton: View
    lateinit internal var mActivity: Activity
//    internal var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        user = Utils().getSharedAuth(this)
        showDebugDBAddressLogToast(this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)

    }

    override
    fun onEmpty() {

    }
    override
    fun onErrorAlert(erro: String) {
        longToast(erro)
    }
    override
    fun onAlertMessage(msg: String) {
        longToast(msg)
    }

    override
    fun onProgressShow() {
        val progress = mViewProgress as ProgressBar?
        val button = mViewButton as RelativeLayout?
        progress!!.visibility = View.VISIBLE
        button!!.visibility = View.INVISIBLE
    }

    override
    fun onProgressDismiss() {
        val progress = mViewProgress as ProgressBar?
        val button = mViewButton as RelativeLayout?
        progress!!.visibility = View.GONE
        button!!.visibility = View.VISIBLE
    }



    override
    fun startActivity(activity: Activity) {
        startActivity(Intent(baseContext, activity.javaClass))
    }
    override
    fun startActivity(mActivity: Activity, extraValue: String, extraKey:String) {
        startActivity(intentFor<MainActivity>(extraKey to extraValue).clearTop())

//        startActivity(Intent(baseContext, activity.javaClass).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))

    }

    override
    fun onAlertDialogMessage(title: String, text: String, mActivity: Activity) {
        val builder = AlertDialog.Builder(this, R.style.AppTheme_AlertDialog)
        builder.setTitle(title)
        builder.setMessage(text)
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { arg0, _ -> arg0.dismiss() }

        val alerta = builder.create()
        alerta.show()
    }

    override
    fun onAlertDialogMessageFinish(title: String, text: String, mActivity: Activity) {
        val builder = AlertDialog.Builder(this, R.style.AppTheme_AlertDialog)
        builder.setTitle(title)
        builder.setCancelable(false)
        builder.setMessage(text)
        builder.setPositiveButton("OK") { arg0, _ ->
            arg0.dismiss()
            finishAffinity()
            startActivity(Intent(baseContext, mActivity.javaClass))
        }

        val alerta = builder.create()
        alerta.show()
    }

    override fun onPhoneDispatcher(number:String) {
//        makeCall(number)
    }

    override fun onSharedButton(text:String,subject: String) {
        share(text,subject)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {

        fun showDebugDBAddressLogToast(context: Context) {
            if (BuildConfig.DEBUG) {
                try {
                    val debugDB = Class.forName("com.amitshekhar.DebugDB")
                    val getAddressLog = debugDB.getMethod("getAddressLog")
                    val value = getAddressLog.invoke(null)
                    Log.d("DB_DEBUG", value as String)
                    //                Toast.makeText(context, (String) value, Toast.LENGTH_LONG).show();
                } catch (ignore: Exception) {

                }

            }
        }
    }


}
