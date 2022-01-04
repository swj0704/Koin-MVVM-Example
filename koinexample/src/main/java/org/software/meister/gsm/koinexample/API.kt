package org.software.meister.gsm.koinexample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("mealServiceDietInfo")
    fun getMeal(
        @Query("KEY") key : String = "67ebdc05dfea4d1dbc9fe76fc5a7932b",
        @Query("Type") type : String = "json",
        @Query("pIndex") index : Int = 1,
        @Query("pSize") size : Int = 5,
        @Query("ATPT_OFCDC_SC_CODE") local_code : String = "F10",
        @Query("SD_SCHUL_CODE") school_code : String = "7380292",
        @Query("MLSV_YMD") ymd : String
    ) : Call<MealResponse>
}