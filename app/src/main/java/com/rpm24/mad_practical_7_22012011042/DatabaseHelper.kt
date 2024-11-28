package com.rpm24.mad_practical_7_22012011042

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context:Context?):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME="testadnroid"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(PersonDbTableData.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + PersonDbTableData.TABLE_NAME)
        onCreate(db)
    }

    fun insertPerson(name: String, emailId: String, phoneNo: String, address: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(PersonDbTableData.COLUMN_PERSON_NAME, name)
            put(PersonDbTableData.COLUMN_PERSON_EMAIL_ID, emailId)
            put(PersonDbTableData.COLUMN_PERSON_PHONE_NO, phoneNo)
            put(PersonDbTableData.COLUMN_PERSON_ADDRESS, address)
        }
        return db.insert(PersonDbTableData.TABLE_NAME, null, values)
    }

    fun getAllPersons(): List<Person> {
        val db = readableDatabase
        val cursor = db.query(
            PersonDbTableData.TABLE_NAME,
            null,  // Select all columns
            null,  // No WHERE clause
            null,  // No WHERE arguments
            null,  // No group by
            null,  // No having
            null   // No order by
        )

        val personList = mutableListOf<Person>()

        with(cursor) {
            while (moveToNext()) {
                val person = Person(
                    id = getString(getColumnIndexOrThrow(PersonDbTableData.COLUMN_ID)),
                    name = getString(getColumnIndexOrThrow(PersonDbTableData.COLUMN_PERSON_NAME)),
                    emailId = getString(getColumnIndexOrThrow(PersonDbTableData.COLUMN_PERSON_EMAIL_ID)),
                    phoneNo = getString(getColumnIndexOrThrow(PersonDbTableData.COLUMN_PERSON_PHONE_NO)),
                    address = getString(getColumnIndexOrThrow(PersonDbTableData.COLUMN_PERSON_ADDRESS)),
                    latitude = getDoubleOrNull(getColumnIndexOrThrow(PersonDbTableData.COLUMN_PERSON_GPS_LAT)),
                    longitude = getDoubleOrNull(getColumnIndexOrThrow(PersonDbTableData.COLUMN_PERSON_GPS_LONG))
                )
                personList.add(person)
            }
            close()
        }

        return personList
    }


    // Helper function for nullable columns
    private fun Cursor.getDoubleOrNull(columnIndex: Int): Double? =
        if (isNull(columnIndex)) null else getDouble(columnIndex)

}