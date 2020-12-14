package com.zhyea.bamboo.general

import android.app.Activity
import android.app.Dialog
import android.content.Context

/**
 * er
 */
class CustomDialog(context: Context, themeResId: Int) : Dialog(context, themeResId) {


    private fun onUserInteraction() {
        if (context is Activity) {
            val a = context as Activity
            a.onUserInteraction()
        }
    }


}