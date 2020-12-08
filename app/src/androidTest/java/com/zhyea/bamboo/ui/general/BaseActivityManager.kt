package com.zhyea.bamboo.ui.general

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import java.util.*
import kotlin.collections.ArrayList


/**
 * iw
 * @author robin
 */
class BaseActivityManager(private val activity: Activity) {


    private val chain: ArrayList<BaseActivityManager> = ArrayList(12);

    private val history: LinkedList<BaseActivityManager> = LinkedList();

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
        //
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
    private fun onActivityCreate(bundle: Bundle) {
        //
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
        //
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
        //
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
        //
    }


    /**
     * 分发Activity Resume
     */
    private fun dispatchActivityResume() {
        onActivityResume()
        for (item in chain) {
            item.dispatchActivityResume()
        }
    }

    /**
     * Activity Resume
     */
    fun onActivityResume(act: Activity) {
        if (act !== this.activity) {
            return
        }
        dispatchActivityResume()
        gotoActive()
    }

    /**
     * Activity Resume
     */
    private fun onActivityResume() {
        //
    }


    /**
     * 分发Activity保存实例状态事件
     */
    private fun dispatchActivitySaveInstanceState(paramBundle: Bundle) {
        onActivitySaveInstanceState(paramBundle)
        for (item in chain) {
            item.dispatchActivitySaveInstanceState()
        }
    }

    /**
     * Activity保存实例状态
     */
    fun onActivitySaveInstanceState(act: Activity, bundle: Bundle) {
        if (act !== this.activity) {
            return
        }
        dispatchActivitySaveInstanceState(bundle)
    }

    /**
     * Activity保存实例状态
     */
    private fun onActivitySaveInstanceState(bundle: Bundle) {
        //
    }


    private fun dispatchKeyDown(eventCode: Int, event: KeyEvent): Boolean {
        if (eventCode == 82) {
            return true
        }
        val itr: ListIterator<BaseActivityManager> =
            this.history.listIterator(this.history.size)
        while (itr.hasPrevious()) {
            val tmp = itr.previous()
            if (tmp.dispatchKeyDown(eventCode, event)) {
                return true
            }
        }
        return onKeyDown(eventCode, event)
    }


    private fun onKeyDown(eventCode: Int, event: KeyEvent): Boolean {
        return false
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
