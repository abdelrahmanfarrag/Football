package com.example.football.data.remote.response


import com.example.football.data.model.Videos
import com.example.football.data.model.Videos.Competition
import com.example.football.data.model.Videos.Side1
import com.example.football.data.model.Videos.Side2
import com.google.gson.annotations.SerializedName

data class VideosResponse(
  @SerializedName("competition")
  val competition: Competition? = null,
  @SerializedName("date")
  val date: String? = null,
  @SerializedName("embed")
  val embed: String? = null,
  @SerializedName("side1")
  val side1: Side1? = null,
  @SerializedName("side2")
  val side2: Side2? = null,
  @SerializedName("thumbnail")
  val thumbnail: String? = null,
  @SerializedName("title")
  val title: String? = null,
  @SerializedName("url")
  val url: String? = null,
  @SerializedName("videos")
  val videos: List<Video>? = null,
  var videoToPlay: String? = null
) {
  data class Competition(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String
  )

  data class Side1(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String
  )

  data class Side2(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String
  )

  data class Video(
    @SerializedName("embed")
    val embed: String? = null,
    @SerializedName("title")
    val title: String? = null
  )
}

fun VideosResponse.mapToVideos() = Videos(
  Competition(
    competition?.name ?: ""
  ),
  Side1(side1?.name ?: ""),
  Side2(side2?.name ?: ""),
  date ?: "",
  thumbnail ?: "",
  videoToPlay ?: "",
  title ?: ""
)