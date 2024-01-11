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
 * Use the [TransactionLibrarian.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransactionLibrarian : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var transctionList: List<Pinjam>
    private lateinit var bookingCommunicator: BookingCommunicator

    private var recyclerView : RecyclerView? = null
    private var recyclerViewTransactionAdapter: RecyclerViewTransactionAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookingCommunicator = activity as BookingCommunicator
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_transaction_librarian, container, false)

        try {
            recyclerView = view.findViewById<View>(R.id.TransactionRecyclerView) as RecyclerView
        } catch (e: Throwable){
            Log.e("error", e.message.toString())
        }


        transactionViewModel.currentTransaction.observe(requireActivity(), Observer {


            if(recyclerViewTransactionAdapter == null){
                recyclerViewTransactionAdapter = RecyclerViewTransactionAdapter(
                    TransactionList = it.orEmpty(),
                    bookingCommunicator = bookingCommunicator
                )
                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(requireActivity())
                recyclerView!!.layoutManager = layoutManager
                recyclerView!!.adapter = recyclerViewTransactionAdapter
            }else{
                recyclerViewTransactionAdapter?.updateData(transctionList)
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
         * @return A new instance of fragment TransactionLibrarian.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TransactionLibrarian().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}