package pattara.phuteeka.neversitup.data

import pattara.phuteeka.neversitup.data.remote.model.GetDepartmentResponse
import pattara.phuteeka.neversitup.data.remote.model.GetProductResponse

data class DepartmentModel(
    val department:List<GetDepartmentResponse> = arrayListOf(),
    var title:String = "",
    var product:List<GetProductResponse> = arrayListOf()
)
