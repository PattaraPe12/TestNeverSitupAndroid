package pattara.phuteeka.neversitup.data.remote.model


import com.google.gson.annotations.SerializedName

data class GetDepartmentResponse (
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("imageUrl")
    var imageUrl: String? = "",
    @SerializedName("name")
    var name: String? = ""
)