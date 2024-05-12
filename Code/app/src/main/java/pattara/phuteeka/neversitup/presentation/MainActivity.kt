package pattara.phuteeka.neversitup.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pattara.phuteeka.neversitup.data.DepartmentModel
import pattara.phuteeka.neversitup.databinding.ActivityMainBinding
import pattara.phuteeka.neversitup.presentation.dialog.DialogDetail
import java.net.HttpURLConnection
import java.net.URL


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mViewModel: MainViewModel by viewModels()
    private lateinit var mViewBinding : ActivityMainBinding
    lateinit var dialogDetail: DialogDetail

    private val adapter by lazy {
        MainAdapter { type,data ->
            if (type== DisplayType.DEPARTMENT) {
                mViewModel.getProduct(data)
            }else{
                val dialog = DialogDetail.newInstance(data)
                val transaction = supportFragmentManager.beginTransaction()
                transaction.add(dialog, "Desc")
                transaction.commitAllowingStateLoss()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        initObserve()
        mViewBinding.rvMain.adapter = adapter
        dialogDetail = DialogDetail()
//        setUpRecycler()
    }

    private fun initObserve() = with(mViewModel) {
        listDepartment.observe(this@MainActivity){
            adapter.addAll(DepartmentModel(it,"", arrayListOf()))
//            getImageUrl("https://loremflickr.com/640/480")

//            CoroutineScope(Dispatchers.IO).launch {
//                val url = URL("https://loremflickr.com/640/480")
//                val http = url.openConnection() as HttpURLConnection
//                val statusCode = http.responseCode
//                Log.d("DEBUG"," MainActivity code : $statusCode")
//                Log.d("DEBUG ","MainActivity url : ${http.url}")
//            }

        }
        listProduct.observe(this@MainActivity){
            adapter.addProduct(mViewModel.getDepartmentById()?.name?:"",it)
        }
        getDepartment()

    }

    private fun setUpRecycler() {
        mViewBinding.rvMain.layoutManager = LinearLayoutManager(this)
        mViewBinding.rvMain.adapter = adapter
    }

    enum class DisplayType {
        DEPARTMENT, PRODUCT
    }
}