package pattara.phuteeka.neversitup.domain

import pattara.phuteeka.neversitup.data.repository.DepartmentRepository
import pattara.phuteeka.neversitup.data.remote.model.GetDepartmentResponse
import pattara.phuteeka.neversitup.data.remote.model.GetProductResponse
import javax.inject.Inject

interface DepartmentUseCase {
    suspend fun execute(): List<GetDepartmentResponse>?
    suspend fun execute(depId: String): List<GetProductResponse>?
}

class DepartmentUseCaseImpl @Inject constructor(
    private val repository: DepartmentRepository
) : DepartmentUseCase {
    override suspend fun execute(): List<GetDepartmentResponse>? {
        return repository.fetchGetDepartment()?.body()?: arrayListOf()
    }

    override suspend fun execute(depId: String): List<GetProductResponse>? {
        return repository.fetchGetProduct(depId)?.body()?: arrayListOf()
    }

}
