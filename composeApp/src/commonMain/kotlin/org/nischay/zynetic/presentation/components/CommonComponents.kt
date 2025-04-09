package org.nischay.zynetic.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
fun NetworkImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    Box(modifier = modifier) {
//implementation simple karo
        val painter = rememberImagePainter(url)

        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize()
        )
// loading pehle launch baadme
        LaunchedEffect(url) {

        }
    }
}

@Composable
fun ImageCarousel(
    imageUrls: List<String>,
    modifier: Modifier = Modifier
) {
    val limitedImageUrls = imageUrls.take(6)
    Box(modifier = modifier) {
        if (limitedImageUrls.isEmpty()) {
            Text("No images available", style = MaterialTheme.typography.bodyLarge)
        } else {
            val pagerState = rememberPagerState(pageCount = { limitedImageUrls.size })
            HorizontalPager(state = pagerState) { page ->
                NetworkImage(
                    url = limitedImageUrls[page],
                    contentDescription = "Product image ${page + 1}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun RatingBar(
    rating: Double,
    maxRating: Int = 5,
    modifier: Modifier = Modifier
) {
    val safeRating = rating.coerceIn(0.0, maxRating.toDouble())
    Row(modifier = modifier) {
        for (i in 1..maxRating) {
            val icon = when {
                i <= floor(safeRating) -> Icons.Filled.Star
                i > ceil(safeRating) -> Icons.Outlined.Star
                else -> Icons.Filled.Star // Simplified - use filled star for half stars too
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}