package br.com.squarebits.kotlininit

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : BaseActivity() {

    lateinit var adapter: MyBaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initView()
    }

    fun initView(){
        adapter = MyBaseAdapter(this)


        var strings = ArrayList<String>()
        strings.add("A")
        strings.add("B")
        strings.add("C")
        this.list.setAdapter(adapter)
        adapter.refresh(strings)

    }


}
