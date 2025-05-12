package ru.tinkoff.lunch.screens.main.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.tinkoff.lunch.databinding.FragmentBottomSheetCreateLunchBinding

class CreateLunchBottomSheet(
    listener: CreateLunchBottomSheetListener,
) : BottomSheetDialogFragment() {

    private val binding by lazy { FragmentBottomSheetCreateLunchBinding.inflate(layoutInflater) }
    private var listener: CreateLunchBottomSheetListener? = listener

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
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as CreateLunchBottomSheetListener
        } catch (_: ClassCastException) {
        }
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
