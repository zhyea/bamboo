package com.zhyea.bamboo.general

import com.zhyea.bamboo.reader.common.Pool

/**
 * it
 */
class ArrayNinePool : Pool<Array<Float>>() {


    override fun newInstance(): Array<Float> {
        return Array(9) { 0.0F }
    }

    override fun reset(t: Array<Float>) {
        t.fill(0.0F)
    }

}