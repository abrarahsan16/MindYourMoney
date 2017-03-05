package me.colinmarsch.simpleweather.mindyourmoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.onClick;

/**
 * Created by colinmarsch on 2017-03-04.
 */

public class NewCat extends Activity {

    EditText cat;
    EditText bal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_cat);

        cat = (EditText) findViewById(R.id.new_cat);
        bal = (EditText) findViewById(R.id.new_bal);
    }

    public void submit(View view) {
        Intent in = new Intent();
        in.putExtra("name", cat.getText().toString().split(" ")[0]);
        in.putExtra("budget", Integer.parseInt(bal.getText().toString()));
        setResult(RESULT_OK, in);
        finish();
    }
}
