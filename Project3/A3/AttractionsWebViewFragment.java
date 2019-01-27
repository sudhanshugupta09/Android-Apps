package com.gupta.sudhanshu.cs478.project3_sdmp;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AttractionsWebViewFragment extends Fragment {

    private WebView wv;
    private int mcurrIdx = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.attraction_web_view, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Don't destroy Fragment on reconfiguration
        setRetainInstance(true);

        // This Fragment adds options to the ActionBar
//        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wv = (WebView) getActivity().findViewById(R.id.attraction_webView);
    }

    public int getCurrIdx(){
        return mcurrIdx;
    }

    public void showURL(int position){

        if ( position >= AttractionActivity.attractionList.size() || position < 0)
            return;
        mcurrIdx = position;
        wv.setWebViewClient(new WebViewClient());
        wv.setWebChromeClient(new WebChromeClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        wv.loadUrl(AttractionActivity.attractionList.get(position).getUrl());
    }
}
