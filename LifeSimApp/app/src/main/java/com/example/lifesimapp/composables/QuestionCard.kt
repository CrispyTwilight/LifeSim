// Auth: John O'Neal
// Date: 05/09/2024
// Desc: The composable which makes up the question card

package com.example.lifesimapp.composables

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lifesimapp.classes.Option
import com.example.lifesimapp.classes.Question

@Composable
fun QuestionCard(
    question: Question, onOptionSelected: (Option) -> Unit, modifier: Modifier = Modifier
) {
    Log.d(
        "QuestionCard",
        "Rendering QuestionCard for question: $question"
    )  // Log when QuestionCard is being rendered
    var selectedOption by remember { mutableStateOf<Option?>(null) }
    Log.d(
        "QuestionCard",
        "Question: $question, Selected option: $selectedOption"
    )  // Log the question and selected option
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(Alignment.CenterVertically),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = question.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = question.description,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))

            question.options.forEach { option ->
                Button(
                    onClick = {
                        selectedOption = option
                        onOptionSelected(option)
                        Log.d("QuestionCard", "Option clicked: $option")

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (
                            option == selectedOption)
                            MaterialTheme.colorScheme.tertiary
                        else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = option.text, textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun QuestionCardPreview() {
    val question =
        Question(title = "Question Title", description = "Question Description", options = listOf(
            "Here is a semi long option that might go out of view and word wrap",
            "Option 2",
            "Option 3"
        ).map {
            Option(
                it, happiness = 0, wealth = 0
            )
        })
    QuestionCard(question = question, onOptionSelected = {})
}