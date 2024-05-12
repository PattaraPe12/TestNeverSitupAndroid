package pattara.phuteeka.neversitup.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pattara.phuteeka.neversitup.data.DepartmentModel
import pattara.phuteeka.neversitup.data.remote.model.GetProductResponse
import pattara.phuteeka.neversitup.databinding.AdapterMainBinding

class MainAdapter (
    private val mItemClickDepartment: (MainActivity.DisplayType, String) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var itemData = DepartmentModel()

    inner class ViewHolder(val binding: AdapterMainBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding =
            AdapterMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int = 2

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val adapter by lazy {
            ItemAdapter { type,data ->
                mItemClickDepartment.invoke(type,data)
            }
        }
        var type = MainActivity.DisplayType.DEPARTMENT
        if (position==0){
            type = MainActivity.DisplayType.DEPARTMENT
            holder.binding.tvTitle.text = "Department Carousel"
            holder.binding.rvItem.layoutManager = LinearLayoutManager(holder.binding.root.context,LinearLayoutManager.HORIZONTAL,false)
//            adapter.addAll()
        }else{
            type = MainActivity.DisplayType.PRODUCT
            holder.binding.tvTitle.text = "Product list : ${itemData.title}"
            holder.binding.rvItem.layoutManager = GridLayoutManager(holder.binding.root.context,2)
        }
        holder.binding.rvItem.adapter = adapter
        adapter.addAll(itemData,type)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: DepartmentModel) {
        itemData = data
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addProduct(departmentName:String,product:List<GetProductResponse>){
        itemData.title = departmentName
        itemData.product = product
        notifyItemChanged(1)
    }
}