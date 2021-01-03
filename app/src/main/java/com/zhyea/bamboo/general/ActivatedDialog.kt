package com.zhyea.bamboo.general

import android.content.Context
import android.view.KeyEvent

class ActivatedDialog(
    private val handler: ContentController,
    context: Context,
    resetWindow: Boolean,
    clearFlags: Boolean
) :
    ZyDialog(context, resetWindow, clearFlags) {


    override fun onBackPressed() {
        handler.getActivity().onBackPressed()
    }

    override fun onKeyDown(keyCode: Int, keyEvent: KeyEvent): Boolean {
        return if (!super.onKeyDown(keyCode, keyEvent)) {
            handler.onKeyDown(keyCode, keyEvent)
        } else {
            true
        }
    }

    override fun onKeyUp(keyCode: Int, keyEvent: KeyEvent): Boolean {
        return if (!super.onKeyUp(keyCode, keyEvent)) {
            handler.onKeyUp(keyCode, keyEvent)
        } else {
            true
        }
    }

}