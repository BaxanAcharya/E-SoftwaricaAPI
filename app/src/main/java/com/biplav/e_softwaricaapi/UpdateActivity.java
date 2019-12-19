package com.biplav.e_softwaricaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.LocaleData;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.biplav.e_softwaricaapi.api.EmployeeAPI;
import com.biplav.e_softwaricaapi.api.Url;
import com.biplav.e_softwaricaapi.model.Employee;
import com.biplav.e_softwaricaapi.model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {

    EditText name, age, salary;
    Button update;
    EmployeeAPI employeeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name = findViewById(R.id.etnameu);
        age = findViewById(R.id.etageu);
        salary = findViewById(R.id.etsalaryu);
        update = findViewById(R.id.btnsaveu);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

//Extract the data…
        final String nameu = bundle.getString("name");
        final int ageu = Integer.parseInt(bundle.getString("age"));
        final String salaryu = bundle.getString("salary");
        final int idu = Integer.parseInt(String.valueOf(bundle.get("id")));

        name.setText(nameu);
        age.setText(""+ageu);
        salary.setText(salaryu);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Url.base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                employeeAPI = retrofit.create(EmployeeAPI.class);

                String namei=name.getText().toString();
              int  agei= Integer.parseInt(age.getText().toString());
             String salaryi=salary.getText().toString();

                EmployeeCUD employeeCUD=new EmployeeCUD(namei,salaryi,agei);
                Call<Void> voidCall = employeeAPI.updateEmployee(idu, employeeCUD);

                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        Log.d("error", response.message());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("error", t.getLocalizedMessage());
                    }
                });
            }
        });

    }
}
