package com.br.pingpongx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.br.pingpongx.databinding.ActivityMainBinding
import com.br.pingpongx.databinding.ActivityPlayerBinding
import com.br.pingpongx.model.LastGame

class PlayerActivity : AppCompatActivity() {

    private val TAG = "PINGPONGX"

    // private lateinit var etPlayer1: EditText

    private val LAST_GAME_REQUEST_CODE = 1
    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG, "onCreate")

        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btStart.setOnClickListener {
            nextScreen()
        }
    }

    private fun nextScreen() {
        val nextScreenIntent = Intent(this, MainActivity::class.java)
        nextScreenIntent.putExtra(MainActivity.KEY_EXTRA_PLAYER1, binding.etPlayer1.text.toString())
        nextScreenIntent.putExtra(MainActivity.KEY_EXTRA_PLAYER2, binding.etPlayer2.text.toString())
        startActivityForResult(nextScreenIntent, LAST_GAME_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LAST_GAME_REQUEST_CODE && resultCode === RESULT_OK) {
            val player1Name = data?.getStringExtra("PLAYER1_NAME")
            val player2Name = data?.getStringExtra("PLAYER2_NAME")
            val player1Score = data?.getIntExtra("PLAYER1_SCORE", 0)
            val player2Score = data?.getIntExtra("PLAYER2_SCORE", 0)

            // binding.tvLastGame.setText("$player1Name $player1Score - $player2Score $player2Name")

            val lastGame = data?.getParcelableExtra<LastGame>("LAST_GAME")

            binding.tvLastGame.text = "${lastGame?.player1Name} ${lastGame?.player1Score} - ${lastGame?.player2Score} ${lastGame?.player2Name}"
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }
}