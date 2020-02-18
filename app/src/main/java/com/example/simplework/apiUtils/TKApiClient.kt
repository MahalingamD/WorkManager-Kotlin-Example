package com.ers.tkenterprise.apiUtils


import com.example.simplework.activity.FourthActivity
import com.example.simplework.webservice.TKWebserviceInterface
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Gowtham Raj on 17/05/2019.
 */

object TKApiClient {

    private var retrofit: Retrofit? = null

    val interceptor = HttpLoggingInterceptor()

    val cacheSize = (20 * 1024 * 1024).toLong()
    lateinit var myCache :Cache
    lateinit var okHttpClient:OkHttpClient


    var gson = GsonBuilder()
        .setLenient()
        .create()

    //https://github.com/zemirco/sf-city-lots-json/raw/master/citylots.json

    //Call Webservice - LIVE / LOCAL
    fun getService(fourthActivity: FourthActivity): TKWebserviceInterface {

         myCache = Cache(fourthActivity.cacheDir, cacheSize)
        okHttpClient = OkHttpClient().newBuilder().cache(myCache).connectTimeout(5, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES).writeTimeout(5, TimeUnit.MINUTES).addInterceptor(interceptor).build()

        return client.create(TKWebserviceInterface::class.java)
    }

    val client: Retrofit
        get() {
            if (retrofit == null) {
                //retrofit = Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build()
                retrofit = Retrofit.Builder().baseUrl("https://github.com/").client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build()
            }
            return this.retrofit!!
        }

    //Get Base URL from Login Screen
    fun changeApiBaseUrl(newApiBaseUrl: String) {

        retrofit = Retrofit.Builder().baseUrl(newApiBaseUrl).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build()
    }
}