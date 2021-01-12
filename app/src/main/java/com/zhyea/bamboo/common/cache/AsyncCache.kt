package com.zhyea.bamboo.common.cache

import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import java.io.File

class AsyncCache(dbPath: String?) {

    private var db: SQLiteDatabase? = null

    private var executeThread: Thread? = null

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


    private fun b(): Int {
        val localIterator: Iterator<*> = this.b.values().iterator()
        var j = 0
        while (localIterator.hasNext()) j += (localIterator.next() as AsyncCache.RecordList).size()
        return j
    }

    fun a(paramInt: Int) {
        this.g = paramInt
    }



}



