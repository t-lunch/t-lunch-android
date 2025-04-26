package ru.tinkoff.lunch.navigation

import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.tinkoff.lunch.root.sign_in.SignInFragment
import ru.tinkoff.lunch.root.sign_up.SignUpFragment
import ru.tinkoff.lunch.screens.main.MainActivity
import ru.tinkoff.lunch.screens.main.ui.MainFragment

@Suppress("FunctionName")
object Screens {

    fun SignInScreen() = FragmentScreen { SignInFragment() }

    fun SignUpScreen() = FragmentScreen { SignUpFragment() }

    fun MainScreen() = FragmentScreen { MainFragment() }

    fun MainActivityScreen() = ActivityScreen { MainActivity.createIntent(it) }
}
