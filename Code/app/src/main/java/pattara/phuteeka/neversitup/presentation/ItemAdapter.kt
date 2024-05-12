package pattara.phuteeka.neversitup.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pattara.phuteeka.neversitup.R
import pattara.phuteeka.neversitup.data.DepartmentModel
import pattara.phuteeka.neversitup.databinding.AdapterDepartmentBinding
import pattara.phuteeka.neversitup.databinding.AdapterProductBinding
//import pattara.phuteeka.neversitup.utils.glide.GlideApp
import java.net.HttpURLConnection
import java.net.URL


class ItemAdapter(
    private val mItemClickDepartment: (MainActivity.DisplayType, String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemData = DepartmentModel()
    private var viewTypeDisplay = MainActivity.DisplayType.DEPARTMENT

    inner class ViewHolderDepartment(val binding: AdapterDepartmentBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ViewHolderProduct(val binding: AdapterProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemViewType(position: Int): Int {
        return if (viewTypeDisplay == MainActivity.DisplayType.DEPARTMENT) {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewTypeDisplay == MainActivity.DisplayType.DEPARTMENT) {
            val itemViewBinding =
                AdapterDepartmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolderDepartment(itemViewBinding)
        } else {
            val itemViewBinding =
                AdapterProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolderProduct(itemViewBinding)
        }

    }

    override fun getItemCount(): Int {
        return if (viewTypeDisplay == MainActivity.DisplayType.DEPARTMENT) {
            itemData.department.size
        } else {
            itemData.product.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolderDepartment) {
            with(holder) {
                binding.tvTitle.text = itemData.department[position].name ?: ""
                displayImageUrl(itemData.department[position].imageUrl ?: "",binding.root.context,binding.img)
//                CoroutineScope(Dispatchers.IO).launch {
//                    val url = URL(itemData.department[position].imageUrl ?: "")
//                    val http = url.openConnection() as HttpURLConnection
//                    val statusCode = http.responseCode
//                    if (statusCode == 200){
//                        val lastUrl = http.url
//                        CoroutineScope(Dispatchers.Main).launch{
//                            Glide.with(binding.root.context)
//                                .asBitmap()
//                                .load(lastUrl)
//                                .into(object : CustomTarget<Bitmap>(){
//                                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                                        binding.img.setImageBitmap(resource)
//                                    }
//                                    override fun onLoadCleared(placeholder: Drawable?) {
//                                    }
//                                })
//                        }
//                    }
//                }

                binding.root.setOnClickListener {
                    mItemClickDepartment.invoke(
                        MainActivity.DisplayType.DEPARTMENT,
                        itemData.department[position].id ?: ""
                    )
                }
            }
        } else if (holder is ViewHolderProduct) {
            with(holder) {
                binding.tvName.text = itemData.product[position].name ?: ""
                binding.tvDesc.text = itemData.product[position].desc ?: ""
                binding.tvPrice.text = itemData.product[position].price ?: ""
                displayImageUrl(itemData.product[position].imageUrl ?: "",binding.root.context,binding.img)

                binding.root.setOnClickListener {
                    mItemClickDepartment.invoke(
                        MainActivity.DisplayType.PRODUCT,
                        itemData.product[position].desc ?: ""
                    )
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(data: DepartmentModel, type: MainActivity.DisplayType) {
        itemData = data
        viewTypeDisplay = type
        notifyDataSetChanged()
    }

    private fun displayImageUrl(imageUrl:String, mContext: Context, imageView:ImageView){
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL(imageUrl)
            val http = url.openConnection() as HttpURLConnection
            val statusCode = http.responseCode
            if (statusCode == 200){
                val lastUrl = http.url
                CoroutineScope(Dispatchers.Main).launch{
                    Glide.with(mContext)
                        .asBitmap()
                        .load(lastUrl)
                        .centerCrop()
                        .into(object : CustomTarget<Bitmap>(){
                            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                imageView.setImageBitmap(resource)
                            }
                            override fun onLoadCleared(placeholder: Drawable?) {
                            }
                        })
                }
            }
        }

    }
}