package com.example.perpustakaan_kel4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountLibrarian.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountLibrarian : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var librarianViewModel: LibrarianViewModel
    private lateinit var librarianCommunicator: LibrarianCommunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        librarianViewModel = ViewModelProvider(requireActivity())[LibrarianViewModel::class.java]
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_account_librarian, container, false)

        var profile_phone: TextView = view.findViewById<View>(R.id.profile_phone_lib) as TextView
        var profile_email: TextView = view.findViewById(R.id.profile_email_lib) as TextView
        var profile_name: TextView = view.findViewById(R.id.profile_name_lib) as TextView

        librarianViewModel.currentLibrarian.observe(requireActivity(), Observer {
            profile_name.text = it.first_name_librarian + " " + it.last_name_librarian
            profile_email.text = it.email
            profile_phone.text = it.no_telp
        })

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNotif = view.findViewById<ConstraintLayout>(R.id.notification_col)
        val btnContactSupport = view.findViewById<ConstraintLayout>(R.id.contact_us_col)
        val btnDeleteAccount = view.findViewById<ConstraintLayout>(R.id.delete_account_col)
        val btnTermsPrivacy = view.findViewById<ConstraintLayout>(R.id.terms_privacy_col)
        val btnLogout = view.findViewById<ConstraintLayout>(R.id.logout_col)
        val editAcc = view.findViewById<TextView>(R.id.btnChange)


        editAcc.setOnClickListener {
            librarianCommunicator = activity as LibrarianCommunicator
            librarianCommunicator.editLibrarianFragment()
        }

        btnLogout.setOnClickListener {
            val intent = Intent(requireActivity(), LoginScreen::class.java)
            startActivity(intent)
            activity?.finish()
        }

        btnDeleteAccount.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete Account")
                .setMessage("Are you sure you want to delete your account?")
                .setPositiveButton("Delete") { dialog, which ->

                    // Handle the positive button click (Delete)
                    // Implement the logic to delete the account
                    val url: String = ApiEndPoint.DELETE_LIBRARIAN
                    val stringRequest = object : StringRequest(
                        Method.POST, url,
                        Response.Listener { response ->
                            Log.d("response", response)

                            if (response.equals("success")) {
                                Toast.makeText(
                                    requireContext(),
                                    "Account Deleted",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Handler(Looper.getMainLooper()).postDelayed({
                                    val intent = Intent(requireActivity(), LoginScreen::class.java)
                                    startActivity(intent)
                                    activity?.finish()
                                }, 1000)


                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Failed to delete account. Please try again later",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        },
                        Response.ErrorListener { response ->
                            Log.d("response", response.toString())
                        }
                    ) {
                        override fun getParams(): HashMap<String, String> {
                            val params = HashMap<String, String>()
                            params["id_librarian"] = librarianViewModel.currentLibrarian.value!!.id_librarian
                            return params
                        }
                    }
                    Volley.newRequestQueue(requireContext()).add(stringRequest)
                }
                .setNegativeButton("Cancel") { dialog, which ->
                    // Handle the negative button click (Cancel)
                }
                .show()

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountLibrarian.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountLibrarian().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}