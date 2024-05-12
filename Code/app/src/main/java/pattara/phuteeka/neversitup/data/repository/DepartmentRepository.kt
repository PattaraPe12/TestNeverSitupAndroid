package pattara.phuteeka.neversitup.data.repository

import pattara.phuteeka.neversitup.data.remote.DepartmentAPIService
import pattara.phuteeka.neversitup.data.remote.model.GetDepartmentResponse
import pattara.phuteeka.neversitup.data.remote.model.GetProductResponse
import retrofit2.Response
import javax.inject.Inject

interface DepartmentRepository {
    suspend fun fetchGetDepartment(): Response<List<GetDepartmentResponse>>?
    suspend fun fetchGetProduct(depId:String):Response<List<GetProductResponse>>?
}

class DepartmentRepositoryImpl @Inject constructor(
    private val apiService: DepartmentAPIService
) : DepartmentRepository {
    override suspend fun fetchGetDepartment(): Response<List<GetDepartmentResponse>>? {
        return runCatching {
            apiService.getDepartments()
        }.getOrNull()
    }

    override suspend fun fetchGetProduct(depId:String): Response<List<GetProductResponse>>? {
        return runCatching {
                apiService.getProducts(depId)
        }.getOrNull()
    }

}