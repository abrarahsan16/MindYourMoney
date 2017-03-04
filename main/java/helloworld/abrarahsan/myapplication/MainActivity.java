package helloworld.abrarahsan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call the button

        Button btn = (Button) findViewById(R.id.btnNext);
        //Action when pressed
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent date= new Intent(MainActivity.this,dateRange.class);
                startActivity(date);
            }
        });
    }
}
