package me.colinmarsch.simpleweather.mindyourmoney;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class budget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        Button btnComp = (Button) findViewById(R.id.btnComplete);
        //Action when pressed
        btnComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnComp = new Intent(budget.this, MainActivity.class);
                startActivity(btnComp);
            }
        });
    }
}
