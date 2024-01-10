package com.example.perpustakaan_kel4

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
 * Use the [EditMemberFromLibrarian.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditMemberFromLibrarian(member: Member) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var member: Member = member
    private lateinit var memberViewModel: MemberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        memberViewModel = ViewModelProvider(requireActivity())[MemberViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_member_from_librarian, container, false)
        var editTextEditFirstname =
            view.findViewById<View>(R.id.editTextEditFirstnameLib) as TextView
        var editTextEditLastname = view.findViewById<View>(R.id.editTextEditLastNameLib) as TextView
        var editTextEditEmail = view.findViewById<View>(R.id.editTextEditEmailLib) as TextView
        var editTextPhoneNumber = view.findViewById<View>(R.id.editTextPhoneNumberLib) as TextView
        var editMemberCancel = view.findViewById<View>(R.id.editMemberCancelLib) as Button
        var editMemberSave = view.findViewById<View>(R.id.editMemberSaveLib) as Button

        editTextEditFirstname.text = member.first_name_member
        editTextEditLastname.text = member.last_name_member
        editTextEditEmail.text = member.email
        editTextPhoneNumber.text = member.no_telp
        editMemberCancel.setOnClickListener {
            closeCurrentFragment()
        }
        editMemberSave.setOnClickListener {
            sendModifiedMember(
                firstname = editTextEditFirstname.text.toString(),
                lastName = editTextEditLastname.text.toString(),
                email = editTextEditEmail.text.toString(),
                noTelp = editTextPhoneNumber.text.toString()
            )

            try {
                member = Member(
                    member.id_member,
                    editTextEditFirstname.text.toString(),
                    editTextEditLastname.text.toString(),
                    editTextEditEmail.text.toString(),
                    editTextPhoneNumber.text.toString(),
                    member.password
                )
                memberViewModel.updateOrDeleteMember(member.id_member.toInt(), member)

            } catch (e: Throwable) {
                Log.d("response", e.toString())
            }
            Toast.makeText(requireContext(), "Account Update Successful", Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({
                closeCurrentFragment()
            }, 500)
        }

        return view
    }

    private fun sendModifiedMember(
        firstname: String,
        lastName: String,
        email: String,
        noTelp: String
    ) {
        val url: String = ApiEndPoint.UPDATE_MEMBER
        val stringRequest = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                Log.d("response update member", response)

                if (response.equals("success")) {

                } else {
                    Toast.makeText(requireContext(), "update member failed", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            Response.ErrorListener { response ->
                Toast.makeText(requireContext(), "$response", Toast.LENGTH_SHORT).show()
                Log.d("response update member ", response.toString())
            }
        ) {
            override fun getParams(): HashMap<String, String> {
                val params = HashMap<String, String>()
                params["id_member"] = member.id_member
                params["first_name_member"] = firstname
                params["last_name_member"] = lastName
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
        transaction.replace(R.id.frame_layout_librarian, MembersLibrarian())
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
         * @return A new instance of fragment EditMemberFromLibrarian.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, member: Member) =
            EditMemberFromLibrarian(member).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}