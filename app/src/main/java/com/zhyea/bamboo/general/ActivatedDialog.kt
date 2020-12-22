package com.zhyea.bamboo.general

import android.content.Context
import android.view.KeyEvent

class ActivatedDialog(handler: ActivatedHandler, context: Context, resetWindow: Boolean, clearFlags: Boolean) :
    ZyDialog(context, resetWindow, clearFlags) {


    override fun onBackPressed() {
        this.context.getActivity().onBackPressed()
    }

    override fun onKeyDown(paramInt: Int, paramKeyEvent: KeyEvent): Boolean {
        return if (!super.onKeyDown(paramInt, paramKeyEvent)) iw.`access$300`(this.a, paramInt, paramKeyEvent) else true
    }

    override fun onKeyUp(paramInt: Int, paramKeyEvent: KeyEvent): Boolean {
        return if (!super.onKeyUp(paramInt, paramKeyEvent)) iw.`access$400`(this.a, paramInt, paramKeyEvent) else true
    }

}