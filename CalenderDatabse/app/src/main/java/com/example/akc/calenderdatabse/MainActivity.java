package com.example.akc.calenderdatabse;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import com.example.akc.calenderdatabse.Databse.DatabaseAdapter;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText e1, e2;
    Button b1, b2;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        final DatabaseAdapter adapter = new DatabaseAdapter(this);
        e1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    new DatePickerDialog(MainActivity.this, listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Date = e1.getText().toString();
                String Exp = e2.getText().toString();
                Integer expe = Integer.parseInt(Exp);
                Long xyz = adapter.insertdata(Date, expe);
                if (xyz < 0) {
                    Toast.makeText(getApplication(), "Not Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "Row Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Date = e1.getText().toString();
                Integer abc = adapter.fetchdata(Date);
                if (abc == 0) {
                    Toast.makeText(getApplication(), "Not Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplication(), "Row Found with Expense " + abc, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String string = Integer.toString(dayOfMonth) + "/" + Integer.toString(monthOfYear + 1) + "/" + Integer.toString(year);
            e1.setText(string);
        }
    };
}
