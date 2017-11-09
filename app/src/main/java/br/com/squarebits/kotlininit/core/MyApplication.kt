package br.com.squarebits.kotlininit.core

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.text.Html
import android.util.Log
import java.net.InetAddress
import java.util.*


/**
 * Created by luan on 1/15/15.
 */
class MyApplication : Application() {


    val context: Context
        get() = this


    /*public void downloadImage(Context context, String url, ImageView imageView) {
            Picasso.with(context).load(url).placeholder(R.drawable.img_placeholder_custom)
                    .error(R.drawable.img_placeholder_custom).into(imageView);
    }

    public void downloadImage(Context context, String url, ImageView imageView, Callback callback) {
        Picasso.with(context).load(url).placeholder(R.drawable.img_placeholder_custom)
                .error(R.drawable.img_placeholder_custom).into(imageView, callback);
    }*/


    //You can replace it with your name
    val isInternetAvailable: Boolean
        get() {
            try {
                val ipAddr = InetAddress.getByName("https://www.google.com")

                return if (ipAddr != null) {
                    false
                } else {
                    true
                }

            } catch (e: Exception) {
                return false
            }

        }

    /*  public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni == null ? false : true;
    } */

    val isNetworkConnected: Boolean
        get() {
            try {
                val nInfo = getSystemService(
                        Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                nInfo.activeNetworkInfo.isConnectedOrConnecting

                val cm = getSystemService(
                        Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val netInfo = cm.activeNetworkInfo
                return if (netInfo != null && netInfo.isConnectedOrConnecting) {

                    true
                } else {
                    false
                }
            } catch (e: Exception) {
                return false
            }

        }


    override fun onCreate() {
        //        MultiDex.install(getApplicationContext());
        super.onCreate()
        //        Realm.init(this);
        instance = this
        //        try{
        //            FirebaseApp.initializeApp(this);
        //            FirebaseMessaging.getInstance().subscribeToTopic("general");
        //        }catch (Exception e){
        //            Log.e("teste",e.getMessage());
        //        }


        //        UtilsPlus.initialize(getApplicationContext(),getPackageName());


    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */

    fun pauseApp() {
        timer = Timer()
        Log.i("Main", "Invoking logout timer")
        val logoutTimeTask = LogOutTimerTask()
        timer!!.schedule(logoutTimeTask, (900000 * 2).toLong())
        //        900000*2
    }

    fun onResume() {
        if (timer != null) {
            timer!!.cancel()
            Log.i("Main", "cancel timer")
            timer = null
        }
    }

    private inner class LogOutTimerTask : TimerTask() {

        override fun run() {
            //            new Utils().clearShared(sInstance);
            //redirect user to login screen
            //            Intent i = new Intent(getInstance(), MainActivity.class);
            //            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //            i.putExtra("timeout",true);
            //            startActivity(i);

        }

    }

    fun changeActionBarTitle(activity: Activity, title: String) {
        activity.actionBar!!.title = Html.fromHtml("<font color='#ffffff'>$title</font>")
    }

    enum class TrackerName {
        APP_TRACKER, // Tracker used only in this app.
        GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
        ECOMMERCE_TRACKER
        // Tracker used by all ecommerce transactions from a company.
    }


    fun applicationName(): String {
        try {

            val appR = context.resources
            val txt = appR.getText(appR.getIdentifier("app_name", "string", context.packageName))
            return txt.toString()
        } catch (ex: Exception) {
            Log.e("applicationColor", ex.message)
            return MyApplication::class.java.`package`.name.replace("br.com.", "")
        }


    }

    override fun onTerminate() {
        super.onTerminate()
    }

    companion object {
        private var timer: Timer? = null


        //    private static int VERSION_IMPORTACAO = 1;
        //    public RealmConfiguration configurationSurvey(){
        //
        //        RealmConfiguration configuration = new RealmConfiguration.Builder()
        //                .name("reymaster.realm")
        //                .schemaVersion(1)
        //                .modules(new ObjectModule())
        //                .deleteRealmIfMigrationNeeded()
        //                .build();
        //        Realm.setDefaultConfiguration(configuration);
        //        return configuration;
        //    }


        /**
         * @return MyApplication singleton instance
         */
        @get:Synchronized
        var instance: MyApplication? = null
            private set

        fun existeConexao(context: Context): Boolean {
            val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val netInfo = connectivity.activeNetworkInfo ?: return false

                // Se não existe nenhum tipo de conexão retorna false

                val netType = netInfo.type

                // Verifica se a conexão é do tipo WiFi ou Mobile e
                // retorna true se estiver conectado ou false em
                // caso contrário
                return if (netType == ConnectivityManager.TYPE_WIFI || netType == ConnectivityManager.TYPE_MOBILE) {
                    netInfo.isConnected

                } else {
                    false
                }

        }
    }
}
