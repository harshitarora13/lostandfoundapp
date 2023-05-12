package com.example.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateNewAdvertActivity extends AppCompatActivity {

    private EditText edt_name, edt_phone, edt_description, edt_date, edt_location;
    private Button btn_save;

    String Type;
    private DatabaseHelper databaseHelper;
    RadioButton radioButton;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_advert);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        edt_name = findViewById(R.id.edt_name);
        edt_phone = findViewById(R.id.edt_phone);
        edt_description = findViewById(R.id.edt_description);
        edt_date = findViewById(R.id.edt_date);
        edt_location = findViewById(R.id.edt_location);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(v -> {

            String name = edt_name.getText().toString();
            String phone = edt_phone.getText().toString();
            String description = edt_description.getText().toString();
            String date = edt_date.getText().toString();
            String location = edt_location.getText().toString();

            if (name.isEmpty()) {
                edt_name.setError("Name is Required");
                edt_name.requestFocus();
                return;
            }

            if (phone.isEmpty()) {
                edt_phone.setError("Phone is Required");
                edt_phone.requestFocus();
                return;
            }

            if (description.isEmpty()) {
                edt_description.setError("Description is Required");
                edt_description.requestFocus();
                return;
            }

            if (date.isEmpty()) {
                edt_date.setError("Date is Required");
                edt_date.requestFocus();
                return;
            }

            if (location.isEmpty()) {
                edt_location.setError("Location is Required");
                edt_location.requestFocus();
                return;
            }
            databaseHelper = new DatabaseHelper(getApplicationContext());
            Post post = new Post(Type,name, phone, description, date, location);
            boolean result = databaseHelper.createPost(post);
            if (result == true) {
                Toast.makeText(this, "Save Successfully", Toast.LENGTH_SHORT).show();
                edt_name.setText("");
                edt_date.setText("");
                edt_description.setText("");
                edt_location.setText("");
                edt_phone.setText("");

            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void checkButton(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rbtn_lost:
                if (checked)
                    Type="Lost";
                break;
            case R.id.rbtn_found:
                if (checked)
                    Type="Found";
                break;

        }
    }
}