package com.zhyea.bamboo.reader

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Looper


abstract class BambooApp() : Application() {

    private var activity:Activity? = null

    private var created:Boolean =false



    init {
        app = this
    }

    private fun checkAccess(): Boolean {
        return Thread.currentThread() === Looper.getMainLooper().thread
    }


    protected fun onActivityCreate(activity: Activity, bundle: Bundle?) {
        this.activity = activity
        this.created = true
        val localIterator: Iterator<*> = this.d.iterator()
        while (localIterator.hasNext()) (localIterator.next() as DkAppListener).onActivityCreate(
            activity,
            bundle
        )
    }


    companion object {
        var app: BambooApp? = null

        fun get(): BambooApp? {
            return app;
        }
    }


}