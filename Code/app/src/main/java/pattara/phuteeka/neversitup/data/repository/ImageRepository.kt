package pattara.phuteeka.neversitup.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import pattara.phuteeka.neversitup.data.remote.ImageAPIService
import retrofit2.Response
import java.util.Objects
import javax.inject.Inject

interface ImageRepository {
    suspend fun getImageUrl(url:String): Response<Any>?
}

class ImageRepositoryImpl @Inject constructor(private val imageAPIService:ImageAPIService):ImageRepository{
    override suspend fun getImageUrl(url: String): Response<Any>? {

        val response = try {
            imageAPIService.getImage(url)
        }catch (e:Exception){
            Log.d("DEBUG","ImageRepository Exception : ${e}")
            null
        }
//        val response =  runCatching {
//            imageAPIService.getImage(url)
//        }.getOrNull()
//                val response = imageAPIService.getImage(url)
        Log.d("DEBUG","ImageRepository url : ${response?.code()}")
        Log.d("DEBUG","ImageRepository url : ${Gson().toJson(response?.headers())}")
        Log.d("DEBUG","ImageRepository url : ${Gson().toJson(response?.raw())}")
        return response
    }
}