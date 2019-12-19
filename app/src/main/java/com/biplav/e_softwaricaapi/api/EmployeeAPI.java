package com.biplav.e_softwaricaapi.api;

import com.biplav.e_softwaricaapi.model.Employee;
import com.biplav.e_softwaricaapi.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {

    //retrive all employee
    @GET("employees")
    Call<List<Employee>> getAllEmployee();


    //create employee
    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD employeeCUD);

    //get emp on the basis of id
    @GET("employee/{empID}")
    Call<Employee> getEmployeeById(@Path("empID") int empID);

    //update the employee
    @PUT("update/{empID}")
    Call<Void> updateEmployee(@Path("empID") int empID, @Body EmployeeCUD emp);

    //delete the employee
    @DELETE("delete/{empID}")
    Call<Void> deleteEmployee(@Path("empID") int empID);
}
