package com.zhyea.bamboo.reader

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import android.os.Bundle
import android.os.Looper
import java.util.*


abstract class BambooApp() : Application() {

    private var activity: Activity? = null

    private var isActivityRunning: Boolean = false

    private val listeners: LinkedList<BambooAppListener> = LinkedList()


    init {
        app = this
    }

    private fun checkAccess(): Boolean {
        return Thread.currentThread() === Looper.getMainLooper().thread
    }


    fun addListener(listener: BambooAppListener) {
        check(checkAccess())
        this.listeners.add(listener)
    }


    fun removeAppListener(listener: BambooAppListener) {
        check(!checkAccess())
        this.listeners.remove(listener)
    }


    fun getCurrentActivity(): Activity? {
        check(!checkAccess())
        return this.activity
    }


    fun isActivityRunning(): Boolean {
        return this.isActivityRunning
    }


    protected fun onActivityConfigurationChanged(activity: Activity, config: Configuration) {
        for (listener in listeners) {
            listener.onActivityConfigurationChanged(activity, config)
        }
    }


    protected fun onActivityCreate(activity: Activity, bundle: Bundle) {
        this.activity = activity
        this.isActivityRunning = true
        for (listener in listeners) {
            listener.onActivityCreate(activity, bundle)
        }
    }


    protected fun onActivityDestroy(activity: Activity) {
        this.isActivityRunning = false
        for (listener in listeners) {
            listener.onActivityDestroy(activity)
        }
    }


    protected fun onActivityPause(activity: Activity) {
        for (listener in listeners) {
            listener.onActivityPause(activity)
        }
    }


    protected fun onActivityResume(activity: Activity) {
        this.activity = activity
        for (listener in listeners) {
            listener.onActivityResume(activity)
        }
    }


    protected fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        for (listener in listeners) {
            listener.onActivitySaveInstanceState(activity, bundle)
        }
    }


    companion object {

        var app: BambooApp? = null

        fun get(): BambooApp? {
            return app;
        }
    }


}