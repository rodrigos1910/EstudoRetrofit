package com.example.estudoretrofit

import androidx.annotation.RestrictTo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    //Singleton
    companion object{

        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        private fun  getRetrofirInstance(): Retrofit{

            val http = OkHttpClient.Builder() //variavel para conectar a internet

            if (!::INSTANCE.isInitialized){
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }




            return INSTANCE

        }

        fun createPostService() : PostService{
            return getRetrofirInstance().create(PostService::class.java)
        }

        //funcao para retorno generico, para usar em qualquer endpoint
        fun <S> createService(abc: Class<S>) : S{
            return getRetrofirInstance().create(abc)
        }

    }
}