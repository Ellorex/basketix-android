package com.aston.basketix.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.aston.basketix.R
import com.aston.basketix.api.scoreboard.Games
import com.aston.basketix.api.scoreboard.Scoreboard
import com.aston.basketix.api.scoreboard.current_dailygames
import com.aston.basketix.api.teams.Teams
import com.aston.basketix.db.models.Team
import com.aston.basketix.db.models.Team_Table
import com.aston.basketix.utils.Constant
import com.aston.basketix.utils.Network
import com.google.gson.Gson
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_scoreboard.*
import java.util.*

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
        current_date.text = currentdatetime.toString()

    }

    private fun getTeamsData() {
        if(Network.isNetworkAvailable(context)) {
            Log.e("isNetworkAvailable", "ok")

            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(context)
            val url = Constant.URL_TEAMS

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(Request.Method.GET, url,
                    Response.Listener<String> { json ->
                        Log.e("json", json)

                        var gson = Gson()
                        val teams = gson.fromJson(json, Teams::class.java)

                        for (team in teams.league?.standard!!) {
                            //Log.e("teams", "team1 : " + (team.fullName))

                            var queryTeam = SQLite.select().from(Team::class.java).where(Team_Table.teamId.eq(team.teamId)).querySingle()

                            if(queryTeam == null) {
                                Log.e("teams", "insert")

                                var newTeam = Team(team)
                                newTeam.save()
                            } else {
                                Log.e("teams", "update")

                                queryTeam?.isNBAFranchise = team.isNBAFranchise
                                queryTeam?.city = team.city
                                queryTeam?.isAllStar = team.isAllStar
                                queryTeam?.altCityName = team.altCityName
                                queryTeam?.fullName = team.fullName
                                queryTeam?.tricode = team.tricode
                                queryTeam?.nickname = team.nickname
                                queryTeam?.urlName = team.urlName
                                queryTeam?.confName = team.confName
                                queryTeam?.divName = team.divName

                                queryTeam?.update()
                            }
                        }

                        getScoreBoardData()
                    },
                    Response.ErrorListener {
                        Toast.makeText(context, "Erreur serveur", Toast.LENGTH_SHORT).show()
                    })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        } else {
            Log.e("isNetworkAvailable", "erreur pas de réseau")
        }
    }

    private fun getScoreBoardData() {
        if(Network.isNetworkAvailable(context)) {
            Log.e("isNetworkAvailable", "ok")

            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(context)
            val url = Constant.URL_SCOREBOARD

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(Request.Method.GET, url,
                    Response.Listener<String> { json ->
                        Log.e("json", json)

                        var gson = Gson()
                        val scoreboard = gson.fromJson(json, Scoreboard::class.java)

                        for (scoreboard in scoreboard.games!!) {
                            Log.e("scoreboard", "scoreboard : " + (scoreboard.startTimeEastern))

                            var queryVTeam = SQLite.select().from(Team::class.java).where(Team_Table.teamId.eq(scoreboard.vTeam?.teamId)).querySingle()
                            var queryHTeam = SQLite.select().from(Team::class.java).where(Team_Table.teamId.eq(scoreboard.hTeam?.teamId)).querySingle()

                            scoreboard.vTeam?.team = queryVTeam?.fullName
                            scoreboard.hTeam?.team = queryHTeam?.fullName
                        }

                        // Adapter
                        listViewScoreBoard.adapter = ArrayAdapter<Games>(context, android.R.layout.simple_list_item_1, scoreboard.games)
                    },
                    Response.ErrorListener {
                        Toast.makeText(context, "Erreur serveur", Toast.LENGTH_SHORT).show()
                    })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        } else {
            Log.e("isNetworkAvailable", "erreur pas de réseau")
        }
    }
}
