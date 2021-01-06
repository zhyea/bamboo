package com.zhyea.bamboo.general

import android.graphics.PointF
import com.zhyea.bamboo.reader.common.Pool

/**
 * io
 */
class PointFPool : Pool<PointF>() {


    override fun newInstance(): PointF {
        return PointF()
    }

    override fun reset(t: PointF) {
        t.x = 0.0F
        t.y = 0.0F
    }
}