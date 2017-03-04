package helloworld.abrarahsan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dateRange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_range);

        Button btn2 = (Button) findViewById(R.id.btnNext2);
        //Action when pressed
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent budget= new Intent(dateRange.this,budget.class);
                startActivity(budget);
            }
        });
    }
}
