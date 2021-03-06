package com.zhyea.bamboo.common.cache

import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import java.io.File
import java.util.*
import kotlin.collections.LinkedHashMap

class AsyncCache(dbPath: String?) {

    /**
     * h
     */
    private var db: SQLiteDatabase? = null

    /**
     * c
     */
    private var executeThread: Thread? = null

    /**
     * b
     */
    private val map: LinkedHashMap<String, RecordList> = LinkedHashMap(8)


    init {
        var tmpDb: SQLiteDatabase? = null
        while (true) {
            try {
                tmpDb = SQLiteDatabase.openOrCreateDatabase(File(Uri.parse(dbPath).path, "index.db"), null)
                tmpDb!!.beginTransaction();
                try {
                    if (tmpDb.version < 1)
                        tmpDb.execSQL(
                            String.format(
                                "CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s INTEGER, %s BLOB)",
                                arrayOf("indices", "index_id", "hash_code", "key")
                            )
                        )
                    tmpDb.version = 1;
                    tmpDb.setTransactionSuccessful();
                    tmpDb.endTransaction();
                    this.db = tmpDb;
                    this.executeThread = Thread(AsyncCacheRunner(this));
                    this.executeThread!!.start();
                    break
                } finally {
                    tmpDb.endTransaction();
                }
            } catch (e: Exception) {
                e.printStackTrace()
                continue
            }
            this.db = null
        }
    }


    constructor() : this(null)


    private fun a(paramBoolean: Boolean): ListIterator<*>? {

        val j = b()
        val list = ArrayList<ListIterator<Any>>(this.map.size)
        for (recordList in this.map.values) {
            val k: Int = recordList.size
            val itr = recordList.listIterator(k)
            list.add(itr)
        }

        return b(this, j, paramBoolean, list)
    }


    private fun b(): Int {
        val values = this.map.values
        var total = 0
        for (list in values) {
            total += list.size
        }
        return total;
    }

    fun a(paramInt: Int) {
        this.g = paramInt
    }


    enum class DataState(val code: Int) {
        NULL(0),
        UNFILLED(1),
        FILLED(2),
        EMPTY(4), ;
    }


    class RecordList : LinkedList<Any>() {

    }

}



