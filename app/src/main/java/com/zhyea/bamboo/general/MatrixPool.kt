package com.zhyea.bamboo.general

import android.graphics.Matrix
import com.zhyea.bamboo.reader.common.Pool

/**
 * im
 */
class MatrixPool : Pool<Matrix>() {


    override fun newInstance(): Matrix {
        return Matrix()
    }


    override fun reset(t: Matrix) {
        t.reset()
    }

}