package br.com.squarebits.kotlininit

import android.os.Bundle
import android.widget.ListView

class ListActivity : BaseActivity() {

    var adapter: MyBaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        initView();
    }


    fun initView(){
        var list = findViewById(R.id.list) as ListView
        adapter = MyBaseAdapter(this)

        var strings = ArrayList<String>()
        strings.add("A")
        strings.add("B")
        strings.add("C")

        adapter!!.refresh(strings)

        list.setAdapter(adapter)


    }
}
