package com.zhyea.bamboo.general

import android.graphics.Point
import com.zhyea.bamboo.reader.common.Pool

/**
 * in
 */
class PointPool : Pool<Point>() {


    override fun newInstance(): Point {
        return Point()
    }

    override fun reset(t: Point) {
        t.x = 0
        t.y = 0
    }
}