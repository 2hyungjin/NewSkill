package com.example.hilt

import retrofit2.Response
import javax.inject.Inject

class UserRepository
@Inject constructor(val userApi: GithubApi) {
    suspend fun getUsers(): List<User> {
        return userApi.getUsers()
    }
}