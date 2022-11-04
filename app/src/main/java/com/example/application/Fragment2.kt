package com.example.application

import android.R.attr.description
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment


class Fragment2 : Fragment() {

    open fun Fragment2(){

    }

    lateinit var view1: View
    lateinit var search: EditText
    lateinit var button: Button
    lateinit var latest: TextView
    lateinit var emptyString: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        view1 = inflater.inflate(R.layout.fragment_2, container, false)
        button = view1.findViewById(R.id.btnGet)
        search = view1.findViewById(R.id.search)
        latest = view1.findViewById(R.id.latestSearch)


        button.setOnClickListener(View.OnClickListener {
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

            val editor: SharedPreferences.Editor = sharedPref.edit()

            editor.putString("latest", search.getText().toString())


            editor.apply()
            val bundle = Bundle()
            bundle.putString("movieName", search.getText().toString())
            val fragment3 = Fragment3()
            fragment3.arguments = bundle
            replaceFragment(fragment3)
        })
        loadData()
        updateViews()
        return view1
    }

    fun loadData() {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        emptyString = sharedPref.getString("latest", "").toString()

    }

    fun updateViews() {
        latest.setText(emptyString)

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.mainFrame, fragment).addToBackStack(null);
        fragmentTransaction.commit()
    }
}