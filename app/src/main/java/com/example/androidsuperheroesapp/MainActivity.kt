package com.example.androidsuperheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidsuperheroesapp.data.Datasource
import com.example.androidsuperheroesapp.model.Hero
import com.example.androidsuperheroesapp.ui.theme.AndroidSuperHeroesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidSuperHeroesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperHeroesApp()
                }
            }
        }
    }
}


/**
 * App composable contains all the app components and main layout
 */
@Composable
fun SuperHeroesApp(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopHeroBar() },
        modifier = modifier,
        contentWindowInsets = WindowInsets(
            top = dimensionResource(id = R.dimen.medium_padding),
            left = dimensionResource(id = R.dimen.medium_padding),
            right = dimensionResource(id = R.dimen.medium_padding)
        )
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_padding))
        ) {
            items(Datasource().superHeroes) { hero ->
                HeroCard(hero = hero)
            }
        }
    }
}

/*
* TopAppBar
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeroBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier = modifier,
    )
}


/*
* HeroCard composable
* */
@Composable
fun HeroCard(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.elevation))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.medium_padding))
                .height(dimensionResource(id = R.dimen.list_height)),
        ) {
            CardContent(
                name = hero.name,
                description = hero.description,
                modifier = Modifier.weight(1f)
            )
            CardImage(image = hero.image)
        }
    }
}


/*
* CardContent composable
* */
@Composable
fun CardContent(@StringRes name: Int, @StringRes description: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = name),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(id = description),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

/*
* CardImage composable
* */
@Composable
fun CardImage(@DrawableRes image: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = modifier
            .size(dimensionResource(id = R.dimen.image))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuperHeroesAppPreview() {
    AndroidSuperHeroesAppTheme(darkTheme = false) {
        SuperHeroesApp()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuperHeroesAppDarkPreview() {
    AndroidSuperHeroesAppTheme(darkTheme = true) {
        SuperHeroesApp()
    }
}
