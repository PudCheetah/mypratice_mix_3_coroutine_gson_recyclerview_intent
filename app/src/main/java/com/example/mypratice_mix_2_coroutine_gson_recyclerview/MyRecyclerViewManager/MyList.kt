package com.example.mypratice_mix_2_coroutine_gson_recyclerview.MyRecyclerViewManager

import com.example.mypratice_mix_2_coroutine_gson_recyclerview.MyDatabase.MyJsonClass
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.URL

class MyList {
    private lateinit var myJsonData: MyJsonClass
    private lateinit var myJob: Job

    init {
        myJob = CoroutineScope(Dispatchers.IO).launch {
            var myURL =
                URL("https://opendata.cwa.gov.tw/api/v1/rest/datastore/E-A0014-001?Authorization=CWA-1394A705-AF6D-4DD6-9D2A-28ABBA9CF3B6&format=JSON").readText()
            myJsonData = Gson().fromJson(myURL, MyJsonClass::class.java)
        }
    }

    fun getMyJob(): Job {
        return myJob
    }
    fun getMyListSize(): Int{
        return myJsonData.records.tsunami.size
    }

    fun getReport(report: String, indext: Int): String{
        var result = when (report) {
            "reportColor" -> myJsonData.records.tsunami[indext].reportColor
            "reportContent" -> myJsonData.records.tsunami[indext].reportContent
            "reportNo" -> myJsonData.records.tsunami[indext].reportNo
            "reportType" -> myJsonData.records.tsunami[indext].reportType
            "tsunamiNo" -> myJsonData.records.tsunami[indext].tsunamiNo.toString()
            else -> {"getReport()出錯"}
        }
        return result
    }
}