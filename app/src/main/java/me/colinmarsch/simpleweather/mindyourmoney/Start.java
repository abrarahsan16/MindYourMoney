package me.colinmarsch.simpleweather.mindyourmoney;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.colinmarsch.simpleweather.mindyourmoney.MainActivity;
import me.colinmarsch.simpleweather.mindyourmoney.R;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        //Call the button

        Button btn = (Button) findViewById(R.id.btnNext);
        //Action when pressed
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent date= new Intent(Start.this, dateRange.class);
                startActivity(date);
            }
        });
    }
}
