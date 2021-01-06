package com.zhyea.bamboo.general

import android.graphics.Paint
import com.zhyea.bamboo.reader.common.Pool

/**
 * ij
 */
class PaintPool : Pool<Paint>() {

    override fun newInstance(): Paint {
        return Paint()
    }

    override fun reset(t: Paint) {
        t.reset()
    }
}