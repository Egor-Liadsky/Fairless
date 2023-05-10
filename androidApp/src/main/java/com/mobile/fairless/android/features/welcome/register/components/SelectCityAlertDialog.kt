package com.mobile.fairless.android.features.welcome.register.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mobile.fairless.android.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.views.topBars.SearchTopBar
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.welcome.dto.City
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel


@Composable
fun SelectCityAlertDialog(
    viewModelWrapper: ViewModelWrapper<RegisterViewModel>,
    cities: List<City>?,
    isOpen: Boolean,
    cityChanged: (City) -> Unit,
    onValueChanged: () -> Unit
) {

    val state = viewModelWrapper.viewModel.state.collectAsState()

    var status by remember {
        mutableStateOf(isOpen)
    }

    var sortedCities by remember {
        mutableStateOf(cities?.map { it.name.contains(state.value.search.toString()) }
        )
    }

    Log.e("ajshdasdad", sortedCities.toString())

    if (isOpen) {
        Dialog(
            onDismissRequest = {
                status = false
                onValueChanged()
            },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(
                color = colors.backgroundWelcome, modifier = Modifier.fillMaxSize()
            ) {
                Column(Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
                    SearchTopBar(
                        searchString = state.value.search ?: "",
                        onClearText = { },
                        onMicClick = { },
                        onSearchClick = {},
                        onSearchChange = { viewModelWrapper.viewModel.searchChanged(it) }
                    )
                    Spacer(modifier = Modifier.padding(bottom = 15.dp))

                    LazyColumn {
                        if (cities != null) {
                            items(items = cities.map {
                                if (it.name.lowercase()
                                        .contains(state.value.search.toString().lowercase())
                                ) it else null
                            }) { item ->
                                if (item != null) {
                                    CityCardView(city = item.name) {
                                        cityChanged(item)
                                        onValueChanged()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CityCardView(city: String, onClick: () -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable { onClick() },
        elevation = 2.dp,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = city,
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = colors.black
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_next), contentDescription = "ic_next",
                tint = colors.orangeMain,
                modifier = Modifier.size(15.dp)
            )
        }
    }
}
