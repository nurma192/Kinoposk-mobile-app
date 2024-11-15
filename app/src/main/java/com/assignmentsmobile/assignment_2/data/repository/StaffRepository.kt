package com.assignmentsmobile.assignment_2.data.repository

import com.assignmentsmobile.assignment_2.data.Staff
import com.assignmentsmobile.assignment_2.data.domain.StaffApiService
import retrofit2.Response

class StaffRepository(private val apiService: StaffApiService) {
    suspend fun getStaffById(filmId: Int): Response<List<Staff>> {
        return apiService.getStaffById(filmId)
    }
}