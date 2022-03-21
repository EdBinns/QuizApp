package com.edbinns.quizapp.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(text: String, isStartBtn: Boolean = true, onClick: () -> Unit) {

    Button(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8036E9)),
        modifier = Modifier.padding(4.dp).width(200.dp).height(60.dp),
    ) {
        Text(
            text = text,
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun CustomInput(
    title: String = "",
    label:String = "Name",
    onTextChange: (String) -> Unit = {},
    onImeAction: () -> Unit = {},
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = title,
        onValueChange = onTextChange,
        label = { Text(text = label, color = Color.White) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = Color.White,

        ),
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = Modifier.padding(4.dp)
    )


}