package com.soonyong.mywebcrawler

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.soonyong.mywebcrawler.crawl.SampleBootReceiver
import com.soonyong.mywebcrawler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var appBarConfiguration: AppBarConfiguration? = null
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setSupportActionBar(binding!!.toolbar)
        val receiver = ComponentName(applicationContext, SampleBootReceiver::class.java)
        val pm = applicationContext.packageManager
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration.Builder(navController.graph)
                .build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration!!)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_new) {
            Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
        return (NavigationUI.navigateUp(navController, appBarConfiguration!!)
                || super.onSupportNavigateUp())
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}