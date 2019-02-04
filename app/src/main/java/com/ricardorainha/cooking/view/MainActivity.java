package com.ricardorainha.cooking.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ricardorainha.cooking.R;
import com.ricardorainha.cooking.model.Recipe;
import com.ricardorainha.cooking.model.Repository;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {

    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
