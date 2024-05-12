package pattara.phuteeka.neversitup.data.remote.model


import com.google.gson.annotations.SerializedName

data class GetProductResponse (
    @SerializedName("departmentId")
    var departmentId: String? = "",
    @SerializedName("desc")
    var desc: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("imageUrl")
    var imageUrl: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("price")
    var price: String? = "",
    @SerializedName("type")
    var type: String? = ""
)