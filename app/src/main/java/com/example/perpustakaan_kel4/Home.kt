package com.example.perpustakaan_kel4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var memberFirstName: TextView
    private lateinit var memberViewModel: MemberViewModel

    private var recyclerView : RecyclerView? = null
    private var recyclerViewBookAdapter : RecyclerViewBookAdapter? = null
    private var bookList = mutableListOf<Buku>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        memberViewModel = ViewModelProvider(requireActivity())[MemberViewModel::class.java]
//        Log.d("responsezzz", memberViewModel.currentMember.value!!.last_name_member)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bookList = ArrayList()
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        memberFirstName = view.findViewById<View>(R.id.memberFirstName) as TextView
        recyclerView = view.findViewById<View>(R.id.imageRecyclerView) as RecyclerView?
        recyclerViewBookAdapter = RecyclerViewBookAdapter(requireActivity(), bookList)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(requireActivity(), 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recyclerViewBookAdapter

        prepareBookListData()

        memberViewModel.currentMember.observe(requireActivity(), Observer {
            memberFirstName.text = it.first_name_member
        })

        return view
    }

    private fun prepareBookListData() {
        //panggil data

        recyclerViewBookAdapter!!.notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}