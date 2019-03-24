package com.example.kitesurfingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IdeaFilterActivity extends AppCompatActivity {

    public EditText mEditTextCountry;
    public EditText mEditTextWind;
    public Button mButtonApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_filter);

        mEditTextCountry = findViewById(R.id.edittext_country);
        mEditTextWind = findViewById(R.id.edittext_wind);

    }


    public void apply(View view) {
        if (TextUtils.isEmpty(mEditTextCountry.getText().toString())) {
            Toast.makeText(this, "Enter a Country ", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), IdeaListActivity.class);
            intent.putExtra("country", mEditTextCountry.getText().toString());
            intent.putExtra("windProbability", mEditTextWind.getText().toString());
            //CALL api
            startActivity(intent);
        } ;
    }
}
