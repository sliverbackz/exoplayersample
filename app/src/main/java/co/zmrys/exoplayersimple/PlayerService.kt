package co.zmrys.exoplayersimple

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.content.ContextCompat
import co.zmrys.exoplayersimple.extensions.NotificationManager
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import timber.log.Timber

class PlayerService : Service() {

    private lateinit var binder: PlayerServiceBinder
    private lateinit var notificationManager: NotificationManager
    private var mediaSessionToken: MediaSessionCompat.Token? = null
    private var isForegroundService = false

    inner class PlayerServiceBinder : Binder() {
        val service: PlayerService get() = this@PlayerService
    }

    override fun onBind(intent: Intent?): IBinder {
        binder = PlayerServiceBinder()
        mediaSessionToken = intent?.getParcelableExtra(MainActivity.MEDIA_SESSION_TOKEN)
        Timber.i(mediaSessionToken.toString())
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        notificationManager.hideNotification()
        return super.onUnbind(intent)
    }

    fun setPlayer(exoPlayer: ExoPlayer) {
        notificationManager =
            NotificationManager(this, mediaSessionToken!!, playerNotificationListener)
        notificationManager.showNotificationForPlayer(exoPlayer)
    }

    private val playerNotificationListener =
        object : PlayerNotificationManager.NotificationListener {
            override fun onNotificationPosted(
                notificationId: Int,
                notification: Notification,
                ongoing: Boolean
            ) {
                if (ongoing && !isForegroundService) {
                    ContextCompat.startForegroundService(
                        applicationContext,
                        Intent(applicationContext, this@PlayerService::class.java)
                    )
                    startForeground(notificationId, notification)
                    isForegroundService = true
                }
            }

            override fun onNotificationCancelled(notificationId: Int, dismissedByUser: Boolean) {
                stopForeground(true)
                isForegroundService = false
                stopSelf()
            }
        }

}