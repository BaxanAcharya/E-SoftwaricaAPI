package com.biplav.e_softwaricaapi.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.biplav.e_softwaricaapi.R;
import com.biplav.e_softwaricaapi.adapter.EmployeeAdapter;
import com.biplav.e_softwaricaapi.api.EmployeeAPI;
import com.biplav.e_softwaricaapi.api.Url;
import com.biplav.e_softwaricaapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.recycleview);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Employee instance
        EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall=employeeAPI.getAllEmployee();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful())
                {
                    Toast.makeText(getContext(), "Error " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("error", response.message());
                    return;
                }

                List<Employee> employeeList=response.body();

                EmployeeAdapter employeeAdapter=new EmployeeAdapter(employeeList,getContext());
                recyclerView.setAdapter(employeeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("error", t.getLocalizedMessage());
            }
        });




        return view;
    }

}
