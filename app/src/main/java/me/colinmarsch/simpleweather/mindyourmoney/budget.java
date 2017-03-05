package me.colinmarsch.simpleweather.mindyourmoney;

import android.content.Intent;
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

        final String name = getIntent().getStringExtra("name");
        editText = (EditText) findViewById(R.id.budget_input);
        Button btnComp = (Button) findViewById(R.id.btnComplete);
        //Action when pressed
        btnComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnComp = new Intent(budget.this, MainActivity.class);
                btnComp.putExtra("name", name);
                btnComp.putExtra("budget", editText.getText().toString());
                startActivity(btnComp);
            }
        });
    }
}
