package com.example.shoppinglist.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.Screen
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun LoginView(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit = {},
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
            // Título
            Text(
                text = "Login",
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

            // Botão de Login
            Button(
                onClick = {
                    viewModel.onLoginClick {
                        onLoginSuccess()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão de Registro
            Button(
                onClick = {
                    navController.navigate(Screen.Register.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Account")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Indicador de carregamento
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            }

            // Exibição de erro
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
fun LoginViewPreview() {
    ShoppingListTheme {
        LoginView(navController = rememberNavController())
    }
}
