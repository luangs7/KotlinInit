package br.com.squarebits.kotlininit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import java.util.ArrayList


class MyBaseAdapter(private val context: Context) : BaseAdapter() {

    private val objects = ArrayList<String>()
    private val layoutInflater: LayoutInflater

    init {
        this.layoutInflater = LayoutInflater.from(context)
    }

    fun addData(mList: List<String>) {
        this.objects.addAll(mList)
        notifyDataSetChanged()
    }


    fun refresh() {
        notifyDataSetChanged()
    }

    fun refresh(mList: List<String>) {
        this.objects.clear()
        this.objects.addAll(mList)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return objects.size
    }

    override fun getItem(position: Int): String {
        return objects[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_clicks, null)
            convertView!!.tag = ViewHolder(convertView)
        }
        initializeViews(getItem(position) as String, convertView.tag as ViewHolder)
        return convertView
    }

    private fun initializeViews(`object`: String, holder: ViewHolder) {

        holder.month.text = `object`

    }

    protected inner class ViewHolder(view: View) {
        public val visitas: TextView
        public val textView19: TextView
        public val month: TextView

        init {
            month = view.findViewById(R.id.month) as TextView
            textView19 = view.findViewById(R.id.textView19) as TextView
            visitas = view.findViewById(R.id.visitas) as TextView
        }
    }
}
