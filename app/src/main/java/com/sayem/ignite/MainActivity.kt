package com.sayem.ignite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sayem.ignite.ui.screen.MainViewModel
import com.sayem.ignite.ui.screen.Screen
import com.sayem.ignite.ui.screen.dashboard.DashboardScreen
import com.sayem.ignite.ui.screen.splash.SplashScreen
import com.sayem.ignite.ui.theme.ComposeTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ComposeTemplateTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ComposeTemplateTheme {
//        Greeting("Android")
//    }
//}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                Surface {
                    AppNavigation()
                }
            }
        }
    }

    @Composable
    private fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.Splash.route) {

            // Splash
            composable(Screen.Splash.route) {
                SplashScreen(
                    onSplashFinished = {
                        val options = NavOptions.Builder()
                            .setPopUpTo(Screen.Splash.route, inclusive = true)
                            .build()
                        navController.navigate(
                            Screen.Dashboard.route,
                            options
                        ) // Move to dashboard
                    }
                )
            }

            // Dashboard
            composable(Screen.Dashboard.route) {
                DashboardScreen()
            }
        }
    }
}