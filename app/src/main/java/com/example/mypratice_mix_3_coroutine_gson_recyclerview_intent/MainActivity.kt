package com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent.MyRecyclerViewManager.MyAdapter
import com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMainBinding: ActivityMainBinding
    private lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        println("MainActivity-1(onCreat)")
        myViewModel= ViewModelProvider(this).get(MyViewModel::class.java)
        println("MainActivity-2(ViewModelProvider Finish)")
        super.onCreate(savedInstanceState)
        println("MainActivity-3(CoroutineScope)")
        CoroutineScope(Dispatchers.Main).launch {
            println("MainActivity-4(CoroutineScope start)")
            bindingMainBinding = ActivityMainBinding.inflate(layoutInflater)
            println("MainActivity-5(CoroutineScope joinAll-start)")
            joinAll(myViewModel.getJob())
            println("MainActivity-6(CoroutineScope joinAll-end)")
            bindingMainBinding.apply {
                println("MainActivity-7(CoroutineScope RecyclerView)")
                myRVMain.layoutManager = LinearLayoutManager(this@MainActivity)
                myRVMain.setHasFixedSize(true)
                myRVMain.adapter = MyAdapter(myViewModel)
            }
            println("MainActivity-8(CoroutineScope setContentView)")
            setContentView(bindingMainBinding.root)
            println("MainActivity-9(CoroutineScope End)")
        }
    }
}