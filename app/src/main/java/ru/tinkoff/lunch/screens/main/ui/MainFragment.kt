package ru.tinkoff.lunch.screens.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.runtime.getValue
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.lunch.navigation.Screens.LunchDetailsScreen
import ru.tinkoff.lunch.screens.main.ui.compose.MainFragmentScreen
import ru.tinkoff.lunch.screens.main.ui.presentation.MainFragmentNews
import ru.tinkoff.lunch.screens.main.ui.presentation.MainFragmentViewModel
import ru.tinkoff.lunch.utils.views.showAlertSnackbar
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    val viewModel = hiltViewModel<MainFragmentViewModel>()
                    val state by viewModel.uiState.collectAsStateWithLifecycle()

                    MainFragmentScreen(
                        uiState = state,
                        onEvent = viewModel::onEvent,
                    )

                    LaunchedEffect(Unit) {
                        viewModel.news.collect { news ->
                            when (news) {
                                is MainFragmentNews.OpenLunchDetailsScreen -> {
                                    router.navigateTo(LunchDetailsScreen(news.id))
                                }
                                is MainFragmentNews.ShowError -> {
                                    showAlertSnackbar(message = news.error.message)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
