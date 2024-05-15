// Auth: Hunter Kauffman
// Date: 05/09/2024
// Desc: This is the result screen that shows what the player ended with

package com.example.lifesimapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.lifesimapp.ui.theme.LifeSimAppTheme

class ResultsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LifeSimAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ResultsScreen(
                        name = "",
                        result = "",
                        wealth = 0,
                        happiness = 0,
                        onTryAgain = { tryAgain() },
                        onMainMenu = { mainMenu() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    @Composable
    fun ResultsScreen(
        name: String,
        result: String,
        wealth: Int,
        happiness: Int,
        onTryAgain: () -> Unit,
        onMainMenu: () -> Unit,
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
            Text(
                text = "Results!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "$name you $result the game.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Wealth: $wealth",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Happiness: $happiness",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(16.dp))
            AppButton(text = "Try Again", onClick = onTryAgain)
            Spacer(Modifier.height(16.dp))
            AppButton(text = "Main Menu", onClick = onMainMenu)


        }

    }

    @Preview(showBackground = true)
    @Composable
    fun ResultsScreenPreview() {
        LifeSimAppTheme {
            ResultsScreen("Hunter", "Won", 0, 0, onTryAgain = {}, onMainMenu = {})
        }
    }

    fun tryAgain() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun mainMenu() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }
}