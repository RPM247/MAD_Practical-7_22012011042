package com.rpm24.mad_practical_7_22012011042

class PersonDbTableData {
    companion object{
        const val TABLE_NAME = "persons"

        const val COLUMN_ID="id"
        const val COLUMN_PERSON_NAME="name"
        const val COLUMN_PERSON_EMAIL_ID="email_id"
        const val COLUMN_PERSON_PHONE_NO="phone_no"
        const val COLUMN_PERSON_ADDRESS="address"
        const val COLUMN_PERSON_GPS_LAT="lat"
        const val COLUMN_PERSON_GPS_LONG="long"

        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PERSON_NAME + " TEXT,"
                + COLUMN_PERSON_EMAIL_ID + " TEXT,"
                + COLUMN_PERSON_PHONE_NO + " TEXT,"
                + COLUMN_PERSON_ADDRESS + " TEXT,"
                + COLUMN_PERSON_GPS_LAT + " TEXT DEFAULT NULL,"
                + COLUMN_PERSON_GPS_LONG + " TEXT DEFAULT NULL"
                + ")")
    }
}