package com.zhyea.bamboo.general

import android.graphics.RectF
import com.zhyea.bamboo.reader.common.Pool

class RectFPool : Pool<RectF>() {


    override fun newInstance(): RectF {
        return RectF()
    }

    override fun reset(t: RectF) {
        t.setEmpty()
    }
}