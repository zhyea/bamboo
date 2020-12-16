package com.zhyea.bamboo.reader

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle


interface BambooAppListener {

    fun onActivityConfigurationChanged(activity: Activity, config: Configuration)

    fun onActivityCreate(activity: Activity, bundle: Bundle)

    fun onActivityDestroy(activity: Activity)

    fun onActivityPause(activity: Activity)

    fun onActivityResume(activity: Activity)

    fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle)


}