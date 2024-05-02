package com.example.mypratice_mix_2_coroutine_gson_recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypratice_mix_2_coroutine_gson_recyclerview.MyRecyclerViewManager.MyAdapter
import com.example.mypratice_mix_2_coroutine_gson_recyclerview.MyRecyclerViewManager.MyList
import com.example.mypratice_mix_2_coroutine_gson_recyclerview.databinding.ActivityMainBinding
import com.example.mypratice_mix_2_coroutine_gson_recyclerview.databinding.ActivityMyRecyclerviewItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMainBinding: ActivityMainBinding
    private lateinit var bindingActivityMyRecyclerviewItemBinding: ActivityMyRecyclerviewItemBinding
    var myList = MyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            bindingMainBinding = ActivityMainBinding.inflate(layoutInflater)
            joinAll(myList.getMyJob())
            bindingMainBinding.apply {
                myRVMain.layoutManager = LinearLayoutManager(this@MainActivity)
                myRVMain.setHasFixedSize(true)
                myRVMain.adapter = MyAdapter(myList)
            }
            setContentView(bindingMainBinding.root)
        }


    }
}