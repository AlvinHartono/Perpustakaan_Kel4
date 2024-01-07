package com.example.perpustakaan_kel4

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [editLibrarianAccount.newInstance] factory method to
 * create an instance of this fragment.
 */
class editLibrarianAccount : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var  librarianViewModel: LibrarianViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        librarianViewModel = ViewModelProvider(requireActivity())[LibrarianViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_edit_librarian_account, container, false)

        var editTextEditFirstname = view.findViewById<View>(R.id.editTextEditFirstnameLib) as TextView
        var editTextEditLastname = view.findViewById<View>(R.id.editTextEditLastNameLib) as TextView
        var editTextEditEmail = view.findViewById<View>(R.id.editTextEditEmailLib) as TextView
        var editTextPhoneNumber = view.findViewById<View>(R.id.editTextPhoneNumberLib) as TextView
        var editLibrarianCancel = view.findViewById<View>(R.id.editLibrarianCancel) as Button
        var editLibrarianSave = view.findViewById<View>(R.id.editLibrarianSave) as Button


        librarianViewModel.currentLibrarian.observe(requireActivity(), Observer {
            editTextEditFirstname.text = it.first_name_librarian
            editTextEditLastname.text = it.last_name_librarian
            editTextEditEmail.text = it.email
            editTextPhoneNumber.text = it.no_telp
        })



        editLibrarianCancel.setOnClickListener {
            closeCurrentFragment()
        }

        editLibrarianSave.setOnClickListener {
            Log.d("response editacc", "yo 5")

            var updatedLibrarian = Librarian(
                librarianViewModel.currentLibrarian.value!!.id_librarian,
                editTextEditFirstname.text.toString(),
                editTextEditLastname.text.toString(),
                editTextEditEmail.text.toString(),
                editTextPhoneNumber.text.toString(),
                librarianViewModel.currentLibrarian.value!!.password
            )


            librarianViewModel.updateLibrarianData(updatedLibrarian)

            try {
                sendModifiedLibrarian(
                    editTextEditFirstname.text.toString(),
                    editTextEditLastname.text.toString(),
                    editTextEditEmail.text.toString(),
                    editTextPhoneNumber.text.toString()
                )

                Toast.makeText(requireContext(), "Account Update Successful", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    closeCurrentFragment()
                }, 500)


            } catch (e: Throwable) {
                Log.e("response edit member", e.toString())
            }
        }

        return view
    }

    private fun sendModifiedLibrarian(
        firstname: String,
        lastName: String,
        email: String,
        noTelp: String
    ) {
        val url: String = ApiEndPoint.UPDATE_LIBRARIAN
        val stringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                Log.d("response update librarian", response)

                if (response.equals("success")) {

                } else {
                    Toast.makeText(requireContext(), "update librarian failed", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            Response.ErrorListener { response ->
                Toast.makeText(requireContext(), "$response", Toast.LENGTH_SHORT).show()
                Log.d("response update librarian ", response.toString())
            }
        ) {
            override fun getParams(): HashMap<String, String> {
                val params = HashMap<String, String>()
                params["id_librarian"] = librarianViewModel.currentLibrarian.value!!.id_librarian
                params["first_name_librarian"] = firstname
                params["last_name_librarian"] = lastName
                params["email"] = email
                params["no_telp"] = noTelp
                return params
            }
        }
        Volley.newRequestQueue(requireContext()).add(stringRequest)


    }


    private fun closeCurrentFragment() {
        // Get the fragment manager
        val fragmentManager = requireActivity().supportFragmentManager

        // Begin a fragment transaction
        val transaction = fragmentManager.beginTransaction()

        // replacing the current fragment
        transaction.replace(R.id.frame_layout_librarian, Account())
        // Commit the transaction
        transaction.commit()

        // Optionally, you can add the following line to allow the user to navigate back
        // fragmentManager.popBackStack()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment editLibrarianAccount.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            editLibrarianAccount().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}