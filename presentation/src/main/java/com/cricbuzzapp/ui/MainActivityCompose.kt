package com.cricbuzzapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cricbuzz.domain.model.RestaurantDomain
import com.cricbuzzapp.R

class MainActivityCompose {
    @Composable
    fun SetupMainActivityCompose() {
        val viewModel: MainActivityViewModel = viewModel()

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val (search_bar, search_results) = createRefs()

            val startGuideline = createGuidelineFromStart(0.015f)
            val endGuideline = createGuidelineFromEnd(0.015f)

            OutlinedTextField(
                modifier = Modifier
                    .constrainAs(search_bar) {
                        start.linkTo(startGuideline, 12.dp)
                        end.linkTo(endGuideline, 12.dp)
                        top.linkTo(parent.top, 32.dp)
                        width = Dimension.fillToConstraints
                    }
                    .defaultMinSize(minHeight = 48.dp),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium
                ),
                placeholder = {
                    Text(
                        text = "Search restaurants",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                },
                value = viewModel.searchText,
                shape = RoundedCornerShape(16.dp),
                onValueChange = {
                    viewModel.onSearchTextChanged(it)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    errorBorderColor = Color.Red,
                    cursorColor = Color.Black,
                    textColor = Color.Black
                ),
                trailingIcon = {
                    if (viewModel.searchText.isNotEmpty()) {
                        Image(
                            modifier = Modifier
                                .height(28.dp)
                                .width(28.dp)
                                .clickable {
                                    viewModel.updateSearchText("")
                                },
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Search field text clear icon"
                        )
                    }
                },
            )

            LazyColumn(modifier = Modifier
                .constrainAs(search_results) {
                    top.linkTo(search_bar.bottom, 20.dp)
                    start.linkTo(startGuideline, 12.dp)
                    end.linkTo(endGuideline, 12.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }
                .fillMaxWidth()
            ) {
                viewModel.restaurantList.forEachIndexed { _, restaurantDomain ->
                    item {
                        SearchItem(restaurantDomain)
                    }
                }
            }
        }
    }

    @Composable
    fun SearchItem(data: RestaurantDomain?) {
        Card {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "ID : " + data?.id,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                )
                Text(
                    text = "Name : " + data?.name,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                )
                Text(
                    text = "Cuisine type : " + data?.cuisine_type,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                )

                Text(
                    text = "Reviews are :",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 10.dp, top = 15.dp)
                )

                data?.reviews?.forEachIndexed { _, reviewDomain ->
                    Text(
                        text = "Name: " +reviewDomain.name,
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 15.dp, top = 20.dp)
                    )

                    Text(
                        text = "Rating : " + reviewDomain.rating,
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 14.dp, top = 10.dp)
                    )

                    Text(
                        text = "Comment :" + reviewDomain.comment,
                        fontSize = 14.sp,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 14.dp, top = 10.dp, bottom = 10.dp)
                    )
                }

            }
        }
    }
}