package ru.tinkoff.lunch.screens.main.ui

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.lunch.screens.main.di.MainComponent
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.databinding.FragmentMainBinding
import ru.tinkoff.lunch.screens.main.presentation.MainNews
import ru.tinkoff.lunch.screens.main.ui.mapper.MainUiState
import ru.tinkoff.lunch.screens.main.ui.recycler.MainFragmentHolderFactory
import ru.tinkoff.lunch.utils.views.FlowFragment
import ru.tinkoff.lunch.utils.views.showAlertSnackbar
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

@AndroidEntryPoint
class MainFragment : FlowFragment<MainComponent>(R.layout.fragment_main) {

    private val store by storeViaViewModel { component.getMainStore() }
    private val binding by viewBinding(FragmentMainBinding::bind)

    private lateinit var recycler: TiRecyclerCoroutines<ViewTyped>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            fragment = this,
            uiStateMapper = component.uiStateMapper,
            stateCollector = ::render,
            newsCollector = ::news,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        recycler = TiRecyclerCoroutines(
            binding.recyclerView,
            MainFragmentHolderFactory()
        )
    }

    private fun render(state: MainUiState) {
        recycler.setItems(items = state.items)
        controlShimmersVisibility(isLoading = state.areShimmersVisible)
    }

    private fun news(news: MainNews) {
        when (news) {
            is MainNews.ShowError -> showAlertSnackbar(message = news.error.message)
        }
    }

    private fun controlShimmersVisibility(isLoading: Boolean) = with(binding) {
        if (isLoading) {
            shimmers.startShimmer()
        } else {
            shimmers.apply {
                stopShimmer()
                visibility = View.GONE
            }
            recyclerView.visibility = View.VISIBLE
        }
    }
}
