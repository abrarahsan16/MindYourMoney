package me.colinmarsch.simpleweather.mindyourmoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by colinmarsch on 2017-03-04.
 */

public class Payment extends Activity {

    EditText cat;
    EditText payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity);

        cat = (EditText) findViewById(R.id.selected_cat);
        payment = (EditText) findViewById(R.id.amt);
    }

    public void submit(View view) {
        Intent in = new Intent();
        in.putExtra("category", cat.getText().toString().split(" ")[0]);
        in.putExtra("spent", Integer.parseInt(payment.getText().toString()));
        setResult(RESULT_OK, in);
        finish();
    }

}
