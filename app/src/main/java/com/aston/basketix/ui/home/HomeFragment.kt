package com.aston.basketix.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aston.basketix.R
import com.aston.basketix.api.daily_games.URL_DAILYGAMES
import com.aston.basketix.db.models.Team
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import android.provider.ContactsContract.CommonDataKinds.Organization



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val currentdatetime = Calendar.getInstance().time
        current_date.text = currentdatetime.toString()+" "+URL_DAILYGAMES



    }

}
