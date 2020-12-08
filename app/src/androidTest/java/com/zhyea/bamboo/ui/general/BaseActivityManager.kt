package com.zhyea.bamboo.ui.general

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle


/**
 * iw
 * @author robin
 */
class BaseActivityManager(private val activity: Activity) {


    private val chain: ArrayList<BaseActivityManager> = ArrayList(12);

    /**
     * 分发Activity配置改变事件
     */
    private fun dispatchActivityConfigurationChanged(config: Configuration) {
        onActivityConfigurationChanged(config)
        for (item in chain) {
            item.dispatchActivityConfigurationChanged(config)
        }
    }


    /**
     * Activity配置改变
     */
    fun onActivityConfigurationChanged(act: Activity, configuration: Configuration) {
        if (act !== this.activity) {
            return
        }
        dispatchActivityConfigurationChanged(configuration)
    }

    /**
     * Activity配置改变
     */
    private fun onActivityConfigurationChanged(config: Configuration) {
    }


    /**
     * 分发Activity创建事件
     */
    private fun dispatchActivityCreate(bundle: Bundle) {
        onActivityCreate(bundle)
        for (item in chain) {
            item.dispatchActivityCreate(bundle)
        }
    }

    /**
     * activity创建
     */
    fun onActivityCreate(act: Activity, bundle: Bundle) {
        if (act !== this.activity) {
            return
        }
        dispatchActivityCreate(bundle)
    }

    /**
     * activity创建
     */
    private fun onActivityCreate(paramBundle: Bundle) {
    }


    /**
     * 分发Activity销毁事件
     */
    private fun dispatchActivityDestroy() {
        onActivityDestroy()
        for (item in chain) {
            item.dispatchActivityDestroy()
        }
    }

    /**
     * Activity销毁
     */
    fun onActivityDestroy(paramActivity: Activity) {
        if (paramActivity !== this.activity) {
            return
        }
        dispatchActivityDestroy()
    }

    /**
     * Activity销毁
     */
    private fun onActivityDestroy() {
    }


    /**
     * 分发Activity Pause事件
     */
    private fun dispatchActivityPause() {
        onActivityPause()
        for (item in chain) {
            item.dispatchActivityPause()
        }
    }

    /**
     * Activity Pause
     */
    fun onActivityPause(paramActivity: Activity) {
        if (paramActivity !== this.activity) {
            return
        }
        dispatchActivityPause()
        gotoDeactive()
    }

    /**
     * Activity Pause
     */
    private fun onActivityPause() {
    }


    /**
     * 分发Activity Result
     */
    private fun dispatchActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        onActivityResult(requestCode, resultCode, data)
        for (item in chain) {
            item.dispatchActivityResult(requestCode, resultCode, data)
        }
    }

    /**
     * Activity Result
     */
    fun onActivityResult(act: Activity, requestCode: Int, resultCode: Int, data: Intent) {
        if (act !== this.activity) {
            return
        }
        dispatchActivityResult(requestCode, resultCode, data)
    }

    /**
     * Activity Result
     */
    private fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    }


    private fun dispatchActivityResume() {
        onActivityResume()
        for (item in chain) {
            item.dispatchActivityResume()
        }
    }

    private fun onActivityResume() {}

    fun onActivityResume(act: Activity) {
        if (act !== this.activity){ return}
        dispatchActivityResume()
        gotoActive()
    }


    private fun gotoDeactive() {
/*        if (!b && !this.m) throw AssertionError()
        val localListIterator: ListIterator<*> = this.f.listIterator(this.f.size())
        while (localListIterator.hasPrevious()) (localListIterator.previous() as iw).gotoDeactive()
        this.m = false
        onDeactive()*/
    }


    private fun gotoActive() {
/*        if (!b && this.m) throw AssertionError()
        this.m = true
        onActive(this.n)
        this.n = false
        val localIterator: Iterator<*> = this.f.iterator()
        while (localIterator.hasNext()) {
            val localiw: iw = localIterator.next() as iw
            if (!b && localiw.isActive()) throw AssertionError()
            localiw.gotoActive()
        }
        while (!this.g.isEmpty()) {
            (this.g.getFirst() as Runnable).run()
            this.g.removeFirst()
        }*/
    }


}
