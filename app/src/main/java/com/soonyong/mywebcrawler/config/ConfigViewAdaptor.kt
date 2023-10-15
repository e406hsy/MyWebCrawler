package com.soonyong.mywebcrawler.config

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.soonyong.mywebcrawler.R
import com.soonyong.mywebcrawler.config.manage.ConfigManager
import com.soonyong.mywebcrawler.config.manage.ConfigManagerFactory
import java.io.IOException

class ConfigViewAdaptor(private val context: Context) : BaseAdapter() {
    private val configManager: ConfigManager = ConfigManagerFactory.getConfigManager(context)!!

    override fun getCount(): Int {
        return configManager.crawlConfig.targets.size
    }

    override fun getItem(position: Int): CrawlConfig.Target {
        return configManager.crawlConfig.targets[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        val vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val target: CrawlConfig.Target = configManager.crawlConfig.targets[position]
        val v = vi.inflate(R.layout.list_item, null)
        val title = v.findViewById<TextView>(R.id.firstLine)
        title.text = target.title
        val url = v.findViewById<TextView>(R.id.secondLine)
        url.text = target.url.toString()
        val viewById = v.findViewById<View>(R.id.deleteButton)
        viewById.setOnClickListener {
            try {
                configManager.crawlConfig.targets.removeAt(position)
                notifyDataSetChanged()
            } catch (e: IOException) {
                Log.i(javaClass.name + ".R.id.deleteButton.onClickListener", "pos : $position", e)
            }
        }
        return v
    }
}