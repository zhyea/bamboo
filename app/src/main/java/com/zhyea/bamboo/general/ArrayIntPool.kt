package com.zhyea.bamboo.general

import com.zhyea.bamboo.reader.common.Pool

/**
 * ik
 */
class ArrayIntPool : Pool<Array<Int>>() {
    override fun newInstance(): Array<Int> {
        return Array(2) { 0 }
    }

    override fun reset(t: Array<Int>) {
        t.fill(0)
    }
}