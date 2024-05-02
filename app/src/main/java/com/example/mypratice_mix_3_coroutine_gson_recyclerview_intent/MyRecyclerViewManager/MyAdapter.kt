package com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent.MyRecyclerViewManager

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent.DetailActivity
import com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent.MyViewModel
import com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent.databinding.ActivityMyRecyclerviewItemBinding

class MyAdapter(var myViewModel: MyViewModel): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private lateinit var myIntent: Intent

    inner class MyViewHolder(binding: ActivityMyRecyclerviewItemBinding): RecyclerView.ViewHolder(binding.root) {
        var reportColor = binding.TVReportColor
        var reportContent = binding.TVReportContent
        var reportNo = binding.TVReportNo
        var reportType = binding.TVReportType
        var tsunamiNo = binding.TVTsunamiNo
        var myConstraintlayout = binding.MyConstraintLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ActivityMyRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return myViewModel.getJsonSize()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            reportNo.text = myViewModel.getReport("reportNo" , position)
            reportType.text = myViewModel.getReport("reportType", position)
            reportContent.text = myViewModel.getReport("reportContent", position)
            reportColor.text = myViewModel.getReport("reportColor", position)
            tsunamiNo.text = "海嘯編號: ${myViewModel.getReport("tsunamiNo", position)}"
            myConstraintlayout.setOnClickListener {
                myIntent = Intent(it.context, DetailActivity::class.java)
                myIntent.putExtra("RV_position", position)
                startActivity(it.context, myIntent, null)
            }
        }

    }
}