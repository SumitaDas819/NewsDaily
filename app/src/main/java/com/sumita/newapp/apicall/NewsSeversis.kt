package com.sumita.newapp.apicall

import com.sumita.newapp.ApiResponse.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&apiKey=b461fea21cc242458825d1be951ebe1c
//https://newsapi.org/v2/everything?domains=wsj.com&apiKey=b461fea21cc242458825d1be951ebe1c
const val BASE_URL="https://newsapi.org/"
const val API_KEY="b461fea21cc242458825d1be951ebe1c"
 interface NewsInterface{
     //define the method to call the endpoint of api
  @GET("v2/top-headlines?apiKey=$API_KEY")//@GET is to tell the it is a get request
     //values tels that which end point to call /v2/top-headlines?(endpoint)
  fun getHeadlines(@Query("country")country:String, @Query("page") page:Int) : Call<News>// function to get the headlines
  //In Retrofit, the @Query parameter is used to include query parameters in a URL for GET requests.
 // It allows you to pass specific information or data to the server as part of the request.
 //now it will create a url that is-- https://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=in&page=1

  /*The Call<News> part is the return type of the function. It indicates that the Retrofit request will return a Call object that wraps a response of type News.

The Call object represents a single request/response interaction with a server, and it allows you to enqueue the request, cancel it, or check its status.
 The Call object is part of the Retrofit API and is used to handle the network communication.*/
 }
object NewsSeversis {
    /*
    In Kotlin, you can create a singleton object using the object keyword. An object declaration creates
     a single instance of a class, which is accessible throughout your application.
      The object declaration is similar to creating a class and then creating a single instance
      of that class, but it combines both steps into a single concise declaration.
      //about retrofit object
      The Retrofit class is the main entry point of the Retrofit library.
       It is responsible for creating a REST client that allows you to interact
       with web services by making HTTP requests and handling responses.

       //!important
       Retrofit.Builder() method is used to create a new instance of the Retrofit class.
        The baseUrl() method is used to specify the base URL of the web service,
         and the addConverterFactory() method is used to specify the converter factory,
          which is responsible for converting the JSON response into a Kotlin object.
    */
    val newsIntence: NewsInterface
    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        /*
        GsonConverterFactory is a converter factory provided by the
        Retrofit library that helps in converting JSON data to Java objects (serialization)
        and Java objects to JSON data (deserialization) using the Gson library.
        When you call GsonConverterFactory.create(), it creates an instance of GsonConverterFactory
         that is capable of serializing and deserializing JSON data using Gson. This converter factory
         can be added to the Retrofit instance using the addConverterFactory() method.
        */
        newsIntence =retrofit.create(NewsInterface::class.java)//to tell the retro fit object to implement the interface
    }

}
