package com.zhyea.bamboo.general

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import java.lang.ref.WeakReference
import java.util.*


/**
 * er
 */
class CustomDialog(context: Context, themeResId: Int) : Dialog(context, themeResId) {


    private val c: LinkedList = LinkedList()
    private val d: DialogInterface.OnDismissListener? = null

    /**
     * a
     */
    private fun onUserInteraction() {
        if (context is Activity) {
            val a = context as Activity
            a.onUserInteraction()
        }
    }


    override fun dismiss() {
        val localIterator: MutableIterator<*> = c.iterator()
        while (localIterator.hasNext()) if ((localIterator.next() as WeakReference).get() as Dialog === this) localIterator.remove()
        d?.onDismiss(this)
        val localActivity: Activity = DkApp.get().getCurrentActivity()
        if (localActivity != null && !localActivity.isFinishing) super.dismiss()
    }

    override fun dispatchKeyEvent(keyEvent: KeyEvent): Boolean {
        onUserInteraction()
        return super.dispatchKeyEvent(keyEvent)
    }

    override fun dispatchTouchEvent(motionEvent: MotionEvent): Boolean {
        onUserInteraction()
        return super.dispatchTouchEvent(motionEvent)
    }

    override fun dispatchTrackballEvent(motionEvent: MotionEvent): Boolean {
        onUserInteraction()
        return super.dispatchTrackballEvent(motionEvent)
    }

    override fun setContentView(resource: Int) {
        setContentView(LayoutInflater.from(context).inflate(resource, null))
    }

    override fun setContentView(view: View) {
        setContentView(view, null)
    }


}