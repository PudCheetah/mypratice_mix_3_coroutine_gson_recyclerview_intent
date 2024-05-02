package com.example.mypratice_mix_2_coroutine_gson_recyclerview.MyRecyclerViewManager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypratice_mix_2_coroutine_gson_recyclerview.databinding.ActivityMyRecyclerviewItemBinding

class MyAdapter(var myList: MyList): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder(binding: ActivityMyRecyclerviewItemBinding): RecyclerView.ViewHolder(binding.root) {
        var reportColor = binding.TVReportColor
        var reportContent = binding.TVReportContent
        var reportNo = binding.TVReportNo
        var reportType = binding.TVReportType
        var tsunamiNo = binding.TVTsunamiNo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ActivityMyRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return myList.getMyListSize()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            this.reportNo.text = myList.getReport("reportNo" , position)
            this.reportType.text = myList.getReport("reportType", position)
            this.reportContent.text = myList.getReport("reportContent", position)
            this.reportColor.text = myList.getReport("reportColor", position)
            this.tsunamiNo.text = "海嘯編號: ${myList.getReport("tsunamiNo", position)}"

        }
    }
}