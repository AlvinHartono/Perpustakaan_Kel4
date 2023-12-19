package com.example.perpustakaan_kel4

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Account.newInstance] factory method to
 * create an instance of this fragment.
 */
class Account : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNotif  = view.findViewById<ConstraintLayout>(R.id.notification_col)
        val btnContactSupport  = view.findViewById<ConstraintLayout>(R.id.contact_us_col)
        val btnManageAccount = view.findViewById<ConstraintLayout>(R.id.manage_account_col)
        val btnTermsPrivacy = view.findViewById<ConstraintLayout>(R.id.terms_privacy_col)
        val btnLogout = view.findViewById<ConstraintLayout>(R.id.logout_col)
        val editAcc = view.findViewById<TextView>(R.id.btnChange)

        btnLogout.setOnClickListener{
            val intent = Intent(requireActivity(), LoginScreen::class.java)
            startActivity(intent)
        }
    }
}