package com.example.simplework.webservice



import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming


interface TKWebserviceInterface {

  //  @Streaming
    //@GET("thenixan-blogposts/json-streaming-data/master/one-large-file.json")
    @GET("zemirco/sf-city-lots-json/raw/master/citylots.json")
    fun getresponseData(): Call<ResponseBody>
}