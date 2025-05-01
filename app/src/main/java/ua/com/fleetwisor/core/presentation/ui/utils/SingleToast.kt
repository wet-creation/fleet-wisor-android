package ua.com.fleetwisor.core.presentation.ui.utils

import android.content.Context
import android.widget.Toast


object SingleToast {
    private var mToast: Toast? = null

    fun makeText(context: Context?, text: Int, duration: Int) {
        if (mToast != null) mToast!!.cancel()
        mToast = Toast.makeText(context, text, duration)
        mToast!!.show()
    }
}