package com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent.MyDatabase.MyJsonClass
import com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent.databinding.ActivityInfoDetailBinding
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class DetailActivity: AppCompatActivity() {
    private lateinit var bindingActivityDetailBinding: ActivityInfoDetailBinding
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        println("DetailActivity-1(onCreat)")
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        println("DetailActivity-2(ViewModelProvider Finish)")
        super.onCreate(savedInstanceState)
        println("DetailActivity-3(lifeCycleScope)")
        lifecycleScope.launch{
            println("DetailActivity-4(lifecycleScope-start)")
            bindingActivityDetailBinding = ActivityInfoDetailBinding.inflate(layoutInflater)
            println("DetailActivity-5(lifecycleScope joinAll-start)")
            joinAll(myViewModel.getJob())
            println("DetailActivity-6(lifecycleScope joinAll-end)")
            var rv_position = intent.getIntExtra("RV_position", -1)
            println("DetailActivity-7")
            with(bindingActivityDetailBinding) {
                println("DetailActivity-8(getReport-start)")
                TVTsunamiNoDetail.text = myViewModel.getReport("tsunamiNo", rv_position)
                TVOriginTimeDetail.text = myViewModel.getReport("OriginTime", rv_position)
                TVFocalDepthDetail.text = myViewModel.getReport("FocalDepth", rv_position)
                TVEpicenterLatitudeDetail.text = myViewModel.getReport("EpicenterLatitude", rv_position)
                TVEpicenterLongitudeDetail.text = myViewModel.getReport("EpicenterLongitude", rv_position)
                TVMagnitudeValueDetail.text = myViewModel.getReport("MagnitudeValue", rv_position)
                println("DetailActivity-9(getReport-End)")
            }
            println("DetailActivity-10(lifecycleScope-end)")
        }
        println("DetailActivity-11")
        setContentView(bindingActivityDetailBinding.root)
        println("DetailActivity-12")
    }
}