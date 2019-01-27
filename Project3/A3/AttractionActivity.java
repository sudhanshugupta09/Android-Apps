package com.gupta.sudhanshu.cs478.project3_sdmp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.app.ActionBar;

import java.util.ArrayList;
import java.util.List;

public class AttractionActivity extends AppCompatActivity implements AttractionsListViewFragment.ListSelectionListener{

    private android.app.FragmentManager mFragmentManager;
    private AttractionsListViewFragment mAttractionListViewFragment = new AttractionsListViewFragment();
    private AttractionsWebViewFragment mAttractionWebViewFragment = new AttractionsWebViewFragment();
    private FrameLayout mListViewFrameLayout, mWebViewFrameLayout;

    public static List<Attraction> attractionList = new ArrayList<>();

    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String WVADDED = "wvAdded";

    private boolean wvAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);

        if (savedInstanceState != null) {
            wvAdded = savedInstanceState.getBoolean(WVADDED);
        }

        AttractionActivity.attractionList.clear();
        AttractionActivity.attractionList.add(
                new Attraction("Golden Gate Bridge", "http://www.goldengatebridge.org/"));
        AttractionActivity.attractionList.add(
                new Attraction("Pier 39", "https://www.pier39.com/"));
        AttractionActivity.attractionList.add(
                new Attraction("Aquarium of the Bay", "https://www.aquariumofthebay.org/"));
        AttractionActivity.attractionList.add(
                new Attraction("San Francisco Maritime National Historical Park", "https://www.nps.gov/safr/index.htm"));
        AttractionActivity.attractionList.add(
                new Attraction("Musée Mécanique", "http://museemecaniquesf.com/"));
        AttractionActivity.attractionList.add(
                new Attraction("Madame Tussauds San Francisco", "https://www.madametussauds.com/san-francisco/en/"));
        AttractionActivity.attractionList.add(
                new Attraction("USS Pampanito", "https://maritime.org/uss-pampanito/"));

        mListViewFrameLayout = (FrameLayout) findViewById(R.id.fm_list_view);
        mWebViewFrameLayout = (FrameLayout) findViewById(R.id.fm_web_view);

        mFragmentManager = getFragmentManager();

//        if (savedInstanceState == null) {
        FragmentTransaction fragmentTransaction = mFragmentManager
                .beginTransaction();

        // Add the TitleFragment to the layout
        fragmentTransaction.replace(R.id.fm_list_view, mAttractionListViewFragment);

//            fragmentTransaction.addToBackStack(null);

        // Commit the FragmentTransaction
        fragmentTransaction.commit();
//        }

        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager
                .addOnBackStackChangedListener(new android.app.FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });
    }

    private void setLayout() {
        if (!mAttractionWebViewFragment.isAdded()) {

            // Make the TitleFragment occupy the entire layout
            mListViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mWebViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        } else {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Make the TitleLayout take 1/3 of the layout's width
                mListViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // Make the QuoteLayout take 2/3's of the layout's width
                mWebViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            } else {

                mListViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));


                mWebViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT));
            }
        }
    }


    @Override
    public void onListSelection(int index) {
        // If the QuoteFragment has not been added, add it now
        if (!mAttractionWebViewFragment.isAdded()) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the QuoteFragment to the layout
            fragmentTransaction.replace(R.id.fm_web_view, mAttractionWebViewFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }

//        if (mAttrWebViewFragment.getCurrIdx() != index) {
        mAttractionWebViewFragment.showURL(index);
//        }
    }

    @Override
    public void onOrientationChange(int index) {
        if (index != -1 && wvAdded) {
            if (!mAttractionWebViewFragment.isAdded()) {

                // Start a new FragmentTransaction
                FragmentTransaction fragmentTransaction = mFragmentManager
                        .beginTransaction();

                // Add the QuoteFragment to the layout
                fragmentTransaction.replace(R.id.fm_web_view, mAttractionWebViewFragment);

                // Add this FragmentTransaction to the backstack
                fragmentTransaction.addToBackStack(null);

                // Commit the FragmentTransaction
                fragmentTransaction.commit();

                // Force Android to execute the committed FragmentTransaction
                mFragmentManager.executePendingTransactions();
            }

            if (mAttractionWebViewFragment.getCurrIdx() != index) {
                mAttractionListViewFragment.getListView().setItemChecked(index, true);
                mAttractionListViewFragment.attrAdapter.notifyDataSetChanged();
                mAttractionWebViewFragment.showURL(index);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(WVADDED, mAttractionWebViewFragment.isAdded());

    }

    public void onBackPressed (){
        super.onBackPressed();
        if(!mAttractionWebViewFragment.isAdded()){
            if(mAttractionListViewFragment.isVisible()  || mAttractionListViewFragment.isAdded() ) {
                mAttractionListViewFragment.getListView().clearFocus();
                mAttractionListViewFragment.getListView().clearChoices();
                mAttractionListViewFragment.getListView().setItemChecked(mAttractionWebViewFragment.getCurrIdx(), false);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Get a reference to the MenuInflater
        MenuInflater inflater = getMenuInflater();

        // Inflate the menu using activity_menu.xml
        inflater.inflate(R.menu.my_menu, menu);
//        menu.removeItem(R.id.menu1);

        // Return true to display the menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Log.i("yaya", "yaya");
                // return value true indicates that the menu click has been handled
                return true;
            case R.id.menu2:
                Log.i("yaya", "yaya");
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }
}

