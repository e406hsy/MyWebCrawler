package com.soonyong.mywebcrawler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.soonyong.mywebcrawler.config.CrawlConfig;
import com.soonyong.mywebcrawler.config.manage.ConfigManager;
import com.soonyong.mywebcrawler.config.manage.ConfigManagerFactory;
import com.soonyong.mywebcrawler.databinding.FragmentSecondBinding;

import java.io.IOException;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSave.setOnClickListener(button ->
                {
                    if (binding.urlInput.getText().length() == 0 || binding.intervalInput.getText().length() == 0
                            || binding.titleInput.getText().length() == 0 || binding.xpathInput.getText().length() == 0
                            || (!binding.activeRadioTrue.isChecked() && !binding.activeRadioFalse.isChecked())) {
                        return;
                    }

                    try {
                        ConfigManager configManager = ConfigManagerFactory.getConfigManager(getContext());
                        configManager.addCrawlConfigTarget(CrawlConfig.Target.builder()
                                .url(binding.urlInput.getText().toString())
                                .interval(Long.parseLong(binding.intervalInput.getText().toString()))
                                .title(binding.titleInput.getText().toString())
                                .active(binding.activeRadioTrue.isChecked())
                                .targetXPath(binding.xpathInput.getText().toString())
                                .build());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                }
        );

        binding.buttonPrev.setOnClickListener(button -> NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}