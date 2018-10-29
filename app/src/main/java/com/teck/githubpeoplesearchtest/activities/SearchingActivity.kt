package com.teck.githubpeoplesearchtest.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import com.google.gson.Gson
import com.teck.githubpeoplesearchtest.R
import com.teck.githubpeoplesearchtest.adapter.GithubUsersListAdapter
import com.teck.githubpeoplesearchtest.adapter.UserSelectListener
import com.teck.githubpeoplesearchtest.contants.AppConstants
import com.teck.githubpeoplesearchtest.model.GitHumSearchResponse
import com.teck.githubpeoplesearchtest.model.GithubUser
import com.teck.githubpeoplesearchtest.util.MyLogger
import com.teck.githubpeoplesearchtest.web.ApiControler
import kotlinx.android.synthetic.main.activity_searching.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchingActivity : AppCompatActivity(), UserSelectListener, SearchView.OnQueryTextListener {


    private lateinit var githubUsers:ArrayList<GithubUser>
    private lateinit var githubUsersListAdapter: GithubUsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searching)

        githubUsers= ArrayList();
        githubUsersListAdapter= GithubUsersListAdapter(this@SearchingActivity, githubUsers, this@SearchingActivity);

        rvGithubUsers.layoutManager=LinearLayoutManager(this@SearchingActivity);
        rvGithubUsers.adapter = githubUsersListAdapter


        svGithubUser.setOnQueryTextListener(this@SearchingActivity)
        svGithubUser.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                svGithubUser.onActionViewExpanded();

            }
        });

        svGithubUser.setIconifiedByDefault(true)

    }



    private fun searchFriends(queryText:String?){

        showSearchProgress(true)
        ApiControler.searchGithubUser(object : Callback<GitHumSearchResponse> {
            override fun onResponse(call: Call<GitHumSearchResponse>, response: Response<GitHumSearchResponse>) {

                showSearchProgress(false)
                if(response.isSuccessful&&response.body()!=null){

                    if(response.body().totalCount>0){

                        tvMsg.visibility=View.GONE
                        githubUsers.clear();
                        githubUsers.addAll(response.body().items);
                        githubUsersListAdapter.notifyDataSetChanged()

                    }else {

                        tvMsg.visibility=View.VISIBLE

                    }
                    MyLogger.d("TOTAL COUNT "+response.body().totalCount)




                }
            }

            override fun onFailure(call: Call<GitHumSearchResponse>, t: Throwable) {

                tvMsg.visibility=View.VISIBLE
                tvMsg.setText(t.message)
                showSearchProgress(false)
            }
        }, queryText)
    }

    override fun onQueryTextChange(p0: String?): Boolean {

        searchFriends(p0);
        return true;
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {

        searchFriends(p0);
        return true;
    }


    private fun showSearchProgress(show:Boolean){

        if(show)progSearch.visibility=View.VISIBLE;
        else progSearch.visibility= View.GONE;
    }

    override fun onUserSelected(selectedUser: GithubUser?) {

        val intent = Intent(this, GithubUserDetailActivity::class.java)

        val gson=Gson();

        intent.putExtra(AppConstants.PUT_EXTRA_GITHUB_USER,gson.toJson(selectedUser))
        startActivity(intent)
    }

}
