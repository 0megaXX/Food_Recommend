package kr.trueme.composetest;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TestActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }
}