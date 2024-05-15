// Auth: John O'Neal
// Date: 05/09/2024
// Desc: This is the launch page of the app and where the first screen appears.

package com.example.lifesimapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lifesimapp.composables.AppButton
import com.example.lifesimapp.data.AppDatabase
import com.example.lifesimapp.ui.theme.LifeSimAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the database
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getDatabase(applicationContext)
            Log.d("WelcomeActivity", "Database initialization called from WelcomeActivity")
        }
        setContent {
            LifeSimAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Display the welcome screen
                    WelcomeScreen(
                        onStartNewGame = { startNewGame() },
                        onContinueGame = { continueGame() },
                        onLeaderboard = { showLeaderboard() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun WelcomeScreen(
        onStartNewGame: () -> Unit,
        onContinueGame: () -> Unit,
        onLeaderboard: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to LifeSim", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(16.dp))
            Text(text = "Choose an option to start", style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.height(16.dp))
            AppButton(text = "Start New Game", onClick = onStartNewGame)
            Spacer(Modifier.height(16.dp))
            AppButton(text = "Continue Game", onClick = onContinueGame)
            Spacer(Modifier.height(16.dp))
            AppButton(text = "Leaderboard", onClick = onLeaderboard)
        }
    }

    @Preview
    @Composable
    fun WelcomeScreenPreview() {
        LifeSimAppTheme {
            WelcomeScreen(
                onStartNewGame = {},
                onContinueGame = {},
                onLeaderboard = {}
            )
        }
    }

    fun startNewGame() {
        // Start the game activity
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun continueGame() {
        // TODO: Continue the game
    }

    fun showLeaderboard() {
        // TODO: Show the leaderboard activity
    }
}