package com.framgia.oleo.screen.signup

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.R
import com.framgia.oleo.data.source.model.User
import com.framgia.oleo.databinding.FragmentSignupBinding
import com.framgia.oleo.utils.di.Injectable
import com.framgia.oleo.utils.extension.isCheckMultiClick
import com.framgia.oleo.utils.extension.createDialog
import com.framgia.oleo.utils.extension.showSnackBar
import com.framgia.oleo.utils.liveData.autoCleared
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_signup.*
import javax.inject.Inject

class SignUpFragment : DialogFragment(), Injectable, View.OnClickListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SignUpViewModel
    private lateinit var fireBase: FirebaseAuth

    private var binding by autoCleared<FragmentSignupBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater: LayoutInflater = activity!!.layoutInflater
        viewModel = SignUpViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, null, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        val dialog = createDialog(context!!,binding.root)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        buttonClose.setOnClickListener(this)
        buttonSignUp.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.buttonClose -> if (isCheckMultiClick()) dismiss() // Todo dismiss diaLog Fragment
            R.id.buttonSignUp -> if (isCheckMultiClick()) onSignUp()
        }
    }

    private fun onSignUp() {
        fireBase = FirebaseAuth.getInstance()
        val email = textInputEmail.text.toString()
        val password = textInputPassword.text.toString()
        val progressDialog = ProgressDialog(context)

        fireBase.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity!!) { task ->
            if (task.isSuccessful) {
                progressDialog.show()
                val user = User()
                user.email = email
                user.userName = textInputUserName.text.toString()
                user.phoneNumber = textInputPhoneNumber.text.toString()

                FirebaseDatabase.getInstance().getReference(USER_TABLE_FIRE_BASE)
                    .child(FirebaseAuth.getInstance().currentUser!!.uid)
                    .setValue(user).addOnCompleteListener(activity!!) { result ->
                        progressDialog.dismiss()
                        if (result.isSuccessful) view!!.showSnackBar(getString(R.string.sign_up_success))
                        else view!!.showSnackBar(getString(R.string.sign_up_fail))
                    }
            } else view!!.showSnackBar(task.exception!!.message.toString())
        }
    }

    companion object {

        const val USER_TABLE_FIRE_BASE = "User"

        fun newInstance() = SignUpFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
