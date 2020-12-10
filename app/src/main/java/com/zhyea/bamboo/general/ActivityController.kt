package com.zhyea.bamboo.general

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_MENU
import java.util.*
import kotlin.collections.ArrayList


/**
 * iw
 * @author robin
 */
class ActivityController(private val activity: Activity) {


    /**
     * e
     */
    private val subControllers: ArrayList<ActivityController> = ArrayList(12);

    /**
     * f
     */
    private val history: LinkedList<ActivityController> = LinkedList();

    /**
     * i
     */
    private var menuShowController: ActivityController? = null

    /**
     * d
     */
    private var parent: RequestHandler? = null

    /**
     * 分发Activity配置改变事件
     */
    private fun dispatchActivityConfigurationChanged(config: Configuration) {
        onActivityConfigurationChanged(config)
        for (item in subControllers) {
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
        for (item in subControllers) {
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
        for (item in subControllers) {
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
        for (item in subControllers) {
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
        for (item in subControllers) {
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
        for (item in subControllers) {
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
    private fun dispatchActivitySaveInstanceState(bundle: Bundle) {
        onActivitySaveInstanceState(bundle)
        for (item in subControllers) {
            item.dispatchActivitySaveInstanceState(bundle)
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


    /**
     * 分发KeyDown事件
     */
    private fun dispatchKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KEYCODE_MENU) {
            return true
        }
        val itr: ListIterator<ActivityController> = this.history.listIterator(this.history.size)
        while (itr.hasPrevious()) {
            val tmp = itr.previous()
            if (tmp.dispatchKeyDown(keyCode, event)) {
                return true
            }
        }
        return onKeyDown(keyCode, event)
    }

    /**
     * KeyDown事件
     */
    private fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return false
    }


    /**
     * 分发KeyUp事件
     */
    private fun dispatchKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode != KEYCODE_MENU) {
            val itr: ListIterator<ActivityController> = this.history.listIterator(this.history.size)
            while (itr.hasPrevious()) {
                val tmp = itr.previous()
                tmp.dispatchKeyUp(keyCode, event)
            }
            return onKeyUp(keyCode, event)
        } else if (isMenuShowing()) {
            return doHideMenu()
        } else {
            return doShowMenu()
        }
    }

    /**
     * KeyUp事件
     */
    private fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return false
    }

    /**
     * 判断是否是MenuShowing状态
     */
    private fun isMenuShowing(): Boolean {
        if (this.menuShowController === this) {
            return onCheckMenuShowing()
        } else {
            return this.menuShowController!!.isMenuShowing()
        }
    }

    /**
     * MenuShowing事件
     */
    private fun onCheckMenuShowing(): Boolean {
        return true
    }


    /**
     * 执行ShowMenu事件
     */
    private fun doShowMenu(): Boolean {
        val itr: ListIterator<ActivityController> = this.history.listIterator(this.history.size)
        while (itr.hasPrevious()) {
            val tmp = itr.previous()
            if (tmp.doShowMenu()) {
                this.menuShowController = tmp
                return true
            }
        }
        if (onShowMenu()) {
            this.menuShowController = this
            return true
        }
        return false
    }

    /**
     * ShowMenu事件
     */
    private fun onShowMenu(): Boolean {
        return false
    }

    /**
     * 执行HideMenu事件
     */
    private fun doHideMenu(): Boolean {
        if (this.menuShowController == null) {
            throw AssertionError()
        }
        if (this.menuShowController !== this) {
            return this.menuShowController?.doHideMenu() ?: false
        }
        return onHideMenu()
    }

    /**
     * HideMenu
     */
    private fun onHideMenu(): Boolean {
        return false
    }


    fun activate(controller: ActivityController) {
        this.f.remove(controller)
        this.f.add(controller)
        if (!this.m || controller.isActive()) return
        controller.gotoActive()
    }

    fun addSubController(sub: ActivityController) {
        if (this.subControllers.contains(sub)) {
            return
        }
        this.subControllers.add(sub)
        sub.setParent(this.parent)
    }


    fun setParent(parent: RequestHandler) {
        if (this.h !== paramjc) {
            this.h = parent
            if (this.h == null) onDetachFromParent()
        } else {
            return
        }
        onAttachToParent()
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
