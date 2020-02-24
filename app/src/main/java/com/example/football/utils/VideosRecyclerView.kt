package com.example.football.utils

import android.content.Context
import android.graphics.Point
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.football.R
import com.example.football.data.model.Videos
import com.example.football.presentation.main.videos.adapter.VideosViewHolder
import com.example.football.utils.VolumeState.OFF
import com.example.football.utils.VolumeState.ON
import com.example.football.utils.extensions.gone
import com.example.football.utils.extensions.setDrawable
import com.example.football.utils.extensions.visible
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

/**
 * Authored by Abdelrahman Ahmed on 23 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
private enum class VolumeState {
  ON, OFF
}

class VideosRecyclerView :
  RecyclerView {

  //ItemViews
  private lateinit var thumbnail: ImageView
  private lateinit var volumeControl: ImageView
  private lateinit var progressBar: View
  private lateinit var itemView: View
  private lateinit var frame: FrameLayout
  private lateinit var videoSurfaceView: PlayerView
  private lateinit var videoPlayer: SimpleExoPlayer

  //VariablesIndicators
  private var videosList = mutableListOf<Videos>()
  private var videoSurfaceDefaultHeight = 0
  private var screenDefaultHeight = 0
  private var playedPosition = -1
  private var isVideoViewAdded = false
  private var volumeState = OFF
  private lateinit var customManager: CustomLayoutManager
  private val videoClickListener = OnClickListener {
    toggleVolume()
  }

  constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
    instantiateRecycler()
  }

  constructor(context: Context) : super(context) {
    instantiateRecycler()
  }

  private fun instantiateRecycler() {

    val display =
      (this.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val point = Point()
    display.getSize(point)
    videoSurfaceDefaultHeight = point.x
    screenDefaultHeight = point.y
    videoSurfaceView = PlayerView(this.context)
    customManager = CustomLayoutManager(this.context)
    this.layoutManager = customManager
    videoSurfaceView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
    val bandwidthMeter = DefaultBandwidthMeter()
    val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
    val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
    videoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
    videoSurfaceView.useController = false
    videoSurfaceView.player = videoPlayer
    setVolumeControl(OFF)
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == SCROLL_STATE_IDLE) {
          if (::thumbnail.isInitialized) {
            thumbnail.visibility = View.VISIBLE
          }
          if (!canScrollVertically(1)) {
            playVideo(true)
          } else {
            playVideo(false)
          }
        }

      }
    })
    addOnChildAttachStateChangeListener(object : OnChildAttachStateChangeListener {
      override fun onChildViewDetachedFromWindow(view: View) {
        if (::itemView.isInitialized && itemView == view) {
          resetVideoView()
        }
      }

      override fun onChildViewAttachedToWindow(view: View) {}

    })
    videoPlayer.addListener(object : Player.EventListener {
      override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when (playbackState) {
          Player.STATE_BUFFERING -> {
            if (::progressBar.isInitialized) progressBar.visibility = View.VISIBLE
            //customManager.isScrolling = false
            volumeControl.gone()
          }
          Player.STATE_ENDED -> {
            if (::videoPlayer.isInitialized) {
              videoPlayer.seekTo(0)
              resetVideoView()
            }
          }
          Player.STATE_READY -> {
          //  customManager.isScrolling = true
            if (::progressBar.isInitialized) progressBar.visibility = View.GONE
            if (!isVideoViewAdded) addVideoView()
            volumeControl.visible()
          }
          else -> if (::progressBar.isInitialized) progressBar.visibility = View.GONE


        }
      }

      override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {}

      override fun onSeekProcessed() {}

      override fun onTracksChanged(
        trackGroups: TrackGroupArray?,
        trackSelections: TrackSelectionArray?
      ) {
      }

      override fun onPlayerError(error: ExoPlaybackException?) {}

      override fun onLoadingChanged(isLoading: Boolean) {}

      override fun onPositionDiscontinuity(reason: Int) {}

      override fun onRepeatModeChanged(repeatMode: Int) {}

      override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {}

      override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {}


    })
  }


  private fun playVideo(isEndOfList: Boolean) {
    val targetPosition: Int
    if (!isEndOfList) {
      val startPos = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
      var endPos = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
      if ((endPos - startPos) > 1) {
        endPos = startPos + 1
      }
      if (startPos < 0 || endPos < 0) {
        return
      }

      targetPosition = if (startPos != endPos) {
        val startPositionVideoHeight = getVisibleVideoSurfaceHeight(startPos)
        val endPositionVideoHeight = getVisibleVideoSurfaceHeight(endPos)
        if (startPositionVideoHeight > endPositionVideoHeight) startPos else endPos
      } else {
        startPos
      }

    } else {
      targetPosition = videosList.size - 1
    }
    if (targetPosition == playedPosition) return
    playedPosition = targetPosition
    if (::videoSurfaceView.isInitialized) {
      videoSurfaceView.visibility = INVISIBLE
      removeVideoView(videoSurfaceView)
    }
    val currentPosition =
      targetPosition - (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    val child = getChildAt(currentPosition) ?: return
    val holder = child.tag as VideosViewHolder?
    if (holder == null) {
      playedPosition = -1
      return
    }
    thumbnail = holder.thumbnail
    progressBar = holder.loadingFrame
    volumeControl = holder.volumeController
    itemView = holder.itemView
    frame = holder.itemView.findViewById(R.id.mediaContainer)
    videoSurfaceView.player = videoPlayer
    itemView.setOnClickListener(videoClickListener)
    val dataSourceFactory =
      DefaultDataSourceFactory(context, Util.getUserAgent(context, "football"))
    val mediaUrl =
      "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
    val videoSource =
      ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mediaUrl))
    videoPlayer.prepare(videoSource)
    videoPlayer.playWhenReady = true
    Log.d("targetPosition", "$targetPosition")
  }


  private fun setVolumeControl(volumeState: VolumeState) {
    this.volumeState = volumeState
    if (volumeState == OFF) {
      videoPlayer.volume = 0f
      animateVolumeControl()
    } else if (volumeState == ON) {
      videoPlayer.volume = 1f
      animateVolumeControl()
    }

  }

  private fun animateVolumeControl() {
    if (::volumeControl.isInitialized) {
      volumeControl.bringToFront()
      if (volumeState == OFF) {
        volumeControl.setDrawable(R.drawable.ic_mute)
      } else {
        volumeControl.setDrawable(R.drawable.ic_unmute)

      }
      volumeControl.animate().cancel()
      volumeControl.alpha = 1f
      volumeControl.animate().apply {
        this.alpha(0f)
        this.duration = 600
        this.startDelay = 1000
      }
    }
  }

  private fun toggleVolume() {
    if (::videoPlayer.isInitialized) {
      if (volumeState == OFF) {
        setVolumeControl(ON)
      } else {
        setVolumeControl(OFF)
      }
    }
  }

  private fun addVideoView() {
    frame.addView(videoSurfaceView)
    isVideoViewAdded = true
    videoSurfaceView.requestFocus()
    videoSurfaceView.visibility = View.VISIBLE
    videoSurfaceView.alpha = 1f
    thumbnail.visibility = View.GONE
  }


  private fun getVisibleVideoSurfaceHeight(playPosition: Int): Int {
    val videoAtPosition =
      playPosition - (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    val child = getChildAt(videoAtPosition) ?: return 0
    val location = IntArray(2)
    child.getLocationInWindow(location)
    return if (location[1] < 0) {
      location[1] + videoSurfaceDefaultHeight
    } else {
      screenDefaultHeight - location[1]
    }
  }

  private fun resetVideoView() {
    if (isVideoViewAdded) {
      removeVideoView(videoSurfaceView)
      playedPosition = -1
      videoSurfaceView.visibility = GONE
      thumbnail.visibility = View.VISIBLE
    }
  }

  fun releasePlayer() {
    if (::videoPlayer.isInitialized) {
      videoPlayer.release()
    }
  }

  private fun removeVideoView(videoView: PlayerView) {
    val parent = videoView.parent as ViewGroup? ?: return
    val index = parent.indexOfChild(videoView)
    if (index >= 0) {
      parent.removeViewAt(index)
      isVideoViewAdded = false
      itemView.setOnClickListener(null)
    }
  }

  fun putMediaObject(objects: MutableList<Videos>) {
    this.videosList = objects
  }

  fun getCustomManager() = customManager
}
