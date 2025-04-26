package ru.tinkoff.lunch.screens.main.ui

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.lunch.screens.main.di.MainComponent
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.common.recycler.items.ItemHeader
import ru.tinkoff.lunch.databinding.FragmentMainBinding
import ru.tinkoff.lunch.screens.main.ui.mapper.MainUiState
import ru.tinkoff.lunch.screens.main.ui.recycler.MainFragmentHolderFactory
import ru.tinkoff.lunch.utils.views.FlowFragment
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
            lifecycleOwner = this,
            stateCollector = null,
            newsCollector = null,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun render(state: MainUiState) {

    }

    private fun initRecycler() {
        recycler = TiRecyclerCoroutines(
            binding.recyclerView,
            MainFragmentHolderFactory()
        )
        recycler.setItems(listOf(ItemHeader(text = "Обеды")))
    }
}
