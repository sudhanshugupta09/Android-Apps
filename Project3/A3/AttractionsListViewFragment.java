package com.gupta.sudhanshu.cs478.project3_sdmp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class AttractionsListViewFragment extends ListFragment{
    ListSelectionListener mlistener = null;
    AttractionsAdapter attrAdapter;

    private static int currIndex = -1;

    private static final String TAG = "AttractionsListViewFragment";
    private static final String SELECTED_INDEX = "SELECTED_INDEX";


    public interface ListSelectionListener{
        void onListSelection(int index);
        void onOrientationChange(int index);
    }


    public void onAttach(Context context){
        super.onAttach(context);
        try{
            mlistener = (ListSelectionListener) context;
        }catch (ClassCastException e){
            Log.i(TAG, e.toString());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // retain this fragment
        setRetainInstance(true);

        // Has Options Menu
        setHasOptionsMenu(true);
    }

    public void onActivityCreated(Bundle savedState){
        super.onActivityCreated(savedState);

        // allow only item item to be selected
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        attrAdapter = new AttractionsAdapter(this.getContext(), AttractionActivity.attractionList);
        setListAdapter(attrAdapter);

        if(savedState != null){
            currIndex = savedState.getInt(SELECTED_INDEX);
            mlistener.onOrientationChange(currIndex);
        }
    }

    // When a user selects an item form the list
    @Override
    public void onListItemClick(ListView lv, View v, int pos, long id){
        currIndex = pos;

        // highlight the selected item
        getListView().setItemChecked(pos, true);

        // inform the ???
        mlistener.onListSelection(pos);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(SELECTED_INDEX, currIndex);
    }
}
