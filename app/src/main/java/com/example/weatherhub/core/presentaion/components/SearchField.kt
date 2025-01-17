package com.example.weatherhub.core.presentaion.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.weatherhub.R
import com.example.weatherhub.core.mics.Action
import com.example.weatherhub.core.mics.Consumer

@Composable
fun SearchField(
    searchQuery: String,
    shouldHideKeyboard: Boolean,
    onQueryChanged: Consumer<String>,
    onSearchClicked: Action
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchQuery,
            onValueChange = { onQueryChanged(it) },
            placeholder = {
                Text(text = stringResource(R.string.search_field))
            },
            modifier = Modifier
                .focusRequester(focusRequester)
                .weight(1f),
            singleLine = true,
            keyboardActions = KeyboardActions {
                onSearchClicked()
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        )

        IconButton(
            onClick = {
                onSearchClicked()
            },
            modifier = Modifier.size(38.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                modifier = Modifier.size(38.dp)
            )
        }
    }
    LaunchedEffect(shouldHideKeyboard) {
        if (shouldHideKeyboard) {
            focusRequester.freeFocus()
            keyboard?.hide()
        }
    }
}