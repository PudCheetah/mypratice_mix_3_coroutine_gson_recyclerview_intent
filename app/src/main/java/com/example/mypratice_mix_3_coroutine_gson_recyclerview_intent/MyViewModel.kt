package com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent.MyDatabase.MyJsonClass
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MyViewModel: ViewModel() {
    private lateinit var myJob: Job
    var myJsonData = MutableLiveData<MyJsonClass>()

    init {
        myJob = CoroutineScope(Dispatchers.IO).launch {
            var myURL =
                URL("https://opendata.cwa.gov.tw/api/v1/rest/datastore/E-A0014-001?Authorization=CWA-1394A705-AF6D-4DD6-9D2A-28ABBA9CF3B6&format=JSON").readText()
            withContext(Dispatchers.Main){
                myJsonData.value = Gson().fromJson(myURL, MyJsonClass::class.java)
            }
        }
    }
    fun getJob(): Job{
        return myJob
    }
    fun getJsonSize(): Int{
        return myJsonData.value!!.records.tsunami.size
    }
    fun getReport(report: String, indext: Int): String{
        var result = when (report) {
            "reportColor" -> myJsonData.value!!.records.tsunami[indext].reportColor
            "reportContent" -> myJsonData.value!!.records.tsunami[indext].reportContent
            "reportNo" -> myJsonData.value!!.records.tsunami[indext].reportNo
            "reportType" -> myJsonData.value!!.records.tsunami[indext].reportType
            "tsunamiNo" -> myJsonData.value!!.records.tsunami[indext].tsunamiNo.toString()
            "OriginTime" -> myJsonData.value!!.records.tsunami[indext].earthquakeInfo.originTime.toString()
            "FocalDepth" -> myJsonData.value!!.records.tsunami[indext].earthquakeInfo.focalDepth.toString()
            "EpicenterLatitude" -> myJsonData.value!!.records.tsunami[indext].earthquakeInfo.epicenter.epicenterLatitude.toString()
            "EpicenterLongitude" -> myJsonData.value!!.records.tsunami[indext].earthquakeInfo.epicenter.epicenterLongitude.toString()
            "MagnitudeValue" -> myJsonData.value!!.records.tsunami[indext].earthquakeInfo.earthquakeMagnitude.magnitudeValue.toString()
            else -> {"getReport()出錯"}
        }
        return result
    }
}