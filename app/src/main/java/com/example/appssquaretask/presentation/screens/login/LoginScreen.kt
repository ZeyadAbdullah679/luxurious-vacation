package com.example.appssquaretask.presentation.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.appssquaretask.R
import com.example.appssquaretask.presentation.components.FilledButton
import com.example.appssquaretask.presentation.components.MyTextField
import com.example.appssquaretask.presentation.theme.AppsSquareTaskTheme
import com.example.appssquaretask.presentation.theme.background
import com.example.appssquaretask.presentation.theme.onSecondary
import com.example.appssquaretask.presentation.theme.primary
import com.example.appssquaretask.presentation.utils.rememberFlowWithLifecycle

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(viewModel.effect)
    val context = LocalContext.current

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                LoginReducer.LoginEffect.NavigateToHome -> {
                    navigateToHome()
                }

                LoginReducer.LoginEffect.NavigateToSignUp -> {
                    navigateToSignUp()
                }

                is LoginReducer.LoginEffect.Error -> {
                    Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LoginScreenContent(
        state = state,
        onEvent = viewModel::sendEventForEffect,
        login = viewModel::login
    )
}

@Composable
private fun LoginScreenContent(
    state: LoginReducer.LoginState,
    onEvent: (LoginReducer.LoginEvent) -> Unit,
    login: (String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(background)
            .padding(horizontal = 27.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            modifier = Modifier
                .padding(top = 21.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.app_logo),
                Modifier.size(75.dp, 69.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.login),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = stringResource(R.string.enter_to_your_personal_profile_com_and_follow),
            modifier = Modifier
                .padding(top = 7.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodySmall,
            color = onSecondary
        )

        Spacer(modifier = Modifier.height(14.dp))

        MyTextField(
            value = state.email,
            onValueChange = { onEvent(LoginReducer.LoginEvent.EmailChanged(it)) },
            label = R.string.email,
            keyBoardType = KeyboardType.Phone,
            placeholder = stringResource(R.string.email),
        )

        Spacer(modifier = Modifier.height(14.dp))
        MyTextField(
            value = state.password,
            onValueChange = { onEvent(LoginReducer.LoginEvent.PasswordChanged(it)) },
            label = R.string.password,
            keyBoardType = KeyboardType.Text,
            placeholder = stringResource(R.string.password),
        )


        Spacer(modifier = Modifier.height(47.dp))

        FilledButton(
            text = stringResource(R.string.login),
            onClick = { login(state.email, state.password) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
        Spacer(modifier = Modifier.height(47.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.dont_have_account),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.sign_up),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = primary, textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier
                    .clickable { onEvent(LoginReducer.LoginEvent.SignUpClicked) },
            )
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    AppsSquareTaskTheme {
        LoginScreenContent(
            state = LoginReducer.LoginState("", "", false),
            onEvent = {},
            login = { _, _ -> }
        )
    }
}