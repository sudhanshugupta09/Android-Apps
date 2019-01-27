package com.gupta.sudhanshu.cs478.sdmpproject2;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomCarGallaryActivity extends BaseAdapter {

    private Context mContext;
    private List<CarsDetails> data;

    private static final int PADDING = 0;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    public CustomCarGallaryActivity(Context context, List<CarsDetails> carIds) {
        mContext = context;
        this.data = carIds;
    }


        @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //viewholder to avoid repetitive calls to the findViewById()
        ViewHolder holder;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){

            convertView = inflater.inflate(R.layout.gridview_image_text, parent, false);

            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.gridView_text);
            holder.imageView = (ImageView) convertView.findViewById(R.id.gridView_image);
            holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(WIDTH, HEIGHT));
            holder.imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        TextView textView = holder.textView;
        ImageView imageView = holder.imageView;

        //get details of car object and set the image and company name
        final CarsDetails car = (CarsDetails) getItem(position);
        textView.setText(car.manufacturer);
        imageView.setImageResource(car.image_path);

        return convertView;
    }

    private static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
}
