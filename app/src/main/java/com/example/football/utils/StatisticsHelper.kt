package com.example.football.utils

import com.example.football.data.model.MatchStatistics.Statistics
import com.example.football.data.model.StatisticsList

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */

fun makeProgress(first: Double, second: Double): Int {
  return if (first < second)
    100 - ((first * 100 / (first + second))).toInt()
  else
    (second * 100 / (first + second)).toInt()

}

fun buildStatisticsList(statistics: Statistics, sectionName:MutableList<String>): MutableList<StatisticsList> =
  mutableListOf<StatisticsList>().apply {
    if (statistics.attacks.isNotEmpty()) {
      this.add(
        StatisticsList(
          sectionName[0],
          statistics.attacks.split(":")[1].toInt(),
          statistics.attacks.split(":")[0].toInt()
        )
      )
    }
    if (statistics.attemptsOnGoal.isNotEmpty()) {
      this.add(
        StatisticsList(
          sectionName[1],
          statistics.attemptsOnGoal.split(":")[0].toInt(),
          statistics.attemptsOnGoal.split(":")[1].toInt()
        )
      )
    }

    if (statistics.corners.isNotEmpty()) {
      this.add(
        StatisticsList(
          sectionName[2],
          statistics.corners.split(":")[0].toInt(),
          statistics.corners.split(":")[1].toInt()
        )
      )
    }
    if (statistics.fouls.isNotEmpty()) {
      this.add(
        StatisticsList(
          sectionName[3],
          statistics.fouls.split(":")[0].toInt(),
          statistics.fouls.split(":")[1].toInt()
        )
      )
    }
    if (statistics.freeKicks.isNotEmpty()) {
      this.add(
        StatisticsList(
          sectionName[4],
          statistics.freeKicks.split(":")[0].toInt(),
          statistics.freeKicks.split(":")[1].toInt()
        )
      )
    }

    if (statistics.offsides.isNotEmpty()) {
      this.add(
        StatisticsList(
          sectionName[5],
          statistics.offsides.split(":")[0].toInt(),
          statistics.offsides.split(":")[1].toInt()
        )
      )
    }
    if (statistics.possession.isNotEmpty()) {
      this.add(
        StatisticsList(
          sectionName[6],
          statistics.possession.split(":")[0].toInt(),
          statistics.possession.split(":")[1].toInt()
        )
      )
    }

    if (statistics.yellowCards.isNotEmpty()) {
      this.add(
        StatisticsList(
          sectionName[7],
          statistics.yellowCards.split(":")[0].toInt(),
          statistics.yellowCards.split(":")[1].toInt()
        )
      )
    }
    if (statistics.redCards.isNotEmpty()) {
      this.add(
        StatisticsList(
          sectionName[8],
          statistics.redCards.split(":")[0].toInt(),
          statistics.redCards.split(":")[1].toInt()
        )
      )
    }
    if (statistics.shotsOnTarget.isNotEmpty()) {
      this.add(
        StatisticsList(
          sectionName[9],
          statistics.shotsOnTarget.split(":")[0].toInt(),
          statistics.shotsOnTarget.split(":")[1].toInt()
        )
      )
    }

    if (statistics.shotsOffTarget.isNotEmpty()) {
      this.add(
        StatisticsList(
          sectionName[10],
          statistics.shotsOffTarget.split(":")[0].toInt(),
          statistics.shotsOffTarget.split(":")[1].toInt()
        )
      )
    }

  }
