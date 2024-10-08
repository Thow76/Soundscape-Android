package org.scottishtecharmy.soundscape.screens.markers_routes.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.scottishtecharmy.soundscape.R
import org.scottishtecharmy.soundscape.screens.MainScreens
import org.scottishtecharmy.soundscape.ui.theme.SoundscapeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkersAndRoutesAppBar(showAddIcon: Boolean, navController: NavController) {

    val context = LocalContext.current
    val notAvailableText = "This is not implemented yet."
    val notAvailableToast = {
        Toast.makeText(context, notAvailableText, Toast.LENGTH_SHORT).show()

    }
    CenterAlignedTopAppBar(
        navigationIcon = {
            CustomIconButton(
                modifier = Modifier.width(80.dp),
                iconModifier = Modifier.size(40.dp),
                onClick = {
//                    navController.popBackStack(MainScreens.Home.route, false)
                    navController.navigate(MainScreens.Home.route) {
                        popUpTo(MainScreens.Home.route) {
                            inclusive = false  // Ensures Home screen is not popped from the stack
                        }
                        launchSingleTop = true  // Prevents multiple instances of Home
                    }
                },
                iconText = stringResource(R.string.markers_and_routes_back_button_label),
                contentDescription = stringResource(R.string.markers_and_routes_back_button_description),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        },
        actions = {
            // Only show add route icon when on the routes tab
            AnimatedVisibility(
                visible = showAddIcon,
                enter = fadeIn() + expandHorizontally(),
                exit = fadeOut() + shrinkHorizontally()
            ) {
                CustomIconButton(
                    modifier = Modifier.defaultMinSize(48.dp),
                    iconModifier = Modifier.size(30.dp),
                    onClick = {navController.navigate(MainScreens.AddRoute.route)},
                    icon = Icons.Default.Add,
                    contentDescription = stringResource(R.string.markers_and_routes_add_route_button_description),
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = {
            CustomAppBarTitle(
                modifier = Modifier.semantics { contentDescription =
                    context.getString(R.string.marker_and_routes_screen_title_description) },
                title = stringResource(R.string.markers_routes_app_bar_title),
                contentAlignment = Alignment.Center
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MarkersAndRoutesAppBarPreview() {
    SoundscapeTheme {
        MarkersAndRoutesAppBar(
            showAddIcon = true,
            navController = rememberNavController()
        )
    }

}