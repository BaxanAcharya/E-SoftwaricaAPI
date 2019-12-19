package com.biplav.e_softwaricaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    EditText name,age,salary;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name=findViewById(R.id.etnameu);
        age=findViewById(R.id.etageu);
        salary=findViewById(R.id.etsalaryu);
        update=findViewById(R.id.btnsaveu);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

//Extract the data…
        String nameu = bundle.getString("name");
        String ageu=bundle.getString("age");
        String salaryu=bundle.getString("salary");


        name.setText(nameu);
        age.setText(ageu);
        salary.setText(salaryu);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
