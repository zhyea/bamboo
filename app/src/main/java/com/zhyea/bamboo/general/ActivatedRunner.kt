package com.zhyea.bamboo.general

import android.os.Looper

/**
 * iy
 */
class ActivatedRunner(activatedHandler: ContentController, val idleRunner: IdleRunner) : Runnable {


    override fun run() {
        Looper.myQueue().addIdleHandler(IdleHandler(this))
    }

}