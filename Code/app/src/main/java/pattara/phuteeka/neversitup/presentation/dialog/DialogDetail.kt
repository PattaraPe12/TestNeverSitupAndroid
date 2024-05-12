package pattara.phuteeka.neversitup.presentation.dialog

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import pattara.phuteeka.neversitup.databinding.DialogDetailBinding

class DialogDetail: DialogFragment() {
    private lateinit var mViewBinding : DialogDetailBinding

    companion object {

        fun newInstance(
            desc: String
        ): DialogDetail {
            return DialogDetail().apply {
                arguments = Bundle().apply {
                    putString("data", desc)
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflater = LayoutInflater.from(context)
        mViewBinding = DialogDetailBinding.inflate(inflater)
        arguments?.apply {
            getString("data")?.let {
                mViewBinding.tvDesc.text = it
            }
        }
        mViewBinding.btnClose.setOnClickListener {
            dismiss()
        }
        return mViewBinding.root
    }
}