package com.zhyea.bamboo.reader

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
import com.zhyea.bamboo.general.ActivatedHandler
import com.zhyea.bamboo.general.IRequestHandler
import java.util.*
import java.util.concurrent.TimeUnit


class BambooActivity : Activity() {


    private val listener: BambooActivityListener = BambooActivityListener(this)

    /**
     * n
     */
    private var wakeLock: WakeLock? = null

    /**
     * o
     */
    private var handler: Handler? = null

    /**
     * d
     */
    private val windowRotationChangeListeners: LinkedList<OnWindowRotationChangeListener> = LinkedList()

    /**
     * p
     */
    private val delayMillis = 0;


    private fun lockScreen() {
        getScreenLock().acquire(TimeUnit.MINUTES.toMillis(1))
    }

    private fun unlockScreen() {
        getScreenLock().release()
    }

    private fun resetScreenTimeout() {
        if (null == this.handler) {
            this.handler = Handler(Looper.getMainLooper(), new DkActivity .3(this))
        }
        this.handler!!.removeCallbacksAndMessages(null);
        if (0 == delayMillis) {
            unlockScreen();
        }
        if ((0 != this.delayMillis) && (this.delayMillis != Int.MAX_VALUE)) {
            this.handler!!.sendEmptyMessageDelayed(0, this.delayMillis.toLong())
        }
    }

    private fun getScreenLock(): WakeLock {
        if (this.wakeLock == null) {
            val powerManager: PowerManager = getSystemService(POWER_SERVICE) as PowerManager
            this.wakeLock = powerManager.newWakeLock(FLAG_KEEP_SCREEN_ON, "actTag:")
            this.wakeLock!!.setReferenceCounted(false)
        }
        return this.wakeLock!!
    }

    private fun closeScreenTimeout() {
        this.handler?.removeCallbacksAndMessages(null)
    }


    private fun notifyWindowRotationChange(eventId: Int) {
        for (listener in windowRotationChangeListeners) {
            listener.onWindowRotationChange(eventId)
        }
    }

    /**
     * -------------------------------------------------------------------------
     */

    class BambooActivityTask : TimerTask() {
        override fun run() {
            TODO("Not yet implemented")
        }

    }


    class BambooActivityListener(private val activity: BambooActivity) : IRequestHandler {

        override fun requestDeactive(request: ActivatedHandler?): Boolean {
            return false
        }

        override fun requestHideMenu() {
        }

        override fun requestShowMenu() {
            TODO("Not yet implemented")
        }


        fun getSoftInputMode(handler: ActivatedHandler): Int {
            return this.activity.window.attributes.softInputMode
        }

    }


    interface OnWindowRotationChangeListener {
        fun onWindowRotationChange(eventId: Int)
    }

}