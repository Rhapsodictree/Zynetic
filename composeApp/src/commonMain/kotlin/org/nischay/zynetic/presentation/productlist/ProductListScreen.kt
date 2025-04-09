package org.nischay.zynetic.presentation.productlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import org.nischay.zynetic.domain.model.Product
import org.nischay.zynetic.presentation.components.LoadingView
import org.nischay.zynetic.presentation.components.ErrorView
import org.nischay.zynetic.presentation.components.NetworkImage

@Composable
fun ProductListScreen(onProductClick: (Int) -> Unit) {

    val viewModel: ProductListViewModel = koinInject()
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> LoadingView()
        state.error != null -> ErrorView(state.error ?: "") { viewModel.loadProducts() }
        else -> LazyColumn {
            items(state.products) { product ->
                ProductListItem(product) {
                    onProductClick(product.id)
                }
            }
        }
    }
}

@Composable
fun ProductListItem(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            NetworkImage(
                url = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}