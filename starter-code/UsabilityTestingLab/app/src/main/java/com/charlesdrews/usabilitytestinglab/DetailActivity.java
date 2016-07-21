package com.charlesdrews.usabilitytestinglab;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {
    public static final String SIGN_KEY = "sign_key";

    private DetailFragment mDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String selectedSign = getIntent().getStringExtra(SIGN_KEY);

        // if the sign was not added to the intent, getStringExtra() would return null
        if (selectedSign != null) {

            /*
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.horoscopedates.com/zodiac-signs/" + selectedSign));
            startActivity(intent);
            */
            mDetailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
            mDetailFragment.updateWebView(selectedSign);


            // rather than launch the link externally in the broswer, launch in the detail fragment's webview
            // - (hint: you'll need to get a reference to the detail fragment that's loaded in this activity)
        }
    }
}
