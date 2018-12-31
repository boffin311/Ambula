package com.example.himanshu.ambula;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
FrameLayout contatinerMainFrame;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contatinerMainFrame=findViewById(R.id.containerMainFrame);

        MainScreenFragment mainScreenFragment = new MainScreenFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.containerMainFrame, mainScreenFragment)
                .commit();
    }
}
