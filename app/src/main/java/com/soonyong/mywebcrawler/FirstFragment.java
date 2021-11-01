package com.soonyong.mywebcrawler;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.soonyong.mywebcrawler.config.ConfigViewAdaptor;
import com.soonyong.mywebcrawler.databinding.FragmentFirstBinding;

import lombok.SneakyThrows;

public class FirstFragment extends ListFragment {

    private FragmentFirstBinding binding;

    @SneakyThrows
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        setListAdapter(new ConfigViewAdaptor(getContext()));

        return binding.getRoot();

    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // TODO: 수정 or 삭제 기능
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}