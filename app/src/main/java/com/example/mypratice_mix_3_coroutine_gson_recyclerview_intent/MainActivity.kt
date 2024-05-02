package com.example.mypratice_mix_3_coroutine_gson_recyclerview_intent

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
    override fun onCreate(savedInstanceState: Bundle?) {
        var myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            bindingMainBinding = ActivityMainBinding.inflate(layoutInflater)
            joinAll(myViewModel.getJob())
            bindingMainBinding.apply {
                myRVMain.layoutManager = LinearLayoutManager(this@MainActivity)
                myRVMain.setHasFixedSize(true)
                myRVMain.adapter = MyAdapter(myViewModel)
            }
            setContentView(bindingMainBinding.root)
        }
    }
}