package com.assignmentsmobile.assignment_2.data.domain

import com.assignmentsmobile.assignment_2.data.Actor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ActorApiService {
    @GET("/api/v1/staff/{id}")
    suspend fun getActorDetailById(
        @Path("id")id:Int
    ) : Response<Actor>
}