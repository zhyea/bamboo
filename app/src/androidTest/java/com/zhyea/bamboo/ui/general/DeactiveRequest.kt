package com.zhyea.bamboo.ui.general

import android.app.Activity
import android.content.res.Configuration


class DeactiveRequest(private val activity: Activity) {


    private val chain: ArrayList<DeactiveRequest> = ArrayList(12);

    private fun dispatchActivityConfigurationChanged(config: Configuration) {
        onActivityConfigurationChanged(config)
        for (item in chain) {
            item.dispatchActivityConfigurationChanged(config)
        }
    }


    fun onActivityConfigurationChanged(act: Activity, configuration: Configuration) {
        if (act !== this.activity) {
            return
        }
        dispatchActivityConfigurationChanged(configuration)
    }

    private fun onActivityConfigurationChanged(paramConfiguration: Configuration?) {}
}
