package org.mathieu.cleanrmapi.data.remote

import org.mathieu.cleanrmapi.data.remote.responses.EpisodeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {
    //Recupération de plusieurs episode sur meme ids
    @GET("episode/{ids}")
    suspend fun getEpisodes(@Path("ids") ids : List<Int>): List<EpisodeResponse>

    //Recupération d'episode sur un id
    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id : Int): EpisodeResponse
}