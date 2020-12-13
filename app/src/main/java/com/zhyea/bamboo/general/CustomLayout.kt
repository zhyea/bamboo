package com.zhyea.bamboo.general

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * ae
 */
class CustomLayout(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) :
    FrameLayout(context, attributeSet, defStyleAttr) {


    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context) : this(context, null)

    fun setDrawCorners(drawable: Boolean) {

    }
}