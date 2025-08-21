package com.example.callrecorderpro

import android.app.Service
import android.content.Intent
import android.media.MediaRecorder
import android.os.IBinder

class CallRecorderService : Service() {
    private var recorder: MediaRecorder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startRecording()
        return START_NOT_STICKY
    }

    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(filesDir.absolutePath + "/temp_recording.mp4")

            prepare()
            start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        recorder?.stop()
        recorder?.release()
        recorder = null

        // TODO: Upload temp_recording.mp4 to Google Drive
    }

    override fun onBind(intent: Intent?) = null
}
