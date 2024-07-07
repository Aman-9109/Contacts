package com.example.contacts.screens.home

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.contacts.R
import com.example.contacts.navigation.Routes
import com.example.contacts.room.Contact
import com.example.contacts.state.ContactState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview
fun Home(
    modifier: Modifier = Modifier,
    navController: NavHostController = NavHostController(LocalContext.current)
) {
    val context = LocalContext.current


    Scaffold(
        topBar = { TopAppBar() },
        floatingActionButton = { FAB(onClick = { navController.navigate(Routes.AddContact) }) },
        floatingActionButtonPosition = FabPosition.EndOverlay,

        ) { paddingValues ->
        val viewModel: HomeViewModel = hiltViewModel()
        val contacts = viewModel.contact.collectAsState()

        if (contacts.value == emptyList<ContactState>()) {
            EmptyContactDisplay(paddingValues)

        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                items(contacts.value) {
                    Log.d("AMAN", "Home:${it.id} ")
                    EachContact(
                        contact = it,
                        onCall = {
                            val intent = Intent(Intent.ACTION_CALL).apply {
                                data = android.net.Uri.parse("tel:${it.number}")
                                context.startActivity(this)
                            }
                        },
                        onEdit = { navController.navigate(Routes.Detail(contactId = it.id)) },
                        onDelete = { viewModel.deleteContact(it) })
                }

            }

        }


    }


}

@Composable
private fun ContactList(
    paddingValues: PaddingValues,
    state: State<List<Contact>>,
    onCall: () -> Unit,
    onDelete: () -> Unit
) {

}

@Composable
private fun EmptyContactDisplay(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = "",
            modifier = Modifier.size(170.dp)
        )
        Text(text = "You have no contacts yet", color = Color.Gray)

    }
}

@Composable

fun EachContact(
    modifier: Modifier = Modifier,
    contact: Contact, onCall: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = { onEdit.invoke() })
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "",
            modifier = Modifier.size(60.dp)
        )
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(0.65f)
        ) {
            Text(
                text = contact.name,
                fontWeight = FontWeight.W500,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
            Text(text = contact.number, color = Color.Gray)

        }
        IconButton(onClick = onCall) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "",
                tint = Color.Blue,
                modifier = Modifier.size(40.dp)
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.size(60.dp)
            )
        }


    }

}


@Composable
fun FAB(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        shape = CircleShape,
        modifier = Modifier
            .padding(bottom = 35.dp, end = 10.dp)
            .size(68.dp),
        onClick = { onClick.invoke() },
        containerColor = Color(0xff00B2FF)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.White)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(modifier: Modifier = Modifier) {
    androidx.compose.material3.TopAppBar(navigationIcon = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "", tint = Color.Black)

        }
    }, title = {
        Text(
            text = "Contacts", style = TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 23.sp,
                color = Color.Black
            )
        )
    }, colors = TopAppBarDefaults.topAppBarColors(), actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search",
                tint = Color.Black
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "more vertical",
                tint = Color.Black
            )
        }
    })

}