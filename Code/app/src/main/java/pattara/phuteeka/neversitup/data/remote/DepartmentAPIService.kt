package pattara.phuteeka.neversitup.data.remote

import kotlinx.coroutines.flow.Flow
import pattara.phuteeka.neversitup.data.remote.model.GetDepartmentResponse
import pattara.phuteeka.neversitup.data.remote.model.GetProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DepartmentAPIService {
    @GET("api/v1/departments")
    suspend fun getDepartments(): Response<List<GetDepartmentResponse>>

    @GET("api/v1/departments/{departmentId}/products")
    suspend fun getProducts(
        @Path("departmentId") departmentId: String
    ): Response<List<GetProductResponse>>
}
//
//class DepartmentAPIServiceImpl @Inject constructor() : DepartmentAPIService {
//    override suspend fun getDepartments(): Response<List<GetProductResponse>> {
//
//    }
//
//    override suspend fun getProducts(departmentId: String): Response<List<GetProductResponse>> {
//
//    }
//
//}
//
//@Module
//@InstallIn(ActivityComponent::class)
//abstract class AnalyticsModule {
//
//    @Binds
//    abstract fun bindAnalyticsService(
//        analyticsServiceImpl: AnalyticsServiceImpl
//    ): AnalyticsService
//}