package com.example.football.data.model

/**
 * Authored by Abdelrahman Ahmed on 23 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
data class Videos(
  val competition: Competition,
  val side1: Side1,
  val side2: Side2,
  val date:String,
  val thumbnail:String,
  var video:String,
  val title:String
) {

  data class Competition(
    val name: String
  )
  data class Side1(
    val name:String
  )
  data class Side2(
    val name:String
  )
}