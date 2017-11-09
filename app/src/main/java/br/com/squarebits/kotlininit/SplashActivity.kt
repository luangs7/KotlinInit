package br.com.squarebits.kotlininit

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import br.com.squarebits.kotlininit.dao.LocalDbImplement
import br.com.squarebits.kotlininit.model.User


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSIONS_CODE)
        } else {
            open()
        }

        //        RegistrationIntentService.start(this);

        //        open();

    }

    fun open() {
        Handler().postDelayed({
            val intent = Intent()
            val auth = LocalDbImplement<User>(this@SplashActivity).getDefault(User::class.java)
            //                if(auth != null){
            //                    intent.setClass(SplashActivity.this, MainActivity.class);
            //                }else{
            //                    intent.setClass(SplashActivity.this, LoginActivity.class);
            //                }
            intent.setClass(this@SplashActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }, 3000)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSIONS_CODE -> for (i in permissions.indices) {

                if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE, ignoreCase = true) && grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "Favor habilitar a permissão para usar o aplicativo!", Toast.LENGTH_LONG).show()
                    finishAffinity()
                    return
                } else if (permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE, ignoreCase = true) && grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "Favor habilitar a permissão para usar o aplicativo!", Toast.LENGTH_LONG).show()
                    finishAffinity()
                    return
                } else if (permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION, ignoreCase = true) && grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "Favor habilitar a permissão para usar o aplicativo!", Toast.LENGTH_LONG).show()
                    finishAffinity()
                    return
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        open()
    }

    companion object {
        private val REQUEST_PERMISSIONS_CODE = 128
    }
}
