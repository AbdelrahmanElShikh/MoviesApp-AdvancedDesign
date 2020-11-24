package com.noha.moviesadvanced.core.mapper

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.noha.moviesadvanced.core.resource.PresentationResource
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 *@author: Abdel-Rahman El-Shikh on 09-May-20.
 */
class RemoteResourceMapper<T> {
    fun map(response: Response<T>): PresentationResource<T> {
        return when (response.code()) {
            200 -> PresentationResource.success(response.body()!!)
            401, 404, 500 ->{
                val jsonObject = JSONObject(response.errorBody()!!.string().trim())
                PresentationResource.apiError(
                    jsonObject.getString("status_message"),
                    response.code(),
                    null
                )
            }
            else -> {
                val errorResponse: ErrorResponse =
                    Gson().fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
                return PresentationResource.apiError(
                    errorResponse.errorMsg,
                    response.code(),
                    errorResponse.extras
                )
            }
        }
    }


    fun mapList(response: Response<List<T>>): PresentationResource<List<T>> {
        return when (response.code()) {
            200 -> PresentationResource.success(response.body()!!)
            401, 429, 500 -> PresentationResource.apiError(
                response.message(),
                response.code(),
                null
            )
            else -> {
                val errorResponse: ErrorResponse =
                    Gson().fromJson(response.errorBody()!!.string(), ErrorResponse::class.java)
                return PresentationResource.apiError(
                    errorResponse.errorMsg,
                    response.code(),
                    errorResponse.extras
                )
            }
        }
    }

}