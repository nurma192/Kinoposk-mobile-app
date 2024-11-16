package com.assignmentsmobile.assignment_2.data.repository

import com.assignmentsmobile.assignment_2.data.Actor
import retrofit2.Response
import com.assignmentsmobile.assignment_2.data.domain.ActorApiService


class ActorRepository (private val actorApiService: ActorApiService){
    suspend fun getActorDetailById(actorId: Int): Response<Actor> {
        return actorApiService.getActorDetailById(actorId)
    }

}