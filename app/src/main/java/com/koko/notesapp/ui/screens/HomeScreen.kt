package com.koko.notesapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.koko.notesapp.database.Note
import com.koko.notesapp.routes.Route.ADD_NOTE
import com.koko.notesapp.ui.viewmodels.NoteViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.koko.notesapp.routes.Route

@Composable
fun HomeScreen(
    navController: NavController,
               modifier: Modifier = Modifier,
    viewModel: NoteViewModel = viewModel ()
) {
        Scaffold(
            topBar = { Text(text = "Notes App",modifier=modifier.padding(top = 32.dp),)},
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(ADD_NOTE)
                    },
                    containerColor = Color.Blue,
                ) {
                    Icon(
                        Icons.Filled.Add,
                        "Add note page button",
                        tint = Color.White,
                    )
                }
            }
        ){_->
            val notes by viewModel.getNotes().collectAsState(initial = emptyList())

          LazyColumn (modifier = modifier.padding(top = 64.dp)){
              items(notes){
                  note->
                  NoteListItem(note = note, onNavigate = {
                      navController.navigate("${Route.EDIT_NOTE}/${note.title}/${note.id}/${note.details}")
                  })

              }
          }
        }


}
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
@Composable
fun NoteListItem(note: Note, modifier: Modifier = Modifier, onNavigate: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onNavigate() }
    ) {
        Text(
            text = note.title,
            fontSize = 24.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Justify,
            modifier = modifier

                .defaultMinSize(minHeight = 40.dp)
                .wrapContentHeight(Alignment.CenterVertically)
        )
        Spacer(modifier = modifier.fillMaxWidth() )
        Text(
            text = note.details,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 20.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Justify,
            modifier = modifier

                .padding(8.dp)
                .defaultMinSize(minHeight = 20.dp)
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
}
