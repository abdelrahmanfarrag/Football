package com.example.football.data.remote

import android.util.Log
import android.widget.Toast
import com.example.football.presentation.common.ResourceState.ERROR
import com.example.football.presentation.common.ResourceState.SUCCESS
import com.example.football.presentation.common.ResponseResource
import com.example.football.utils.Error
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class Validator @Inject constructor(private val jsonResponse: Gson) {

  fun <T> validateApiResponse(response: Response<T>?): ResponseResource<T> {
    response?.let {
      it.apply {
        if (isSuccessful) {
          val model = response.body()
          if (model != null) {
            modelToJson(model)
            return ResponseResource(SUCCESS, responseData = model)
          }
        } else {
          return ResponseResource(ERROR, message = Error.GENERAL)

        }
      }

    }
    return ResponseResource(ERROR, message = Error.GENERAL)
  }

  private fun modelToJson(obj: Any): String {
    return jsonResponse.toJson(obj)
  }


}
