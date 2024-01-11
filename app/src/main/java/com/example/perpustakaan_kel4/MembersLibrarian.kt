package com.example.perpustakaan_kel4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MembersLibrarian.newInstance] factory method to
 * create an instance of this fragment.
 */
class MembersLibrarian : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var memberViewModel: MemberViewModel
    private lateinit var memberList: List<Member>
    private lateinit var librarianCommunicator: LibrarianCommunicator

    private var recyclerView: RecyclerView? = null
    private var recyclerViewMemberAdapter: RecyclerViewMemberAdapter? = null


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

        librarianCommunicator = activity as LibrarianCommunicator
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_members_librarian, container, false)

        try {
            recyclerView = view.findViewById<View>(R.id.MemberRecyclerView) as RecyclerView
        } catch (e: Throwable) {
            Log.e("error", e.message.toString())
        }

        memberViewModel.currentMemberList.observe(requireActivity(), Observer {
            memberList = it.orEmpty()

            if (recyclerViewMemberAdapter == null) {
                recyclerViewMemberAdapter = RecyclerViewMemberAdapter(
                    memberList = memberList,
                    librarianCommunicator = librarianCommunicator
                )
                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(requireActivity())
                recyclerView!!.layoutManager = layoutManager
                recyclerView!!.adapter = recyclerViewMemberAdapter
            } else {
                // Update the adapter data without reinitializing it
                recyclerViewMemberAdapter?.updateData(memberList)
            }
        })


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MembersLibrarian.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MembersLibrarian().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}