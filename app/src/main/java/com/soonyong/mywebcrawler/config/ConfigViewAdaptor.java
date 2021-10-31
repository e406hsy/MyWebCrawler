package com.soonyong.mywebcrawler.config;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soonyong.mywebcrawler.R;

import java.io.IOException;

import lombok.SneakyThrows;

public class ConfigViewAdaptor extends BaseAdapter {

    private ConfigReader configReader;
    private final Context context;

    public ConfigViewAdaptor(Context context) throws IOException {
        this.context = context;
        this.configReader = new InMemoryConfigReader();
    }

    @SneakyThrows
    @Override
    public int getCount() {
        return this.configReader.getCrawlConfig().getTargets().size();
    }

    @SneakyThrows
    @Override
    public CrawlConfig.Target getItem(int position) {
        return this.configReader.getCrawlConfig().getTargets().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SneakyThrows
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(getClass().getName(), convertView == null ? "convertView is null" : convertView.toString());
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        CrawlConfig.Target target = configReader.getCrawlConfig().getTargets().get(position);

        View v = vi.inflate(R.layout.list_item, null);
        TextView sectionName = (TextView) v.findViewById(R.id.firstLine);

        sectionName.setText(target.getTitle());
        return v;
    }
}
