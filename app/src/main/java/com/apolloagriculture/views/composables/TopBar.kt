package com.apolloagriculture.views.composables

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.apolloagriculture.views.theme.ApolloAgricultureTheme

@Composable
fun TopBar() {
    TopAppBar(title = { Text("Apollo Agriculture") })
}

@Preview
@Composable
fun TopBarPreview() {
    ApolloAgricultureTheme {
        TopBar()
    }
}

@Preview
@Composable
fun TopBarDarkModePreview() {
    ApolloAgricultureTheme(darkTheme = true) {
        TopBar()
    }
}