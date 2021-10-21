package com.example.orderbookcompose.presentation.coin_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.orderbookcompose.domain.model.Coin
import com.example.orderbookcompose.presentation.Screen
import com.example.orderbookcompose.presentation.coin_list.components.CoinListItem
import com.example.orderbookcompose.presentation.coin_list.components.SearchView
import java.util.*

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val textFieldState = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val coins = state.coins
    var filteredCoins: ArrayList<Coin>

    Column(modifier = Modifier.fillMaxSize()) {

        SearchView(state = textFieldState)

        filteredCoins = if (textFieldState.value.text.isEmpty()) {
            ArrayList(coins)
        } else {
            val resultList = ArrayList<Coin>()
            for (coin in coins){
                if (coin.name.lowercase(Locale.getDefault())
                        .contains(textFieldState.value.text.lowercase(Locale.getDefault())) or
                        coin.symbol.lowercase(Locale.getDefault())
                            .contains(textFieldState.value.text.lowercase(Locale.getDefault()))){
                    resultList.add(coin)
                }
            }
            resultList
        }

        Box(modifier = Modifier.fillMaxSize()){
            LazyColumn(modifier = Modifier.fillMaxSize()){

                items(filteredCoins){ coin ->
                    CoinListItem(
                        coin = coin,
                        onItemClick = {
                            navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                        })
                }
            }
            if (state.error.isNotBlank()){
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center))
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}