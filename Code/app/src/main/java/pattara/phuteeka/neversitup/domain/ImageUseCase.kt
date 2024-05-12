package pattara.phuteeka.neversitup.domain

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import pattara.phuteeka.neversitup.data.repository.ImageRepository
import javax.inject.Inject

interface ImageUseCase {
    suspend fun execute(url:String):Any?
}

class ImageUseCaseImpl @Inject constructor(private val imageRepository:ImageRepository):ImageUseCase{
    override suspend fun execute(url: String): Any? {
        val response = try {
            imageRepository.getImageUrl(url)
        }catch (e:Exception){
            Log.d("DEBUG","ImageUseCaseImpl Exception : ${e}")
            null
        }
        Log.d("DEBUG","ImageUseCaseImpl code : ${response?.code()}")
        Log.d("DEBUG","ImageUseCaseImpl body : ${Gson().toJson(response?.body())}")
        Log.d("DEBUG","ImageUseCaseImpl raw : ${Gson().toJson(response?.raw())}")
        Log.d("DEBUG","ImageUseCaseImpl headers : ${Gson().toJson(response?.headers())}")
        return response?.body()
    }
}