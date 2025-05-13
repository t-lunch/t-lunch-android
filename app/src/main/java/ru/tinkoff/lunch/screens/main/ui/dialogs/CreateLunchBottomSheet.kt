package ru.tinkoff.lunch.screens.main.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.tinkoff.lunch.databinding.FragmentBottomSheetCreateLunchBinding
import ru.tinkoff.lunch.screens.main.ui.dialogs.recycler.CreateLunchBottomSheetViewHolder
import ru.tinkoff.lunch.screens.main.ui.dialogs.recycler.model.ItemLunchTime
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

class CreateLunchBottomSheet(
    listener: CreateLunchBottomSheetListener,
) : BottomSheetDialogFragment() {

    private val binding by lazy { FragmentBottomSheetCreateLunchBinding.inflate(layoutInflater) }
    private var listener: CreateLunchBottomSheetListener? = listener
    private lateinit var recycler: TiRecyclerCoroutines<ViewTyped>

    init {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindActions()
        initRecycler()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as CreateLunchBottomSheetListener
        } catch (_: ClassCastException) {
        }
    }

    private fun initRecycler() {
        recycler = TiRecyclerCoroutines(
            binding.timeRecycler,
            CreateLunchBottomSheetViewHolder(),
        )
        recycler.setItems(
            listOf(
                ItemLunchTime(time = "12:00"),
                ItemLunchTime(time = "13:00"),
                ItemLunchTime(time = "14:00"),
                ItemLunchTime(time = "15:00"),
                ItemLunchTime(time = "16:00"),
                ItemLunchTime(time = "17:00"),
                ItemLunchTime(time = "18:00"),
                ItemLunchTime(time = "19:00"),
                ItemLunchTime(time = "20:00"),
                ItemLunchTime(time = "21:00"),
                ItemLunchTime(time = "22:00"),
                ItemLunchTime(time = "23:00"),
                ItemLunchTime(time = "24:00"),
            )
        )
    }

    private fun bindActions() {
        // todo: add onClickListener
    }

    interface CreateLunchBottomSheetListener {
        fun onLunchCreated(
            // todo: pass an object
        )
    }
}
