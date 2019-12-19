package com.biplav.e_softwaricaapi.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.biplav.e_softwaricaapi.R;
import com.biplav.e_softwaricaapi.api.EmployeeAPI;
import com.biplav.e_softwaricaapi.api.Url;
import com.biplav.e_softwaricaapi.model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment implements View.OnClickListener{


    EditText etname,etage, etsalary;
    Button btnRegister;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        etname = view.findViewById(R.id.etname);
        etage = view.findViewById(R.id.etage);
        etsalary = view.findViewById(R.id.etsalary);
        btnRegister = view.findViewById(R.id.btnsave);

        btnRegister.setOnClickListener(this);
        return view;
    }


    private void Register()
    {
        String name=etname.getText().toString();
        int age= Integer.parseInt(etage.getText().toString());
        String salary=etsalary.getText().toString();
        EmployeeCUD employeeCUD=new EmployeeCUD(name,salary,age);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
        Call<Void> voidCall=employeeAPI.registerEmployee(employeeCUD);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), "Sucessfully registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("error", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {
        Register();
    }
}
