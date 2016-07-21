package com.charlesdrews.usabilitytestinglab;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnZodiacSignSelectedListener {

    private boolean mScreenIsLageEnoughForTwoPanes = false;
    private DetailFragment mDetailFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*boolean large = (getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE;
        boolean xlarge = (getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE;
        boolean horizontal = (getResources().getConfiguration().orientation == 1);

        mScreenIsLageEnoughForTwoPanes = large || xlarge;

        if (mScreenIsLageEnoughForTwoPanes && horizontal){*/

        if (findViewById(R.id.detail_fragment_container) != null) {
            mScreenIsLageEnoughForTwoPanes = false;
            mDetailFragment = DetailFragment.newInstance(new Bundle());
            getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        }
        else{
            mScreenIsLageEnoughForTwoPanes = true;
        }
        // this class - MainActivity - implements the OnZodiacSignSelectedListener interface
        // defined in ListFragment - so "this" can be set as the listener for the list fragment
        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        listFragment.setListener(this);

        // determine which layout file is being used (hint: is there an element in the large-screen
        //  layout that's not in the regular layout?) and if the large screen layout is being used,
        //  then load the detail fragment in MainActivity rather than using DetailActivity
    }

    @Override
    public void onZodiacSignSelected(String zodiacSignSelected) {

        if (mScreenIsLageEnoughForTwoPanes) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.SIGN_KEY, zodiacSignSelected);
            startActivity(intent);
        }
        else{
            mDetailFragment.updateWebView(zodiacSignSelected);
        }
        // - if the detail fragment is loaded into MainActivity, update it rather than launching
        //      the DetailActivity
    }
}