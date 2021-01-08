package com.zhyea.bamboo.general

import android.graphics.*
import com.zhyea.bamboo.reader.common.Pool

object PoolManager {


    /**
     * b
     */
    private val paintPool: Pool<Paint> = PaintPool()

    /**
     * c
     */
    private val matrixPool: Pool<Matrix> = MatrixPool()

    /**
     * d
     */
    private val pointPool: Pool<Point> = PointPool()

    /**
     * e
     */
    private val pointFPool: Pool<PointF> = PointFPool()

    /**
     * f
     */
    private val rectPool: Pool<Rect> = RectPool()

    /**
     * g
     */
    private val rectFPool: Pool<RectF> = RectFPool()

    /**
     * h
     */
    private val arrayTwoPool: Pool<Array<Float>> = ArrayTwoPool()

    /**
     * i
     */
    private val arrayFourPool: Pool<Array<Float>> = ArrayFourPool()

    /**
     * j
     */
    private val arrayNinePool: Pool<Array<Float>> = ArrayNinePool()

    /**
     * k
     */
    private val arrayIntPool: Pool<Array<Int>> = ArrayIntPool()


}