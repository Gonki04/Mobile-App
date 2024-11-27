package com.example.shoppinglist.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.Screen
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun RegisterView(
    modifier: Modifier = Modifier,
    onRegisterSucess: () -> Unit = {},
    navController: NavController
) {
    val viewModel: LoginViewModel = viewModel()
    val state = viewModel.state.value

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Campo de email
            TextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEmailChange(it)
                },
                placeholder = { Text("Email Address") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.error?.contains("email") == true
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de senha
            TextField(
                value = state.password,
                onValueChange = {
                    viewModel.onPasswordChange(it)
                },
                placeholder = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                isError = state.error?.contains("password") == true
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de confirmar senha
            TextField(
                value = state.password,
                onValueChange = {
                    viewModel.onPasswordChange(it)
                },
                placeholder = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                isError = state.error?.contains("confirm") == true
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Botão de registro
            Button(
                onClick = {
                    viewModel.onRegisterClick {
                        onRegisterSucess()
                        navController.navigate(Screen.Login.route)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading
            ) {
                Text("Register")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            }
            state.error?.let { errorMessage ->
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterViewPreview() {
    ShoppingListTheme {
        RegisterView(navController = rememberNavController())
    }
}
