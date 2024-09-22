package com.example.appssquaretask.presentation.screens.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
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
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onClickBack: () -> Unit,
    onLoginClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(viewModel.effect)
    val context = LocalContext.current

    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is SignUpReducer.Effect.Error -> {
                    Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show()
                }

                SignUpReducer.Effect.NavigateToLogin -> onLoginClick()
                SignUpReducer.Effect.NavigateToStart -> onClickBack()
            }
        }
    }

    SignUpScreenContent(
        state = state,
        onEvent = viewModel::sendEventForEffect,
        signUp = viewModel::signUp
    )
}

@Composable
private fun SignUpScreenContent(
    state: SignUpReducer.State,
    onEvent: (SignUpReducer.Event) -> Unit,
    signUp: (String, String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(background)
            .padding(horizontal = 27.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        Box(
            modifier = Modifier
                .padding(top = 21.dp)
                .fillMaxWidth()
                .align(Alignment.Start)
        ) {
            IconButton(
                onClick = { onEvent(SignUpReducer.Event.NavigateBackClicked) },
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.navigate_back),
                    Modifier.size(32.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = stringResource(id = R.string.app_logo),
                    Modifier.size(75.dp, 69.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.sign_up),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = stringResource(R.string.create_your_personal_profile_com_and_follow),
            modifier = Modifier
                .padding(top = 7.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodySmall,
            color = onSecondary
        )

        Spacer(modifier = Modifier.height(14.dp))

        MyTextField(
            value = state.name,
            onValueChange = { onEvent(SignUpReducer.Event.NameChanged(it)) },
            label = R.string.name,
            keyBoardType = KeyboardType.Text,
            placeholder = stringResource(R.string.name),
        )
        Spacer(modifier = Modifier.height(14.dp))
        MyTextField(
            value = state.email,
            onValueChange = { onEvent(SignUpReducer.Event.EmailChanged(it)) },
            label = R.string.email,
            keyBoardType = KeyboardType.Email,
            placeholder = stringResource(R.string.email),
        )
        Spacer(modifier = Modifier.height(14.dp))
        MyTextField(
            value = state.password,
            onValueChange = { onEvent(SignUpReducer.Event.PasswordChanged(it)) },
            label = R.string.password,
            keyBoardType = KeyboardType.Text,
            placeholder = stringResource(R.string.password),
        )
        Spacer(modifier = Modifier.height(14.dp))

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = state.termsAndConditions,
                onCheckedChange = { onEvent(SignUpReducer.Event.TermsAndConditionsChanged(it)) },
                colors = CheckboxDefaults.colors(
                    checkedColor = primary,
                    uncheckedColor = primary,
                )
            )
            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.terms_of_service))
                    append(" ")
                    withStyle(
                        style = MaterialTheme.typography.labelMedium.toSpanStyle().copy(
                            color = primary
                        )
                    ) {
                        append(stringResource(R.string.terms_of_service_and_privacy_policy))

                    }
                },
                modifier = Modifier.padding(start = 4.dp),
                style = MaterialTheme.typography.labelMedium
            )
        }

        Spacer(modifier = Modifier.height(47.dp))

        FilledButton(
            text = stringResource(R.string.sign_up),
            onClick = {
                if (state.termsAndConditions) {
                    signUp(state.name, state.email, state.password)
                }
            },
            enable = state.termsAndConditions,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.have_account),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = primary, textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier
                    .clickable { onEvent(SignUpReducer.Event.SignInClicked) },
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SignUpScreenPreview() {
    AppsSquareTaskTheme {
        SignUpScreenContent(
            onEvent = {},
            state = SignUpReducer.State(
                name = "",
                email = "",
                password = "",
                termsAndConditions = false,
                isLoading = false
            ),
            signUp = { _, _, _ -> }
        )
    }
}