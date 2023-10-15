package com.soonyong.mywebcrawler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import com.soonyong.mywebcrawler.config.ConfigViewAdaptor
import com.soonyong.mywebcrawler.databinding.FragmentFirstBinding


class FirstFragment : ListFragment() {
    private var binding: FragmentFirstBinding? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        listAdapter = context?.let { ConfigViewAdaptor(it) }
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}