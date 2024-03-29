package com.example.apigato.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitHelper {

    var apiService: ApiService? = null

    fun getRetrofit(): ApiService {

        if(apiService == null){

            val retrofit = Retrofit.Builder()
                .baseUrl( "https://api.thecatapi.com/v1/" )
                .addConverterFactory( MoshiConverterFactory.create() )
                .build()
            apiService = retrofit.create(ApiService ::class.java)

        }

        return apiService !!

    }

}