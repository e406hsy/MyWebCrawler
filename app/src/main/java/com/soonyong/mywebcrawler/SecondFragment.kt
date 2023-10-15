package com.soonyong.mywebcrawler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.soonyong.mywebcrawler.config.CrawlConfig
import com.soonyong.mywebcrawler.config.manage.ConfigManagerFactory
import com.soonyong.mywebcrawler.crawl.CrawlJobService
import com.soonyong.mywebcrawler.databinding.FragmentSecondBinding
import java.io.IOException
import java.net.URI

class SecondFragment : Fragment() {
    private var binding: FragmentSecondBinding? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.buttonSave.setOnClickListener {
            if (binding!!.urlInput.text.isEmpty() || binding!!.intervalInput.text!!.isEmpty() || binding!!.titleInput.text!!.isEmpty() || binding!!.xpathInput.text!!.isEmpty() || !binding!!.activeRadioTrue.isChecked && !binding!!.activeRadioFalse.isChecked) {
                return@setOnClickListener
            }
            try {
                val configManager = ConfigManagerFactory.getConfigManager(context)
                configManager!!.addCrawlConfigTarget(CrawlConfig.Target(
                        url= URI( binding!!.urlInput.text.toString()),
                        interval=binding!!.intervalInput.text.toString().toLong(),
                        title=binding!!.titleInput.text.toString(),
                        active=binding!!.activeRadioTrue.isChecked,
                        targetXPath=binding!!.xpathInput.text.toString()
                        ))
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = System.currentTimeMillis()
                calendar[Calendar.HOUR_OF_DAY] = 8
                calendar[Calendar.MINUTE] = 0
                val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val crawlIntent = Intent(context, CrawlJobService::class.java)
                val alarmIntent = PendingIntent.getBroadcast(context, 0, crawlIntent, 0)
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, alarmIntent)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            NavHostFragment.findNavController(this@SecondFragment)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding!!.buttonPrev.setOnClickListener { button: View? ->
            NavHostFragment.findNavController(this@SecondFragment)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}