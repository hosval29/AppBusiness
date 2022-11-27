package com.example.appbusiness.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.appbusiness.R
import com.example.appbusiness.domain.model.Business
import com.gowtham.ratingbar.RatingBar

@Composable
fun BusinessItem(
    modifier: Modifier = Modifier,
    business: Business,
    onDeleteBusiness: () -> Unit,
    onRatingBusiness: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        ConstraintLayout {
            val (name, phone, about, brand, boxRating, boxActions) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(name) {
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        top.linkTo(parent.top, margin = 16.dp)
                        width = Dimension.fillToConstraints
                    },
                text = business.name,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )

            Text(
                modifier = Modifier
                    .constrainAs(phone) {
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        top.linkTo(name.bottom, margin = 4.dp)
                        width = Dimension.fillToConstraints
                    },
                text = business.phone,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            )

            Text(
                modifier = Modifier
                    .constrainAs(about) {
                        start.linkTo(parent.start, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        top.linkTo(phone.bottom, margin = 4.dp)
                        width = Dimension.fillToConstraints
                    },
                text = business.about,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp
                )
            )

            Row(
                modifier = Modifier.constrainAs(boxRating) {
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end)
                    top.linkTo(about.bottom, margin = 4.dp)
                    width = Dimension.fillToConstraints
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingBar(
                    value = business.rating,
                    onValueChange = {},
                    onRatingChanged = {}
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = business.countRating.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp
                    )
                )
            }

            Image(
                modifier = Modifier.constrainAs(brand) {
                    end.linkTo(parent.end, margin = 16.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                },
                painter = painterResource(id = R.drawable.business_icon),
                contentDescription = "Business"
            )

            Row(
                modifier = Modifier
                    .constrainAs(boxActions) {
                        top.linkTo(boxRating.bottom, margin = 12.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                    }
            ) {
                Button(
                    onClick = {
                        onRatingBusiness()
                    },
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colors.primary
                    )
                ) {
                    Image(
                        imageVector = Icons.Default.StarHalf,
                        contentDescription = "Rating Business",
                        modifier = Modifier.wrapContentSize()
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "Calificar",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(
                    onClick = {
                        onDeleteBusiness()
                    },
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colors.primary
                    )
                ) {
                    Image(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Business",
                        modifier = Modifier.wrapContentSize()
                    )
                    Text(
                        "Eliminar",
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

