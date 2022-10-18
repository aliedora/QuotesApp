package com.e.quotesapp

import RetrofitServices
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.quotesapp.adapter.MyQuotesAdapter
import com.e.quotesapp.common.Common
import com.e.quotesapp.model.Quote
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuotesListFragment : Fragment() {
    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyQuotesAdapter
    lateinit var recyclerQuotesList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quotes_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerQuotesList = view.findViewById(R.id.recyclerQuotesList)
        Log.d("Zlu", "we are here")
        layoutManager = LinearLayoutManager(view.context)
        recyclerQuotesList.layoutManager = layoutManager
        mService = Common.retrofitService
        recyclerQuotesList.setHasFixedSize(true)

        var name = arguments?.getString("MySearh") ?: "Naruto"
        if (name.isEmpty()) name = "Naruto"

        Log.d("Zlulu", name)
        getQuotesByTitle(name)

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val fragment = InputFragment()
                    val fragmentTransaction: FragmentTransaction =
                        parentFragmentManager.beginTransaction()
                    fragmentTransaction.addToBackStack(null)
                        .replace(R.id.fragment_container_view, fragment)
                        .commit()
                }
            })
    }

    private fun getRandomQuotesList() {
        mService.getRandomQuotes().enqueue(object : Callback<ArrayList<Quote>> {
            override fun onFailure(call: Call<ArrayList<Quote>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<Quote>>,
                response: Response<ArrayList<Quote>>
            ) {
                showQuotes(response.body() as ArrayList<Quote>, this)
            }
        })
    }


    private fun getQuotesByTitle(name: String) {

        mService.getQuotesByTitle(name).enqueue(object : Callback<ArrayList<Quote>> {
            override fun onFailure(call: Call<ArrayList<Quote>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<Quote>>,
                response: Response<ArrayList<Quote>>
            ) {
                if (response.code() != 200) {
                    Log.d("404", "nothing found")
                    getRandomQuotesList()
                    return
                }
                showQuotes(response.body() as ArrayList<Quote>, this)
            }
        })
    }


    private fun showQuotes(quotesList: ArrayList<Quote>, context: Callback<ArrayList<Quote>>) {
        adapter = MyQuotesAdapter(context, quotesList)
        adapter.notifyDataSetChanged()
        recyclerQuotesList.adapter = adapter
        quotesList.forEach {
            Log.d("Quote", it.quote)
        }
    }
}