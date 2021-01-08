package com.zhyea.bamboo.general

import com.zhyea.bamboo.reader.common.Pool

/**
 * is
 */
class ArrayFourPool : Pool<Array<Float>>() {


    override fun newInstance(): Array<Float> {
        return Array(4) { 0.0F }
    }

    override fun reset(t: Array<Float>) {
        t.fill(0.0F)
    }

}