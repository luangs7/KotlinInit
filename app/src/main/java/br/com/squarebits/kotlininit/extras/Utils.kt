package br.com.squarebits.kotlininit.extras

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ListView
import br.com.squarebits.kotlininit.dao.LocalDbImplement
import br.com.squarebits.kotlininit.model.User
import java.text.SimpleDateFormat
import java.util.regex.Pattern



/**
 * Created by Luan on 04/05/17.
 */

class Utils {

    var contentView: View? = null

    constructor(contentView: View) {
        this.contentView = contentView
    }

    constructor() {}

    fun closeKey(mActivity: Activity) {
        val view = mActivity.currentFocus
        if (view != null) {
            val imm = mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    //
    fun getSharedAuth(context: Context): User {
        return LocalDbImplement<User>(context).getDefault(User::class.java) as User
    }

//    fun getSharedCard(context: Context): CreditCard {
//        return LocalDbImplement<CreditCard>(context).getDefault(CreditCard::class.java)
//    }
//
//
//    //
    fun clearShared(context: Context) {
        LocalDbImplement<User>(context).clearObject(User::class.java)
        //        new LocalDbImplement<CreditCard>(context).clearObject(CreditCard.class);
//        LocalDbImplement<Customer>(context).clearObject(Customer::class.java)

    }
//
//    //
    fun setShared(context: Context, `object`: User) {
        LocalDbImplement<User>(context).save(`object`)
    }
//
//    fun setShared(context: Context, `object`: Customer) {
//        LocalDbImplement<Customer>(context).save(`object`)
//    }
//
//    fun setShared(context: Context, `object`: CreditCard) {
//        LocalDbImplement<CreditCard>(context).save(`object`)
//    }
//
//    fun clearCard(context: Context) {
//        LocalDbImplement<CreditCard>(context).clearObject(CreditCard::class.java)
//    }

    companion object {


        fun setListViewHeightBasedOnChildren(listView: ListView) {
            val listAdapter = listView.adapter ?: // pre-condition
                    return

            var totalHeight = 0
            for (i in 0 until listAdapter.count) {
                val listItem = listAdapter.getView(i, null, listView)
                listItem.measure(0, 0)
                totalHeight += listItem.measuredHeight
            }

            val params = listView.layoutParams
            params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
            listView.layoutParams = params
            listView.requestLayout()
        }

        fun checkEmpty(editText: EditText): Boolean {
            return if (editText.text.length < 1)
                true
            else
                false
        }

        fun checkEmptyMultiple(editTexts: Array<EditText>): Boolean {
            for (currentField in editTexts) {
                if (currentField.text.toString().length <= 0) {
                    return true
                }
            }
            return false
        }

        val currentTimeMonthAndYear: String
            get() {
                val date = System.currentTimeMillis()
                val dateFormat = SimpleDateFormat("MMMM yyyy")

                return dateFormat.format(date)
            }

        fun getCurrentTimeFormatted(format: String): String {
            val date = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat(format)

            return dateFormat.format(date)
        }

        fun isEmailValid(email: CharSequence): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                    .matches()
        }

        fun licensePlate(plate: String): Boolean {
            val m = Pattern.compile("[A-Z]{3}\\d{4}").matcher(plate.replace("-", "").toUpperCase())

            return m.find()
        }

        fun getTextTrim(editText: EditText): String {
            return editText.text.toString().trim { it <= ' ' }
        }

        fun getDefaultValue(editText: EditText, defaultValue: String): String {
            return if (editText.text.length > 0)
                editText.text.toString().trim { it <= ' ' }
            else
                defaultValue

        }

        fun getText(editText: EditText): String {
            return editText.text.toString()
        }

        fun translateType(type: String): String {
            return if (type.equals("User", ignoreCase = true))
                "Usuário"
            else if (type.equals("Profissional", ignoreCase = true))
                "Profissional"
            else if (type.toLowerCase().equals("professional", ignoreCase = true))
                "Profissional"
            else if (type.equals("month", ignoreCase = true))
                "Mês"
            else if (type.equals("hour", ignoreCase = true))
                "Hora"
            else
                type
        }
    }
}
