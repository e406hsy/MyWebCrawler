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
        this.configReader = new ConfigReaderImpl(context);
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
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        CrawlConfig.Target target = configReader.getCrawlConfig().getTargets().get(position);

        View v = vi.inflate(R.layout.list_item, null);
        TextView title = v.findViewById(R.id.firstLine);

        title.setText(target.getTitle());
        TextView url = v.findViewById(R.id.secondLine);
        url.setText(target.getUrl().toString());

        View viewById = v.findViewById(R.id.deleteButton);

        viewById.setOnClickListener(v1 -> {
            try {
                this.configReader.getCrawlConfig().getTargets().remove(position);
                notifyDataSetChanged();
            } catch (RuntimeException e) {
                Log.i(getClass().getName() + ".R.id.deleteButton.onClickListener", "pos : " + position, e);
            }
        });
        return v;
    }
}
