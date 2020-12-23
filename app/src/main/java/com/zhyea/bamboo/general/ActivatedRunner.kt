package com.zhyea.bamboo.general

import android.os.Looper

/**
 * iy
 */
class ActivatedRunner(activatedHandler: ActivatedHandler, val idleRunner: IdleRunner) : Runnable {


    override fun run() {
        Looper.myQueue().addIdleHandler(IdleHandler(this))
    }

}