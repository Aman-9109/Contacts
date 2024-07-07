package com.example.contacts.screens.addContact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contacts.navigation.Routes

@Composable
fun AddContact(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel: AddContactViewModel = hiltViewModel()
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val number = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    val nameError = remember { mutableStateOf("") }
    val numberError = remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(
            onClick = { navController.navigateUp() },
            onDone = {
                val trimmedName = name.value.trim()
                val trimmedNumber = number.value.trim()
                if (trimmedName.isEmpty()) {
                    nameError.value = "Name cannot be empty"
                } else if (trimmedNumber.isEmpty()) {
                    numberError.value = "Phone number cannot be empty"
                } else {
                    viewModel.addContact(
                        name = trimmedName,
                        email = email.value.trim(),
                        address = address.value.trim(),
                        number = trimmedNumber
                    )
                    navController.navigate(Routes.Home)
                }
            })
    }) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Name", style = TextStyle(
                        fontWeight = FontWeight.W500,
                        fontSize = 17.sp,
                        color = Color.Black
                    ), modifier = Modifier.padding(start = 0.dp, bottom = 7.dp)
                )
                OutlinedTextField(
                    value = name.value,
                    onValueChange = {
                        name.value = it
                        nameError.value = "" // Clear error when user starts typing
                    },
                    placeholder = { Text(text = "Enter name", color = Color.Gray) },
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    isError = nameError.value.isNotEmpty()
                )
                if (nameError.value.isNotEmpty()) {
                    Text(
                        text = nameError.value,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 0.dp, bottom = 7.dp)
                    )
                }
                Text(
                    text = "Email", style = TextStyle(
                        fontWeight = FontWeight.W500,
                        fontSize = 17.sp,
                        color = Color.Black
                    ), modifier = Modifier.padding(start = 0.dp, bottom = 7.dp)
                )
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    placeholder = { Text(text = "Enter email", color = Color.Gray) },
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    )
                )
                Text(
                    text = "Phone number", style = TextStyle(
                        fontWeight = FontWeight.W500,
                        fontSize = 17.sp,
                        color = Color.Black
                    ), modifier = Modifier.padding(start = 0.dp, bottom = 7.dp)
                )
                OutlinedTextField(
                    value = number.value,
                    onValueChange = {
                        number.value = it
                        numberError.value = "" // Clear error when user starts typing
                    },
                    placeholder = { Text(text = "Enter Phone number", color = Color.Gray) },
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    isError = numberError.value.isNotEmpty()
                )
                if (numberError.value.isNotEmpty()) {
                    Text(
                        text = numberError.value,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 0.dp, bottom = 7.dp)
                    )
                }
                Text(
                    text = "Address", style = TextStyle(
                        fontWeight = FontWeight.W500,
                        fontSize = 17.sp,
                        color = Color.Black
                    ), modifier = Modifier.padding(start = 0.dp, bottom = 7.dp)
                )
                OutlinedTextField(
                    value = address.value,
                    onValueChange = { address.value = it },
                    placeholder = { Text(text = "Enter Address", color = Color.Gray) },
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(modifier: Modifier = Modifier, onClick: () -> Unit, onDone: () -> Unit) {
    androidx.compose.material3.TopAppBar(navigationIcon = {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                tint = Color.Black
            )
        }
    }, title = {
        Text(
            text = "Add Contact", style = TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 23.sp,
                color = Color.Black
            )
        )
    }, colors = TopAppBarDefaults.topAppBarColors(), actions = {
        IconButton(onClick = onDone) {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "search",
                tint = Color.Black
            )
        }
    })
}
