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
import ru.tinkoff.lunch.screens.main.presentation.MainUiEvent
import ru.tinkoff.lunch.screens.main.ui.dialogs.CreateLunchBottomSheet
import ru.tinkoff.lunch.screens.main.ui.dialogs.CreateLunchBottomSheet.CreateLunchBottomSheetListener
import ru.tinkoff.lunch.screens.main.ui.mapper.MainUiState
import ru.tinkoff.lunch.screens.main.ui.recycler.MainFragmentHolderFactory
import ru.tinkoff.lunch.utils.views.FlowFragment
import ru.tinkoff.lunch.utils.views.showAlertSnackbar
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

@AndroidEntryPoint
class MainFragment : FlowFragment<MainComponent>(R.layout.fragment_main),
    CreateLunchBottomSheetListener {

    private val store by storeViaViewModel { component.getMainStore() }
    private val binding by viewBinding(FragmentMainBinding::bind)

    private lateinit var recycler: TiRecyclerCoroutines<ViewTyped>
    private lateinit var bottomSheet: CreateLunchBottomSheet

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
        binding.fab.setOnClickListener { store.dispatch(MainUiEvent.CreateLunchClicked) }
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
            is MainNews.OpenCreateLunchScreen -> showCreateLunchBottomSheet()
        }
    }

    private fun showCreateLunchBottomSheet() {
        bottomSheet = CreateLunchBottomSheet(listener = this)
        bottomSheet.show(
            childFragmentManager,
            bottomSheet.tag
        )
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

    override fun onLunchCreated() {
        // todo: store.dispatch(MainUiEvent.LunchCreationConfirmed(lunchData))

    }
}
