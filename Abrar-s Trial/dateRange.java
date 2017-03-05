package me.colinmarsch.simpleweather.mindyourmoney;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class dateRange extends AppCompatActivity {
    int nYear, nMonth, nDay;
    Button btnDate;
    static final int DIALOG_ID= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDialogButtonClick();
        setContentView(R.layout.activity_date_range);
        int year, month, day;



        Button btn2 = (Button) findViewById(R.id.btnNext2);
        //Action when pressed
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent budget = new Intent(dateRange.this, budget.class);
                startActivity(budget);
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
            Toast.makeText(dateRange.this, nYear+"/"+nMonth+"/"+nDay, Toast.LENGTH_SHORT).show();
        }
        };
    }


