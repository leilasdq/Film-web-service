package com.example.filmwebservices.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class MainActivity extends SingleFragmentActivity {

    public Intent newIntent (Context context){
        return new Intent(context, MainActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return MoviesFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    }
}
