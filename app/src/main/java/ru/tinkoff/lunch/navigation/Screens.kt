package ru.tinkoff.lunch.navigation

import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.tinkoff.lunch.root.EntrypointActivity
import ru.tinkoff.lunch.root.sign_in.SignInFragment
import ru.tinkoff.lunch.root.sign_up.SignUpFragment
import ru.tinkoff.lunch.screens.main.MainActivity
import ru.tinkoff.lunch.screens.main.ui.MainFragment
import ru.tinkoff.lunch.screens.profile.ProfileFragment

@Suppress("FunctionName")
object Screens {

    fun MainActivityScreen() = ActivityScreen { MainActivity.createIntent(it) }

    fun SignInDeepLogoutScreen() = ActivityScreen {
        EntrypointActivity.createIntent(it, isDeepLogout = true)
    }

    fun SignInScreen() = FragmentScreen { SignInFragment() }

    fun SignUpScreen() = FragmentScreen { SignUpFragment() }

    fun MainScreen() = FragmentScreen { MainFragment() }

    fun ProfileScreen() = FragmentScreen { ProfileFragment() }
}
