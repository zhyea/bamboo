package com.zhyea.bamboo.reader

import android.app.Activity
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
import com.zhyea.bamboo.general.ActivatedHandler
import com.zhyea.bamboo.general.IRequestHandler
import java.util.*


class BambooActivity : Activity() {


    private val listener: BambooActivityListener = BambooActivityListener(this)

    private var wakeLock: WakeLock? = null


    private fun getScreenLock(): WakeLock {
        if (this.wakeLock == null) {
            val powerManager: PowerManager = getSystemService(POWER_SERVICE) as PowerManager
            this.wakeLock = powerManager.newWakeLock(FLAG_KEEP_SCREEN_ON, "actTag:")
            this.wakeLock!!.setReferenceCounted(false)
        }
        return this.wakeLock!!
    }


    private fun unlockScreen() {
        getScreenLock().release()
    }


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

}