package me.colinmarsch.simpleweather.mindyourmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class budget extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        final Intent btnCompi = new Intent(budget.this, MainActivity.class);
        final String SHARED_PREF_NAME_KEY = "me.colinmarsch.simpleweather.mindyourmoney.name_key";
        final String name = getIntent().getStringExtra("name");
        final String timeFrame = getIntent().getStringExtra("timeFrame");
        final String date = getIntent().getStringExtra("date");
        final SharedPreferences sharedPref = this.getSharedPreferences(SHARED_PREF_NAME_KEY, MODE_PRIVATE);
        if(!sharedPref.getString("budget", "").equals("")) {
            btnCompi.putExtra("name", name);
            btnCompi.putExtra("budget", sharedPref.getString("budget", ""));
            btnCompi.putExtra("date", date);
            btnCompi.putExtra("timeFrame", timeFrame);
            startActivity(btnCompi);
        }
        editText = (EditText) findViewById(R.id.budget_input);
        Button btnComp = (Button) findViewById(R.id.btnComplete);
        //Action when pressed
        btnComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().equals("")) {
                    btnCompi.putExtra("date", date);
                    btnCompi.putExtra("timeFrame", timeFrame);
                    btnCompi.putExtra("name", name);
                    btnCompi.putExtra("budget", editText.getText().toString());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("budget", editText.getText().toString());
                    editor.commit();
                    startActivity(btnCompi);
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Are you sure you filled all the fields?", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}
