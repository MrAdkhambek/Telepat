package com.adkhambek.telepat.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.adkhambek.auth.api.User
import com.adkhambek.auth.api.UsersProvider
import com.adkhambek.telepat.R
import com.adkhambek.telepat.databinding.FragmentMessagingBinding
import javax.inject.Inject

class MessagingFragment @Inject constructor(
    private val user: User,
    private val localeUserProvider: UsersProvider,
) : Fragment(R.layout.fragment_messaging) {

    private val binding: FragmentMessagingBinding by viewBinding(
        vbFactory = FragmentMessagingBinding::bind
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = user.name
    }
}