package me.colinmarsch.simpleweather.mindyourmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.colinmarsch.simpleweather.mindyourmoney.MainActivity;
import me.colinmarsch.simpleweather.mindyourmoney.R;

public class Start extends AppCompatActivity {

    private final String SHARED_PREF_NAME_KEY = "me.colinmarsch.simpleweather.mindyourmoney.name_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        final Intent date= new Intent(Start.this, dateRange.class);
        final SharedPreferences sharedPref = this.getSharedPreferences(SHARED_PREF_NAME_KEY, MODE_PRIVATE);
        final EditText et = (EditText) findViewById(R.id.name_field);
        if(!sharedPref.getString("name", "").equals("")) {
            date.putExtra("name", sharedPref.getString("name", ""));
            startActivity(date);
        }
        //Call the button
        Button btn = (Button) findViewById(R.id.btnNext);
        //Action when pressed
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et.getText().toString().equals("")) {
                    date.putExtra("name", et.getText().toString());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("name", et.getText().toString());
                    editor.commit();
                    startActivity(date);
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Are you sure you filled all the fields?", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}
