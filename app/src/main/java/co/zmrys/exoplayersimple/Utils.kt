package co.zmrys.exoplayersimple

import android.content.Context
import android.support.v4.media.MediaMetadataCompat
import co.zmrys.exoplayersimple.extensions.album
import co.zmrys.exoplayersimple.extensions.artist
import co.zmrys.exoplayersimple.extensions.displayDescription
import co.zmrys.exoplayersimple.extensions.displayIconUri
import co.zmrys.exoplayersimple.extensions.displayTitle
import co.zmrys.exoplayersimple.extensions.genre
import co.zmrys.exoplayersimple.extensions.id
import co.zmrys.exoplayersimple.extensions.mediaUri
import co.zmrys.exoplayersimple.extensions.title
import co.zmrys.exoplayersimple.extensions.trackCount
import co.zmrys.exoplayersimple.extensions.trackNumber
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes

object Utils {
    //audio attributes
    private val uAmpAudioAttributes = AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    fun createSimpleExoPlayer(context: Context): SimpleExoPlayer {
        return SimpleExoPlayer.Builder(context)
            .setAudioAttributes(uAmpAudioAttributes, true)
            .setHandleAudioBecomingNoisy(true)
            .build()
    }

    fun createSimpleData(): List<MediaMetadataCompat> {
        val metadataBuilder = MediaMetadataCompat.Builder()
        val data1 = metadataBuilder.apply {
            title = "The way of Waking Up"
            displayTitle = "The way of waking Up"
            album = "Wake Up"
            artist = "The Kyoto Connection"
            displayDescription = "The Kyoto Connection"
            genre = "Electronic"
            trackNumber = 1
            trackCount = 2
            displayIconUri =
                "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/art.jpg"
            id = "1"
            mediaUri =
                "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/01_-_Intro_-_The_Way_Of_Waking_Up_feat_Alan_Watts.mp3"
        }
            .build()
        val data2 = metadataBuilder.apply {
            album = "Wake Up"
            artist = "The Kyoto Connection"
            displayTitle = "Geisha"
            mediaUri =
                "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/02_-_Geisha.mp3"
        }
            .build()
        return listOf(data1, data2)
    }

}