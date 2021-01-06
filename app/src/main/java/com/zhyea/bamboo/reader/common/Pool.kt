package com.zhyea.bamboo.reader.common

import java.util.*

abstract class Pool<T> {


    private val pool: ArrayList<T> = ArrayList(8)

    private var cursor: Int = 0;

    private var total: Int = 0;

    open fun add(): T {
        if (this.cursor >= this.total - 1) {
            this.pool.add(newInstance())
            this.total += 1
        }
        val list: List<T> = this.pool
        val i: Int = this.cursor
        this.cursor = i + 1
        return list[i]
    }


    open fun remove(t: T) {
        var idx: Int = this.total - 1
        while (idx >= 0 && this.pool[idx] !== t) {
            idx--
        }
        if (idx < 0) {
            throw AssertionError()
        }
        if (idx < 0) {
            return
        }

        reset(t)
        if (idx != cursor - 1) {
            val tmp: T = this.pool[idx]
            this.pool[idx] = this.pool[cursor - 1]
            this.pool[cursor - 1] = tmp
        }
        cursor += -1
    }


    abstract fun newInstance(): T


    abstract fun reset(t: T)

}