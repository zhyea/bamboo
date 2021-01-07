package com.zhyea.bamboo.general

import android.graphics.Rect
import com.zhyea.bamboo.reader.common.Pool

class RectPool : Pool<Rect>() {


    override fun newInstance(): Rect {
        return Rect()
    }

    override fun reset(t: Rect) {
        t.setEmpty()
    }
}