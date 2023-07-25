package com.example.dao_pattern.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.dao_pattern.R
import com.example.dao_pattern.ui.Action
import com.example.dao_pattern.ui.viewmodel.UserViewModel

class ConfirmationDialogFragment(
    private val action: Action,
    private val index: Int
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setMessage(getString(R.string.text_confirmation))
        dialog.setPositiveButton(getString(R.string.text_confirm)) { _, _ ->
            val viewModel = UserViewModel.getInstance()
            if (action == Action.DELETE) {
                viewModel.removeUser(index)
            } else {
                viewModel.updateUser(index, "Name Updated", "Email Updated")
            }
        }
        dialog.setNegativeButton(getString(R.string.text_cancel)) { _, _ ->
            dismiss()
        }
        return dialog.create()
    }
}