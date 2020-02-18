package com.example.simplework.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.simplework.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class FourthActivity : AppCompatActivity() {

    val DATE_TIME_FORMAT_FOR_COMPLAINT_MIN = "yyyy-MM-dd HH:mm:ss"

    val  DATETIME12HRS = "dd/MM/yyyy hh:mm aa"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)



       // val aDate=convertDateTime(getUTCTime(),DATETIME12HRS,DATE_TIME_FORMAT_FOR_COMPLAINT_MIN)

        Log.e("UTC  ", "" + getUTCTime())



       /* TKApiClient.getService(this).getresponseData().enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Print", t.toString())
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    val aList = response.body()?.string()
                    val jsonObject = JSONObject(aList ?: "")
                    Log.e("Print", jsonObject.toString())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        })*/


    }


    @SuppressLint("SimpleDateFormat")
    fun getUTCTime(): String {
        val time = Calendar.getInstance().time
        val outputFmt = SimpleDateFormat(DATE_TIME_FORMAT_FOR_COMPLAINT_MIN)
        outputFmt.timeZone = TimeZone.getTimeZone("UTC")
        outputFmt.isLenient = false
        return outputFmt.format(time)
    }



    @SuppressLint("SimpleDateFormat")
    fun convertDateTime(aDataValue: String, originaL_DATETIME_FORAMT: String, Target_DateFormat: String): String {

        var formattedDate = ""
        try {
            if (aDataValue.isNotEmpty()) {
                val dateString = aDataValue
                val dateFormat = SimpleDateFormat(originaL_DATETIME_FORAMT)
                val targetFormat = SimpleDateFormat(Target_DateFormat)
                dateFormat.isLenient = false
                targetFormat.isLenient = false
                val convertedDate: Date

                try {
                    convertedDate = dateFormat.parse(dateString)
                    formattedDate = targetFormat.format(convertedDate)
                } catch (e: ParseException) {
                    e.printStackTrace()
                    return aDataValue
                }
            }

            return formattedDate
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
            return formattedDate
        }
    }
}
