package ru.tinkoff.lunch.screens.main.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.tinkoff.lunch.R
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
    private var isKitchenSelected = true

    init {
        this.listener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState)
        if (bottomSheetDialog is BottomSheetDialog) {
            bottomSheetDialog.behavior.skipCollapsed = true
            bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        return bottomSheetDialog
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

        binding.otherPlaceButton.setOnClickListener {
            if (isKitchenSelected) {
                binding.kitchenButton.apply {
                    isKitchenSelected = false
                    setBackgroundColor(requireContext().getColor(R.color.chip_gray))
                }
                binding.otherPlaceButton.apply {
                    setBackgroundColor(requireContext().getColor(R.color.yellow))
                }
                binding.customPlaceField.isVisible = true
            }
        }

        binding.kitchenButton.setOnClickListener {
            if (!isKitchenSelected) {
                binding.kitchenButton.apply {
                    isKitchenSelected = true
                    setBackgroundColor(requireContext().getColor(R.color.yellow))
                }
                binding.otherPlaceButton.apply {
                    setBackgroundColor(requireContext().getColor(R.color.chip_gray))
                }
                binding.customPlaceField.isVisible = false
            }
        }
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
