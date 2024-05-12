package pattara.phuteeka.neversitup.data.remote

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ImageAPIService {
    @GET("")
    suspend fun getImage(@Url url: String): Response<Any>

}