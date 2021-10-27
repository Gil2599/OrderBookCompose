package com.example.orderbookcompose.presentation.coin_detail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.orderbookcompose.domain.model.Coin
import com.example.orderbookcompose.presentation.coin_list.components.SearchView

@Composable
fun OrderBookRow(
    size: Float,
    price: Float,
    isBuy: Boolean
) {

    Row(
        modifier = Modifier
            //.fillMaxSize()
            //.padding(20.dp)
            .border(
                width = 1.dp,
                color = if (isBuy) Color.Green else Color.Red,
                shape = RoundedCornerShape(5.dp)
            )
    ){

        Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.weight(1F)
            .align(CenterVertically)) {
            Text(
                text = size.toString(),
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1F)
                .align(CenterVertically)) {
            Text(
                text = price.toString(),
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    OrderBookRow(0.0005F, 13456.32F, false)
}