package com.example.problem2.sqllite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.problem2.network.model.PublishPostReq
import java.util.ArrayList

class DbManager(context: Context) {
    companion object{
        const val EMAIL = "email"
        const val PUBLISHER_TYPE = "publisher_type"
        const val IS_JOKE = "is_joke"
        const val DESCRIPTION = "description"

    }
    private val dbName = "Problem2"
    private val dbTable = "Posts"

    private val dbVersion = 1

    private val CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS $dbTable " +
            "($EMAIL TEXT ," +
            "$PUBLISHER_TYPE TEXT ," +
            "$IS_JOKE BOOLEAN ," +
            "$DESCRIPTION TEXT " +
            ");"

    private var db: SQLiteDatabase? = null

    init {
        val dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
    }

  fun insert(values: ContentValues): Long {

        val ID = db!!.insert(dbTable, "", values)
        return ID

    }

    fun queryAll():ArrayList<PublishPostReq> {

        val cursor= db!!.rawQuery("select * from $dbTable", null)
        val retArray = ArrayList<PublishPostReq>()
        while (cursor.moveToNext()) {
            val email = cursor.getString(cursor.getColumnIndex(EMAIL))
            val publisherType = cursor.getString(cursor.getColumnIndex(PUBLISHER_TYPE))
            val isJoke = cursor.getInt(cursor.getColumnIndex(IS_JOKE))
            val description = cursor.getString(cursor.getColumnIndex(DESCRIPTION))
            retArray.add(PublishPostReq(email,publisherType,isJoke,description))
        }
        return retArray;
    }




    inner class DatabaseHelper
    (context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {

    override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(CREATE_TABLE_SQL)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

            db!!.execSQL("Drop table IF EXISTS $dbTable")
            onCreate(db)

        }
    }
}