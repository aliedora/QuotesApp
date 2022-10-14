package com.e.quotesapp
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController

class InputFragment : Fragment(), View.OnClickListener {
    var inputText:String = ""
    lateinit var etInput: EditText
    lateinit var btnGo: Button
    val bundle = Bundle()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGo = view.findViewById(R.id.btn_go)
        etInput = view.findViewById(R.id.et_input)
        btnGo.setOnClickListener(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container,false)
    }


    override fun onClick(v: View?) {
        inputText = etInput.text.toString()
        val fragment = QuotesListFragment()
        bundle.putString("MySearh", inputText)
        val fragmentTransaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        fragment.arguments = bundle
        fragmentTransaction.addToBackStack(null)
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }
}