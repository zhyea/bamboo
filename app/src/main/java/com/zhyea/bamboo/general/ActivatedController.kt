package com.zhyea.bamboo.general

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_MENU
import android.view.View
import android.view.ViewGroup
import java.util.*
import kotlin.collections.ArrayList


/**
 * iw
 * @author robin
 */
class ActivatedController(private val activity: Activity) {


    /**
     * e
     */
    private val subControllers: ArrayList<ActivatedController> = ArrayList(12);

    /**
     * f
     */
    private val history: LinkedList<ActivatedController> = LinkedList();

    /**
     * i
     */
    private var menuShowController: ActivatedController? = null

    /**
     * d
     */
    private var parent: IController = null

    /**
     * m
     */
    private var isActive: Boolean = false

    /**
     * n
     */
    private var isFirstActive = true

    /**
     * h
     */
    private var subControllerParent: IController? = null

    /**
     * j
     */
    private var dialog: Dialog? = null

    /**
     * k
     */
    private var viewGroup: ViewGroup? = null

    /**
     * l
     */
    private var view: View? = null


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
        val itr: ListIterator<ActivatedController> = this.history.listIterator(this.history.size)
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
            val itr: ListIterator<ActivatedController> =
                this.history.listIterator(this.history.size)
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
        val itr: ListIterator<ActivatedController> = this.history.listIterator(this.history.size)
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

    /**
     * 与Parent剥离
     */
    private fun onDetachFromParent() {
        //
    }

    /**
     * 关联parent
     */
    private fun onAttachToParent() {
        //
    }

    /**
     * Deactive时
     */
    private fun onDeactive() {
        //
    }

    /**
     * Active时
     */
    private fun onActive(paramBoolean: Boolean) {
        //
    }


    /**
     * 执行activate
     */
    fun activate(controller: ActivatedController) {
        this.history.remove(controller)
        this.history.add(controller)
        if (!this.isActive || controller.isActive()) {
            return
        }
        controller.gotoActive()
    }

    /**
     * 添加sub controller
     */
    fun addSubController(sub: ActivatedController) {
        if (this.subControllers.contains(sub)) {
            return
        }
        this.subControllers.add(sub)
        sub.setParent(this.parent)
    }


    /**
     * 设置parent controller
     */
    private fun setParent(parent: IController?) {
        if (this.subControllerParent !== parent) {
            this.subControllerParent = parent
            if (this.subControllerParent == null) {
                onDetachFromParent()
            }
        } else {
            return
        }
        onAttachToParent()
    }

    /**
     * 返回是否活跃
     */
    private fun isActive(): Boolean {
        return this.isActive
    }


    /**
     * 执行deactivate
     */
    private fun gotoDeactive() {
        if (!this.isActive) {
            throw AssertionError()
        }
        val itr: ListIterator<ActivatedController> = this.history.listIterator(this.history.size)
        while (itr.hasPrevious()) {
            val tmp = itr.previous()
            tmp.gotoDeactive()
        }
        this.isActive = false
        onDeactive()
    }


    /**
     * 执行active
     */
    private fun gotoActive() {
        if (this.isActive) {
            throw AssertionError()
        }
        this.isActive = true
        onActive(this.isFirstActive)
        this.isFirstActive = false

        for (ac in history) {
            if (!ac.isActive()) {
                ac.gotoActive()
            }
        }

        while (!this.g.isEmpty()) {
            (this.g.getFirst() as Runnable).run()
            this.g.removeFirst()
        }
    }


    /**
     * 执行deactive
     */
    private fun deactivate(controller: ActivatedController) {
        if (!controller.isActive()) {
            throw AssertionError()
        }
        this.history.remove(controller)
        if (!this.isActive && controller.isActive()) {
            throw AssertionError()
        }
        if (!controller.isActive()) {
            return
        }
        controller.gotoDeactive()
    }

    /**
     * 获取popup组件的数量
     */
    private fun getPopupCount(): Int {
        if (this.dialog == null) {
            return 0
        }
        if (this.viewGroup == null) {
            throw AssertionError()
        }
        return this.viewGroup!!.childCount
    }


    /**
     * 关掉全部popup组件
     */
    fun dismissAllPopups() {
        while (getPopupCount() > 0) {
            dismissTopPopup()
        }
    }


    /**
     * 移除sub controller
     */
    private fun removeSubController(sub: ActivatedController) {
        if (!this.subControllers.contains(sub)) {
            return
        }
        if (sub.isActive()) {
            deactivate(sub)
        }
        this.subControllers.remove(sub)
        sub.setParent(null)
    }

    /**
     * 获取content view
     */
    private fun getContentView(): View {
        return this.view!!
    }

    /**
     * 判断是否是popup controller
     */
    private fun isPopupController(controller: ActivatedController): Boolean {
        if (this.dialog == null) {
            return false
        }
        if (this.viewGroup == null) {
            throw AssertionError()
        }
        while (true) {
            for (i1 in 0 until this.viewGroup!!.childCount) {
                val tmp = this.viewGroup!!.getChildAt(i1)
                if (tmp === controller.getContentView()) {
                    return true
                }
            }
        }
    }


    /**
     * 关闭全部popup组件
     */
    private fun dismissPopup(controller: ActivatedController): Boolean {
        var popup = 1;
        if (!isPopupController(controller)) {
            popup = 0
            return popup > 0
        }
        do {
            if (this.dialog == null) throw AssertionError()
            if (this.viewGroup == null) throw AssertionError()
            val localView: View = controller.getContentView()
            deactivate(controller)
            this.viewGroup!!.removeView(localView)
            removeSubController(controller)
        } while (!this.dialog!!.isShowing || this.viewGroup!!.childCount >= popup)
        this.dialog!!.dismiss()
        return popup > 0
    }


    private fun dismissTopPopup(): Boolean {
        if (this.dialog == null) {
            return false
        }
        do {
            if (this.viewGroup == null) throw AssertionError()
        } while (this.viewGroup!!.childCount < 1)

        val lastView: View = this.viewGroup!!.getChildAt(this.viewGroup!!.childCount - 1)
        var controller: ActivatedController? = null
        for (c in this.history) {
            controller = c
            if (c.getContentView() === lastView) {
                break
            }
        }

        while (true) {
            if (controller == null) {
                throw AssertionError()
            }
            dismissPopup(controller)
            return true
        }
    }


    fun findSubController(view: View): ActivatedController? {
        for (controller in this.subControllers) {
            if (view === controller.getContentView()) {
                return controller;
            }
        }
        return null
    }

    fun findViewById(paramInt: Int): View {
        val localView1: View = this.l
        var localView2: View? = null
        if (localView1 != null) localView2 = this.l.findViewById(paramInt)
        if (localView2 == null) localView2 = this.c.findViewById(paramInt)
        return localView2
    }

}
