// Auth: John O'Neal
// Date: 05/09/2024
// Desc: This class defines the game, which is the main content of the app. It will allow the user to swipe through questions and answer them.

package com.example.lifesimapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.lifesimapp.classes.Question
import com.example.lifesimapp.composables.QuestionCard
import com.example.lifesimapp.ui.theme.LifeSimAppTheme
import com.example.lifesimapp.viewmodels.GameViewModel

class GameActivity : ComponentActivity() {
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        Log.d("GameActivity", "ViewModel created")  // Log when the ViewModel is created
        viewModel.loadQuestions(this)

        setContent {
            LifeSimAppTheme {
                val questions by viewModel.questions.collectAsState()
                Log.d("GameActivity", "Questions: $questions")  // Log the questions
                if (questions.isNotEmpty()) {
                    Log.d(
                        "GameActivity",
                        "Rendering GameScreen"
                    )  // Log when GameScreen is being rendered
                    GameScreen(questions, viewModel)
                }
            }
        }
    }
}

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)

@Composable
fun GameScreen(questions: List<Question>, viewModel: GameViewModel) {
    val context = LocalContext.current
    val pagerState = rememberPagerState(
        initialPage = 0,  // Start at the first question
        pageCount = { questions.size },
        initialPageOffsetFraction = 0f
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val intent = Intent(context, WelcomeActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Life Sim")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            }
        ) { contentPadding ->
            HorizontalPager(state = pagerState) { page ->
                QuestionCard(
                    question = questions[page],
                    onOptionSelected = { option -> viewModel.selectOption(option) },
                    modifier = Modifier.padding(contentPadding)
                )
            }
        }
    }
}