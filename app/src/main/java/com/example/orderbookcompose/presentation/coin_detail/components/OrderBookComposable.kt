package com.example.orderbookcompose.presentation.coin_detail.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.orderbookcompose.presentation.coin_detail.CoinOderBookState
import java.util.*

@Composable
fun OrderBookComposable(
    bookState: CoinOderBookState
) {
    Box(modifier = Modifier.fillMaxSize()) {

        if (bookState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        bookState.orderBook?.let {
            Column(modifier = Modifier.fillMaxSize()) {

                for (item in 0..9) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ) {

                        OrderBookRow(size = bookState.orderBook[item].second, price = bookState.orderBook[item].first, isBuy = false)

                    }

                }

            }
        }

    }

}

/*@Preview(showBackground = true)
@Composable
fun OrderBookPreview() {
    OrderBookComposable(listOf(
        Pair(1F, 6F),
        Pair(2F, 5F),
        Pair(3F, 6F),
        Pair(4F, 2F),
        Pair(5F, 5F),
        Pair(6F, 6F),
        Pair(7F, 2F),
        Pair(8F, 5F),
        Pair(9F, 6F),
        Pair(10F, 2F),
        Pair(11F, 5F),
        Pair(12F, 6F),
        Pair(13F, 2F)))
}*/