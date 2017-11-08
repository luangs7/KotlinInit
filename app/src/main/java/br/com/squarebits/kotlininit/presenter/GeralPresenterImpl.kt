package br.com.squarebits.kotlininit.presenter

import android.app.Activity

/**
 * Created by Luan on 06/06/17.
 */

class GeralPresenterImpl {

    interface ActivityPresenterImpl {
        fun onEmpty()
        fun onErrorAlert(erro: String)
        fun onAlertMessage(erro: String)
        fun onAlertDialogMessage(title: String, text: String, mActivity: Activity)
        fun onAlertDialogMessageFinish(title: String, text: String, mActivity: Activity)
        fun onProgressShow()
        fun onProgressDismiss()
        fun onPhoneDispatcher()
        fun onSharedButton()
        fun onFacebookButton()
        fun startActivity(activity: Activity)
        fun startActivity(activity: Activity, extra: String)
        fun onBack()

    }

}
