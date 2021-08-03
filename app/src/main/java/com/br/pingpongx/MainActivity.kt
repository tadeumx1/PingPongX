package com.br.pingpongx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.pingpongx.databinding.ActivityMainBinding
import com.br.pingpongx.model.LastGame

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var playerOneScore = 0
    private var playerTwoScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            playerOneScore = savedInstanceState.getInt("PLAYER_ONE_SCORE", 0)
            playerTwoScore = savedInstanceState.getInt("PLAYER_TWO_SCORE", 0)
            setUpPlayerOneScore()
            setUpPlayerTwoScore()
        }

        setUpPlayers()

        setUpListeners()
    }

    private fun setUpPlayerOneScore() {
        binding.tvPlayerOneScore.text = playerOneScore.toString()
    }

    private fun setUpPlayerTwoScore() {
        binding.tvPlayerTwoScore.text = playerTwoScore.toString()
    }

    private fun setUpListeners() {
        binding.btPlayerOneScore.setOnClickListener {
            playerOneScore++
            binding.tvPlayerOneScore.text = playerOneScore.toString()
        }

        binding.btPlayerTwoScore.setOnClickListener {
            playerTwoScore++
            binding.tvPlayerTwoScore.text = playerTwoScore.toString()
        }

        binding.btRevenge.setOnClickListener {
            init()
        }

        binding.btFinishMatch.setOnClickListener {
            returnLastGame()
            finish()
        }
    }

    private fun returnLastGame() {
        val returnIntent = Intent()
        returnIntent.putExtra("PLAYER1_NAME", binding.tvPlayerOneName.text)
        returnIntent.putExtra("PLAYER2_NAME", binding.tvPlayerTwoName.text)
        returnIntent.putExtra("PLAYER1_SCORE", playerOneScore)
        returnIntent.putExtra("PLAYER2_SCORE", playerTwoScore)

        val lastGame = LastGame(
            binding.tvPlayerOneName.text.toString(),
            binding.tvPlayerTwoName.text.toString(),
            playerOneScore,
            playerTwoScore
        )

        returnIntent.putExtra("LAST_GAME", lastGame)

        setResult(RESULT_OK, returnIntent)
        finish()
    }

    private fun init() {
        playerOneScore = 0
        playerTwoScore = 0
        setUpPlayerOneScore()
        setUpPlayerTwoScore()
    }

    private fun setUpPlayers() {
        binding.tvPlayerOneName.text = intent.getStringExtra(KEY_EXTRA_PLAYER1)
        binding.tvPlayerTwoName.text = intent.getStringExtra(KEY_EXTRA_PLAYER2)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("PLAYER_ONE_SCORE", playerOneScore)
        outState.putInt("PLAYER_TWO_SCORE", playerTwoScore)

    }

    companion object {
        val KEY_EXTRA_PLAYER1 = "KEY_EXTRA_PLAYER1"
        val KEY_EXTRA_PLAYER2 = "KEY_EXTRA_PLAYER2"
    }
}