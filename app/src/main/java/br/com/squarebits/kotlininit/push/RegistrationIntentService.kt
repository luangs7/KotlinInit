package br.com.squarebits.kotlininit.push

import android.app.IntentService
import android.content.Context
import android.content.Intent
import br.com.squarebits.kotlininit.extras.Utils
import br.com.squarebits.kotlininit.model.User
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId

/**
 * Created by Luan on 22/05/17.
 */

class RegistrationIntentService : IntentService(LOG) {
    lateinit var context: Context
    lateinit internal var refreshedToken: String
    lateinit internal var user: User
    override fun onHandleIntent(intent: Intent?) {

        synchronized(LOG) {
            try {
                context = this
                FirebaseApp.initializeApp(context)

                //                String deviceid = new UtilsPlus().getDeviceID();
                refreshedToken = FirebaseInstanceId.getInstance().token as String
                //implementar request aqui
                user = Utils().getSharedAuth(context)
                //                if(!refreshedToken.equalsIgnoreCase(user.getDevice()))
                //                    register(refreshedToken); //Here to call the register request

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    companion object {
        val LOG = "LOG"
    }

}

