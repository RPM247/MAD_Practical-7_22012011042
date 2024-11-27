package com.rpm24.mad_practical_7_22012011042

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL

class HttpRequest {
    fun makeServiceCall(reqUrl:String?,token:String?=null):String?{
        var response:String? = null
        try {
            val url = URL(reqUrl)
            val conn = url.openConnection() as HttpURLConnection
            if(token != null){
                conn.setRequestProperty("Authorization","Bearer $token");
                conn.setRequestProperty("Content-Type","application/json");
            }
            conn.requestMethod = "GET"
            response = convertStreamToString(BufferedInputStream(conn.inputStream))
        }catch (e:MalformedURLException){
            Log.e(TAG,"MalformedURLException" + e.message)
        }catch (e:ProtocolException){
            Log.e(TAG,"ProtocolException" + e.message)
        }catch (e:IOException){
            Log.e(TAG,"IOException" + e.message)
        }catch (e:Exception){
            Log.e(TAG,"Exception" + e.message)
        }
        return response
    }

    private fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        reader.forEachLine {
            stringBuilder.append(it).append("\n")
        }
        return stringBuilder.toString()
    }
}