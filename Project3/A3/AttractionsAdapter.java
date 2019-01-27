package com.gupta.sudhanshu.cs478.project3_sdmp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AttractionsAdapter extends BaseAdapter {

    private Context mContext;
    private List<Attraction> attractionsList = new ArrayList<>();

    public AttractionsAdapter(Context context, List<Attraction> list){
        this.mContext = context;
        this.attractionsList = list;
    }

    @Override
    public int getCount() {
        return attractionsList.size();
    }

    @Override
    public Object getItem(int position) {
        return attractionsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return attractionsList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;

        if(item == null)
            item = LayoutInflater.from(mContext).inflate(R.layout.attractions_item,parent,false);

        Attraction song = (Attraction) this.getItem(position);

        TextView title = (TextView) item.findViewById(R.id.name);
        title.setText(song.getName());

        return item;
    }
}
