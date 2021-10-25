package com.example.orderbookcompose.presentation.coin_detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = size.toString(),
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            modifier = Modifier.align(Alignment.CenterVertically)
                .weight(1F)
        )
        Text(
            text = price.toString(),
            color = if (isBuy) Color.Green else Color.Red,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.CenterVertically)
                .weight(1F)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    OrderBookRow(0.0005F, 13456.32F, false)
}