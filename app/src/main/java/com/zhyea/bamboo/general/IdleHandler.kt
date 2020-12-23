package com.zhyea.bamboo.general

import android.os.MessageQueue

/**
 * iz
 */
class IdleHandler(private val activatedRunner: ActivatedRunner) : MessageQueue.IdleHandler {


    override fun queueIdle(): Boolean {
        return this.activatedRunner.idleRunner.idleRun()
    }

}