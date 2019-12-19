package com.biplav.e_softwaricaapi.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.biplav.e_softwaricaapi.R;
import com.biplav.e_softwaricaapi.UpdateActivity;
import com.biplav.e_softwaricaapi.api.EmployeeAPI;
import com.biplav.e_softwaricaapi.api.Url;
import com.biplav.e_softwaricaapi.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private AlertDialog.Builder builder;
    List<Employee> employeeList;
    Context mcontext;
    EmployeeAPI employeeAPI;


    public EmployeeAdapter(List<Employee> employeeList, Context mcontext) {
        this.employeeList = employeeList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, final int position) {

        final Employee employee = employeeList.get(position);
        holder.id.setText(String.valueOf(employee.getId()));
        holder.age.setText(String.valueOf(employee.getEmployee_age()));
        holder.name.setText(employee.getEmployee_name());
        holder.salary.setText(employee.getEmployee_salary());


        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Url.base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                employeeAPI = retrofit.create(EmployeeAPI.class);

                Call<Employee> employeeCall = employeeAPI.getEmployeeById(employee.getId());
              employeeCall.enqueue(new Callback<Employee>() {
                  @Override
                  public void onResponse(Call<Employee> call, Response<Employee> response) {
                      if (!response.isSuccessful()) {
                          Log.d("error", response.message());
                          return;
                      }

                      Intent intent=new Intent(v.getContext(), UpdateActivity.class);
                      Bundle bundle=new Bundle();

                      bundle.putString("id", String.valueOf(employee.getId()));
                      bundle.putString("name", employee.getEmployee_name());
                      bundle.putString("age", String.valueOf(employee.getEmployee_age()));
                      bundle.putString("salary", employee.getEmployee_salary());
                      intent.putExtras(bundle);
                      mcontext.startActivity(intent);
                  }

                  @Override
                  public void onFailure(Call<Employee> call, Throwable t) {
                    Log.d("error", t.getLocalizedMessage());
                  }
              });
            }
        });

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(v.getContext());

                builder.setMessage("Do you want to delete the image? ").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(Url.base_url)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                employeeAPI = retrofit.create(EmployeeAPI.class);
                                Call<Void> voidCall = employeeAPI.deleteEmployee(employee.getId());

                                voidCall.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if (!response.isSuccessful()) {
                                            Log.d("error", response.message());
                                            return;
                                        }
                                        employeeList.remove(position);
                                        notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Log.d("error", t.getLocalizedMessage());
                                    }
                                });



                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();


            }
        });

    }


    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private TextView id, name, age, salary;
        ImageButton btnupdate, btndelete;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);


            id = itemView.findViewById(R.id.name);
            salary = itemView.findViewById(R.id.age);
            name = itemView.findViewById(R.id.address);
            age = itemView.findViewById(R.id.gender);
            btndelete = itemView.findViewById(R.id.btndelete);
            btnupdate = itemView.findViewById(R.id.btnupdate);

        }
    }
}
