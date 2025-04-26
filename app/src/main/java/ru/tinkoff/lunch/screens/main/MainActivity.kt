package ru.tinkoff.lunch.screens.main

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.databinding.ActivityMainBinding
import ru.tinkoff.lunch.navigation.Screens
import ru.tinkoff.lunch.screens.main.di.MainComponent
import ru.tinkoff.lunch.utils.views.FlowActivity

@AndroidEntryPoint
class MainActivity : FlowActivity<MainComponent>(R.layout.activity_main) {

    companion object {
        fun createIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigationBar()
        router.replaceScreen(Screens.MainScreen())
    }

    private fun initNavigationBar() = with(binding.bottomBar) {
        itemActiveIndicatorColor =
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white))
        setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_history -> { true }
                R.id.menu_item_home -> { true }
                R.id.menu_item_profile -> { true }
                else -> false
            }
        }
    }
}
