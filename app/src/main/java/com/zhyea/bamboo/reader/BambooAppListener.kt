package com.zhyea.bamboo.reader

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle


interface BambooAppListener {

    fun onActivityConfigurationChanged(paramActivity: Activity?, paramConfiguration: Configuration?)

    fun onActivityCreate(paramActivity: Activity?, paramBundle: Bundle?)

    fun onActivityDestroy(paramActivity: Activity?)

    fun onActivityPause(paramActivity: Activity?)

    fun onActivityResume(paramActivity: Activity?)

    fun onActivitySaveInstanceState(paramActivity: Activity?, paramBundle: Bundle?)


}