package me.colinmarsch.simpleweather.mindyourmoney;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import static me.colinmarsch.simpleweather.mindyourmoney.R.id.editText;

public class dateRange extends AppCompatActivity {
    int nYear, nMonth, nDay;
    Button btnDate;
    static final int DIALOG_ID= 0;
    EditText edtext;
    private final String SHARED_PREF_NAME_KEY = "me.colinmarsch.simpleweather.mindyourmoney.name_key";
    SharedPreferences sharedPref;
    EditText time;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_range);
        final Calendar cal= Calendar.getInstance();
        nYear=cal.get(Calendar.YEAR);
        nMonth=cal.get(Calendar.MONTH);
        nDay=cal.get(Calendar.DAY_OF_MONTH);
        final Intent budget = new Intent(dateRange.this, budget.class);
        sharedPref = this.getSharedPreferences(SHARED_PREF_NAME_KEY, MODE_PRIVATE);
        edtext = (EditText) findViewById(R.id.editText2);
        time = (EditText) findViewById(R.id.editText3);
        showDialogButtonClick();
        int year, month, day;

        final String name = getIntent().getStringExtra("name");
        if(!sharedPref.getString("date", "").equals("") && !sharedPref.getString("timeFrame", "").equals("")) {
            budget.putExtra("name", name);
            budget.putExtra("date", sharedPref.getString("date", ""));
            budget.putExtra("timeFrame", sharedPref.getString("timeFrame", ""));
            startActivity(budget);
            finish();
        }
        Button btn2 = (Button) findViewById(R.id.btnNext2);
        //Action when pressed
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtext.getText().toString().equals("") || time.getText().toString().equals("")) {
                    Snackbar.make(findViewById(android.R.id.content), "Are you sure you filled all the fields?", Snackbar.LENGTH_LONG)
                            .show();
                } else {
                    budget.putExtra("date", edtext.getText().toString());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("date", edtext.getText().toString());
                    budget.putExtra("timeFrame", time.getText().toString());
                    editor.putString("timeFrame", time.getText().toString());
                    editor.commit();
                    budget.putExtra("name", name);
                    startActivity(budget);
                    finish();
                }
            }
        });


    }
    public void showDialogButtonClick() {

        btnDate=(Button) findViewById(R.id.btnCalen);
        btnDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showDialog(DIALOG_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id==DIALOG_ID)

            return new DatePickerDialog(this, dpickerListener, nYear, nMonth, nDay);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view , int year, int monthOfYear, int dayOfMonth){
            nYear=year;
            nMonth=monthOfYear;
            nDay=dayOfMonth;
            edtext.setText(new StringBuilder().append(nDay).append("/").append(nMonth + 1).append("/").append(nYear));
        }
    };
}

