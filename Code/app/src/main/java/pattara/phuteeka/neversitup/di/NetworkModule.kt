package pattara.phuteeka.neversitup.di

import android.util.Log
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import pattara.phuteeka.neversitup.BuildConfig
import pattara.phuteeka.neversitup.data.remote.ConstantNetwork
import pattara.phuteeka.neversitup.data.remote.DepartmentAPIService
import pattara.phuteeka.neversitup.data.remote.ImageAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideDepartmentApiService(@Named(ConstantNetwork.RETROFIT_SCOPE_CORE) retrofit: Retrofit): DepartmentAPIService {
        return retrofit.create(
            DepartmentAPIService
            ::class.java
        )
    }

    @Singleton
    @Provides
    @Named(ConstantNetwork.RETROFIT_SCOPE_CORE)
    fun provideRetrofit(): Retrofit {
        val gsonBuilder = GsonBuilder().setLenient()
        val gson = gsonBuilder.serializeNulls().create()
        val gsonConverterFactory = GsonConverterFactory.create(gson)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(provideOkHttpBuilder().build())
            .build()
    }

    private fun provideOkHttpBuilder(): OkHttpClient.Builder {

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

        val okHttpBuilder = OkHttpClient.Builder()

        okHttpBuilder.apply {
            addInterceptor {
                val original: Request = it.request()
                val request: Request = original.newBuilder()
//                    .header("Content-Type", "application/json")
//                    .header("Accept", "application/json")
                    .build()
                it.proceed(request)
            }
            addInterceptor(httpLoggingInterceptor)
            readTimeout(ConstantNetwork.NETWORK_TIME_OUT, TimeUnit.SECONDS)
            connectTimeout(ConstantNetwork.NETWORK_TIME_OUT, TimeUnit.SECONDS)
        }
        return okHttpBuilder

    }

    @Singleton
    @Provides
    fun provideImageAPIService(@Named(ConstantNetwork.RETROFIT_SCOPE_IMAGE) retrofit: Retrofit): ImageAPIService {
        return retrofit.create(
            ImageAPIService
            ::class.java
        )
    }

    @Singleton
    @Provides
    @Named(ConstantNetwork.RETROFIT_SCOPE_IMAGE)
    fun provideRetrofitImageAPIService(): Retrofit {
        val gsonBuilder = GsonBuilder().setLenient()
        val gson = gsonBuilder.serializeNulls().create()
        val gsonConverterFactory = GsonConverterFactory.create(gson)


        val retrofitProvide = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(provideOkHttpBuilder().build())
            .build()
        Log.d("DEBUG provideRetrofitImageAPIService","request : ${retrofitProvide.baseUrl()}")
        return retrofitProvide
    }
}