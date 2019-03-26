package com.fcbox.loadmore


import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list = listOf<String>()



        for (i in 1..10) {
            list = list.plus(i.toString())
        }

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler.layoutManager = layoutManager
        recycler.adapter = object : RecyclerView.Adapter<VH>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
                val tv = TextView(recycler.context)
                tv.textSize = 60f
                tv.width = 200
                return VH(tv)
            }

            override fun getItemCount(): Int {
                return list.size
            }

            override fun onBindViewHolder(holder: VH, position: Int) {
                holder.textView.text = list[position]
            }
        }


        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManager.findLastCompletelyVisibleItemPosition() == list.size - 1) {
                    // bottom of list!
                    // loadMoreData();
                    Log.d("recyclerview", "loadmore")

                    for (i in 1..10) {
                        list = list.plus(i.toString())
                    }
                    recycler.adapter?.notifyDataSetChanged()

                }
            }
        })
    }

}

class VH(item: TextView) : RecyclerView.ViewHolder(item) {
    val textView = item
}