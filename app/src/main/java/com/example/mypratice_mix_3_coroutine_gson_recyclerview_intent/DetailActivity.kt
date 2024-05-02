package com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent.databinding.ActivityInfoDetailBinding
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class DetailActivity: AppCompatActivity() {
    private lateinit var bindingActivityDetailBinding: ActivityInfoDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        var myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        super.onCreate(savedInstanceState)
        lifecycleScope.launch{
            bindingActivityDetailBinding = ActivityInfoDetailBinding.inflate(layoutInflater)
            joinAll(myViewModel.getJob())
            var rv_position = intent.getIntExtra("RV_position", -1)
            with(bindingActivityDetailBinding) {
                TVTsunamiNoDetail.text = myViewModel.getReport("tsunamiNo", rv_position)
                TVOriginTimeDetail.text = myViewModel.getReport("OriginTime", rv_position)
                TVFocalDepthDetail.text = myViewModel.getReport("FocalDepth", rv_position)
                TVEpicenterLatitudeDetail.text = myViewModel.getReport("EpicenterLatitude", rv_position)
                TVEpicenterLongitudeDetail.text = myViewModel.getReport("EpicenterLongitude", rv_position)
                TVMagnitudeValueDetail.text = myViewModel.getReport("MagnitudeValue", rv_position)
            }
        }
        setContentView(bindingActivityDetailBinding.root)
    }
}