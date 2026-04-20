package com.example.lazycolumns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.lazycolumns.ui.theme.LazyColumnsTheme
import ug.ac.ndejje.ronald.Student
import ug.ac.ndejje.ronald.StudentProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StudentListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun StudentListScreen(modifier: Modifier = Modifier) {
    val students = StudentProvider.studentList
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(students) { student ->
            StudentRow(student)
        }
    }
}

@Composable
fun StudentRow(student: Student) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = student.imageRes),
                contentDescription = stringResource(id = R.string.profile_picture_content_description),
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(text = student.name, style = MaterialTheme.typography.titleMedium)
                Text(text = student.registrationNumber, style = MaterialTheme.typography.bodySmall)
                Text(text = student.course, style = MaterialTheme.typography.bodySmall)
            }
            if (student.isPresent) {
                Text(
                    text = stringResource(id = R.string.button_present),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge
                )
            } else {
                Button(onClick = { /* Mark Present action */ }) {
                    Text(text = stringResource(id = R.string.button_mark_present))
                }
            }
        }
    }
}
