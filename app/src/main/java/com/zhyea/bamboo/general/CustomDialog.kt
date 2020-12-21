package com.zhyea.bamboo.general

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.PixelFormat.OPAQUE
import android.graphics.PixelFormat.TRANSLUCENT
import android.view.*
import android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
import com.zhyea.bamboo.R
import com.zhyea.bamboo.reader.BambooApp
import java.lang.ref.WeakReference
import java.util.*


/**
 * er
 */
class CustomDialog(context: Context, paramBoolean1: Boolean, clearFlags: Boolean) :
    Dialog(context, R.style.AppTheme) {

    private val dialogContext: Context = context;

    private val dismissListener: DialogInterface.OnDismissListener? = null

    init {
        var window: Window? = null
        if (clearFlags) {
            window = getWindow()
            window!!.clearFlags(WindowManager.LayoutParams.ANIMATION_CHANGED)
        }
        for (format in TRANSLUCENT..OPAQUE) {
            if (paramBoolean1) {
                break
            }
            window!!.setFormat(format)
            getWindow()!!.addFlags(FLAG_NOT_TOUCHABLE)
        }
    }


    constructor(context: Context, paramBoolean1: Boolean, clearFlags: Boolean, resId: Int)
            : this(context, paramBoolean1, clearFlags) {
        window!!.setWindowAnimations(resId)
    }


    /**
     * a
     */
    private fun onUserInteraction() {
        if (context is Activity) {
            val a = context as Activity
            a.onUserInteraction()
        }
    }


    /**
     * c
     */
    private fun dialog(): Dialog? {
        for (reference in dialogList) {
            val dialog = reference.get()
            if (null != dialog) {
                return dialog
            }
        }
        return null
    }

    /**
     * b
     */
    private fun window(): Window? {
        val dialog: Dialog? = dialog()
        if (null != dialog) {
            return dialog.window
        }
        if (context is Activity) {
            return (context as Activity).window
        }
        return null
    }


    override fun dismiss() {
        val itr = dialogList.iterator()
        while (itr.hasNext()) {
            val dialog = itr.next().get()
            if (dialog === this) {
                itr.remove()
            }
        }

        dismissListener?.onDismiss(this)

        val act: Activity? = BambooApp.get()!!.getCurrentActivity()
        if (null != act && !act.isFinishing) {
            super.dismiss()
        }
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

    override fun setContentView(resId: Int) {
        setContentView(LayoutInflater.from(context).inflate(resId, null))
    }

    override fun setContentView(view: View) {
        setContentView(view, null)
    }


    companion object {

        private val dialogList: LinkedList<WeakReference<Dialog>> = LinkedList()

        private var dismissListener: DialogInterface.OnDismissListener? = null

        fun dialog(): Dialog? {
            for (e in this.dialogList) {
                val d = e.get()
                if (null != d) {
                    return d
                }
            }
            return null
        }


        fun set(listener: DialogInterface.OnDismissListener) {
            dismissListener = listener
        }

    }


}