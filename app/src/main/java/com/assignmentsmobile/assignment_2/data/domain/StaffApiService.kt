package com.assignmentsmobile.assignment_2.data.domain;

import com.assignmentsmobile.assignment_2.data.Staff;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query

interface StaffApiService {
    @GET("/api/v1/staff")
    suspend fun getStaffById(@Query("filmId") id: Int): Response<List<Staff>>
}
