package com.example.perpustakaan_kel4

import android.annotation.SuppressLint
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
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Bookings.newInstance] factory method to
 * create an instance of this fragment.
 */
class Bookings : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var bookingViewModel: BookingViewModel
    private lateinit var bookingList: List<Pinjam>
    private lateinit var bookingMemberCommunicator: BookingMemberComunicator

    private var recyclerView: RecyclerView? = null
    private var recyclerViewBookingAdapter: RecyclerViewBookingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        bookingViewModel = ViewModelProvider(requireActivity())[BookingViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookingMemberCommunicator = activity as BookingMemberComunicator

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_bookings, container, false)

        try {
            recyclerView = view.findViewById<View>(R.id.Booking_RecyclerView) as RecyclerView?
        } catch (e: Throwable) {
            Log.e("response listview", e.message.toString())
        }

        bookingViewModel.currentBooking.observe(requireActivity(), Observer {
            bookingList = it.orEmpty()

            if (recyclerViewBookingAdapter == null) {
                recyclerViewBookingAdapter = RecyclerViewBookingAdapter(
                    bookings = bookingList,
                    bookingMemberCommunicator = bookingMemberCommunicator
                )
                val layoutManager: RecyclerView.LayoutManager =
                    LinearLayoutManager(requireActivity())
                recyclerView!!.layoutManager = layoutManager
                recyclerView!!.adapter = recyclerViewBookingAdapter
            } else{
                recyclerViewBookingAdapter?.updateData(bookingList)
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
         * @return A new instance of fragment Bookings.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Bookings().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}