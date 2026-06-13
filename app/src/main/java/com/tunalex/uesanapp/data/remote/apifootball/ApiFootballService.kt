package com.tunalex.uesanapp.data.remote.apifootball

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFootballService {

    @GET(value = "countries")
    suspend fun getCountries(): CountryResponse

    @GET(value = "teams")
    suspend fun getTeamsByCountry(@Query(value = "country") country: String): TeamResponse

}
