package com.rpm24.mad_practical_7_22012011042

import org.json.JSONObject
import java.io.Serializable

data class Person(
    val id: String,
    val name: String,
    val emailId: String,
    val phoneNo: String,
    val address: String,
    val latitude: Double?,
    val longitude: Double?
) : Serializable {
    companion object {
        fun fromJson(jsonObject: JSONObject): Person {
            val id = jsonObject.getString("id")
            val emailId = jsonObject.getString("email")
            val phoneNo = jsonObject.getString("phone")
            val profileObject = jsonObject.getJSONObject("profile")
            val name = profileObject.getString("name")
            val address = profileObject.getString("address")
            val locationObject = profileObject.getJSONObject("location")
            val latitude = locationObject.getDouble("lat")
            val longitude = locationObject.getDouble("long")
            return Person(
                id = id,
                name = name,
                emailId = emailId,
                phoneNo = phoneNo,
                address = address,
                latitude = latitude,
                longitude = longitude
            )
        }
    }
}