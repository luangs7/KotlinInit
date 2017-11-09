package br.com.squarebits.kotlininit.push

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import android.util.Log
import br.com.squarebits.kotlininit.MainActivity
import br.com.squarebits.kotlininit.R
import br.com.squarebits.kotlininit.extras.Utils
import br.com.squarebits.kotlininit.model.User
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


/**
 * Created by Luan on 22/05/17.
 */

class MyFirebaseMessagingService : FirebaseMessagingService() {

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]

    lateinit internal var id: String
    lateinit internal var nome: String
    internal var onGoing = false
    internal var autoCancel = true
    lateinit internal var user: User

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        Log.d(TAG, "From: " + remoteMessage!!.from)

        user = Utils().getSharedAuth(this)

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
            remoteMessage.notification
            id = remoteMessage.data["company_id"] as String

            sendNotification(remoteMessage.data["text"])
        } else if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification.body!!)
            remoteMessage.notification
            sendNotification(remoteMessage.notification.body)
        }


    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private fun sendNotification(messageBody: String?) {
        val intent: Intent


        intent = Intent(this, MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(resources.getColor(R.color.summer_sky))
                .setContentTitle(resources.getString(R.string.app_name))
                .setContentText(messageBody)
                .setStyle(NotificationCompat.BigTextStyle().bigText(messageBody))
                .setAutoCancel(autoCancel)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setOngoing(onGoing)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (user != null)
            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    companion object {

        private val TAG = "MyFirebaseMsgService"
    }
}