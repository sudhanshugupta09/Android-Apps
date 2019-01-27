package com.gupta.sudhanshu.cs478.sdmpproject2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DealersViewAdapter extends BaseAdapter {


    //declare variables needed to inflate the view, fetch details of each row of listview and the context for the adaptor
    private Context mContext;
    private List<CarsDetails.DealersDetails> data;
    private LayoutInflater mInflater;

    //constructor
    public DealersViewAdapter(Context c, List<CarsDetails.DealersDetails> ids) {
        mContext = c;
        data = ids;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        DealersViewAdapter.ViewHolder holder;

        if(convertView == null) {

            //inflate the view
            convertView = mInflater.inflate(R.layout.list_dealer_details, parent, false);

            //populate the ViewHolder and store it inside the layout.
            holder = new DealersViewAdapter.ViewHolder();
            holder.dealerName = (TextView) convertView.findViewById(R.id.text_dealerName);
            holder.dealerAddress = (TextView) convertView.findViewById(R.id.text_dealerAddress);
            holder.dealerNumber = (TextView) convertView.findViewById(R.id.text_dealerNumber);
            convertView.setTag(holder);
        }
        else{
            // get the holder if convertview is not empty
            holder = (DealersViewAdapter.ViewHolder) convertView.getTag();
        }

        //initialize all textviews needed to show on listview
        TextView dealer_name = holder.dealerName;
        TextView dealer_address = holder.dealerAddress;
        TextView dealer_number = holder.dealerNumber;


        //get details of dealer object and set all textviews
        final CarsDetails.DealersDetails dealer = (CarsDetails.DealersDetails) getItem(position);
        dealer_name.setText(dealer.name);
        dealer_address.setText("Address: " + dealer.address);
        dealer_number.setText("Phone:  " + dealer.number);

        return convertView;

    }
    // Viewholder class for optimal memory use
    private static class ViewHolder {
        public TextView dealerName;
        public TextView dealerAddress;
        public TextView dealerNumber;
    }
}
