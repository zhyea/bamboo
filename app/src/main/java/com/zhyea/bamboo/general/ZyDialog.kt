package com.zhyea.bamboo.general

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.PixelFormat.OPAQUE
import android.graphics.PixelFormat.TRANSLUCENT
import android.view.*
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
import com.zhyea.bamboo.R
import com.zhyea.bamboo.reader.BambooApp
import java.lang.ref.WeakReference
import java.util.*


/**
 * er
 */
open class ZyDialog(context: Context, resetWindow: Boolean, clearFlags: Boolean) :
    Dialog(context, R.style.AppTheme) {

    /**
     * d
     */
    private val dismissListener: DialogInterface.OnDismissListener? = null

    /**
     * b
     */
    private var layout: ZyLayout? = null

    init {
        var window: Window? = null
        if (clearFlags) {
            window = getWindow()
            window!!.clearFlags(WindowManager.LayoutParams.ANIMATION_CHANGED)
        }
        for (format in TRANSLUCENT..OPAQUE) {
            if (!resetWindow) {
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
        for (reference in showingDialogs) {
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
        val itr = showingDialogs.iterator()
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

    override fun setContentView(paramView: View, layoutParams: ViewGroup.LayoutParams?) {
        var params = layoutParams
        this.layout = ZyLayout(context)
        val tmp: ZyLayout? = this.layout
        if (params == null) {
            params = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }
        tmp!!.addView(paramView, params)
        super.setContentView(this.layout!!, ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT))
    }


    override fun show() {
        if (!isShowing) {
            val attributes = window!!.attributes
            attributes.width = MATCH_PARENT
            attributes.height = MATCH_PARENT
            val win: Window? = window()
            if (win != null) {
                attributes.flags =
                    (-0x11 and win.attributes.flags) or (FLAG_NOT_TOUCHABLE and attributes.flags)
            }
            window!!.attributes = attributes
            showingDialogs.addFirst(WeakReference<Dialog>(this))
        }
        super.show()
    }

    companion object {

        private val showingDialogs: LinkedList<WeakReference<Dialog>> = LinkedList()

        private var dismissListener: DialogInterface.OnDismissListener? = null

        fun dialog(): Dialog? {
            for (e in this.showingDialogs) {
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