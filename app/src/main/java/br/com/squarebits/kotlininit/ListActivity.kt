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


        val strings = listOf(getString(R.string.fowler), "Beck", "Evans")

        this.list.setAdapter(adapter)

        adapter.refresh(strings)

    }


}
