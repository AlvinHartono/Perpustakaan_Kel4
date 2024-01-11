package com.example.perpustakaan_kel4

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditTransactionFromLibrarian.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditTransactionFromLibrarian : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var transactionViewModel: TransactionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        transactionViewModel = ViewModelProvider(requireActivity())[transactionViewModel::class.java]

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_transaction_from_librarian, container, false)

        var editJudul = view.findViewById<View>(R.id.judulBuku) as TextView
        var editNama = view.findViewById<View>(R.id.namaMember) as TextView
        var edittglpinjam = view.findViewById<View>(R.id.tglPeminjaman) as TextView
        var editbtstglkembali = view.findViewById<View>(R.id.batastglPengembalian) as TextView
        var edittglkembali = view.findViewById<View>(R.id.tglPengembalian)

        var cancel = view.findViewById<View>(R.id.editTransactionCancel) as Button
        var save = view.findViewById<View>(R.id.editTransactionSave) as Button

        var switchStatus = view.findViewById<View>(R.id.switchStatus) as SwitchCompat
        val inputLayoutTglPengembalian : TextInputLayout = view.findViewById(R.id.textInputLayout8)

        transactionViewModel.currentTransaction.observe(requireActivity(), Observer {

        })

        switchStatus.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                inputLayoutTglPengembalian.isVisible = true
            }
        }

        cancel.setOnClickListener {
            closeCurrentFragment()
        }

        save.setOnClickListener {
            //save
        }

        return view
    }

    private fun closeCurrentFragment() {
        // Get the fragment manager
        val fragmentManager = requireActivity().supportFragmentManager

        // Begin a fragment transaction
        val transaction = fragmentManager.beginTransaction()

        // replacing the current fragment
        transaction.replace(R.id.frame_layout, TransactionLibrarian())
        // Commit the transaction
        transaction.commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditTransactionFromLibrarian.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditTransactionFromLibrarian().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}