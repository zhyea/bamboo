package com.zhyea.bamboo.general

import com.zhyea.bamboo.reader.common.Pool

class ArrayFPool : Pool<Array<Float>>() {


    override fun newInstance(): Array<Float> {
        return Array(2) { 0.0F }
    }

    override fun reset(t: Array<Float>) {
        t.fill(0.0F)
    }

}