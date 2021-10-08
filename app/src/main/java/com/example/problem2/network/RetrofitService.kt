package com.example.problem2.network

import com.example.problem2.network.model.Post
import com.example.problem2.network.model.PublishPostReq
import com.example.problem2.network.response.PublishPostRes
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.ArrayList


interface PostService {

    @POST("posts")
    fun publish(
        @Body PublishPostReq:PublishPostReq
    ): Observable<PublishPostRes>

    @GET("posts")
    fun getList(): Observable<ArrayList<Post>>
}











