package com.shalan.jetpackcomposesample.composelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shalan.jetpackcomposesample.R
import com.shalan.jetpackcomposesample.ui.theme.JetPackComposeSampleTheme
import java.util.*

class BasicComposeLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeSampleTheme {
                ComposeLayoutRoot()
            }
        }
    }
}

@Composable
fun ComposeLayoutRoot() {
    Scaffold(bottomBar = { AppBottomNavigation() }) { paddingValues ->
        HomeScreen(modifier = Modifier.padding(paddingValues))
    }
}


@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        keyboardOptions = KeyboardOptions(autoCorrect = false, imeAction = ImeAction.Search),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                contentDescription = stringResource(id = R.string.search),
                imageVector = Icons.Default.Search
            )
        },
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colorScheme.surface)
    )
}

@Preview(name = "SearchBar Preview", backgroundColor = 0xFFF0EAE2)
@Composable
fun SearchBarPreview() {
    JetPackComposeSampleTheme {
        SearchBar(modifier = Modifier.padding(all = 8.dp))
    }
}

@Composable
fun AlignYourBody(@DrawableRes drawable: Int, @StringRes text: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "image",
            modifier = Modifier
                .size(88.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = text),
            style = androidx.compose.material.MaterialTheme.typography.h3,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Preview(name = "Align your body Preview", backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyPreview() {
    JetPackComposeSampleTheme {
        AlignYourBody(
            modifier = Modifier.padding(8.dp),
            drawable = R.drawable.ab1_inversions,
            text = R.string.ab1_inversions
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier, shape = androidx.compose.material.MaterialTheme.shapes.small) {
        Row(modifier = Modifier.width(192.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                modifier = Modifier.size(56.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = text),
                style = androidx.compose.material.MaterialTheme.typography.h3,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(name = "FavoriteCollectionCard Preview", backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionCardPreview() {
    JetPackComposeSampleTheme {
        FavoriteCollectionCard(
            modifier = Modifier.padding(all = 8.dp),
            drawable = R.drawable.fc2_nature_meditations,
            text = R.string.fc2_nature_meditations
        )
    }
}

@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBody(drawable = item.drawable, text = item.text)
        }
    }
}

@Preview(name = "Align your body row", backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyRowPreview() {
    JetPackComposeSampleTheme {
        AlignYourBodyRow()
    }
}

@Composable
fun FavoriteCollectionsGrid(modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.height(120.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(drawable = item.drawable, text = item.text)
        }
    }
}

@Preview(name = "FavoriteCollectionGridPreview")
@Composable
fun FavoriteCollectionGridPreview() {
    JetPackComposeSampleTheme {
        FavoriteCollectionsGrid()
    }
}

@Composable
fun HomeSection(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = text).uppercase(Locale.getDefault()),
            style = androidx.compose.material.MaterialTheme.typography.h2,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Preview(name = "Home Section Preview")
@Composable
fun HomeSectionPreview() {
    JetPackComposeSampleTheme {
        HomeSection(text = R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(state = rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(text = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(text = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(name = "Home Screen Preview")
@Composable
fun HomeScreenPreview() {
    JetPackComposeSampleTheme {
        HomeScreen()
    }
}

@Composable
fun AppBottomNavigation(modifier: Modifier = Modifier) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = androidx.compose.material.MaterialTheme.colors.background
    ) {

        BottomNavigationItem(selected = true, onClick = { /*TODO*/ }, icon = {
            Icon(imageVector = Icons.Default.Spa, contentDescription = null)
        }, label = {
            Text(text = stringResource(id = R.string.bottom_navigation_home))
        }, alwaysShowLabel = false)

        BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
        }, label = {
            Text(text = stringResource(id = R.string.bottom_navigation_profile))
        }, alwaysShowLabel = false)
    }
}

@Preview(name = "Bottom Navigation Preview")
@Composable
fun AppBottomNavigationPreview() {
    JetPackComposeSampleTheme {
        AppBottomNavigation()
    }
}

@Preview(name = "default")
@Composable
fun DefaultPreview() {
    JetPackComposeSampleTheme {
        ComposeLayoutRoot()
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)