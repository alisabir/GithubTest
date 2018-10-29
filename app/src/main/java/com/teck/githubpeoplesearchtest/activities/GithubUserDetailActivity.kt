package com.teck.githubpeoplesearchtest.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.gson.Gson
import com.teck.githubpeoplesearchtest.R
import com.teck.githubpeoplesearchtest.adapter.GithubUsersListAdapter
import com.teck.githubpeoplesearchtest.contants.AppConstants
import com.teck.githubpeoplesearchtest.model.GithubUser
import com.teck.githubpeoplesearchtest.util.MyLogger
import com.teck.githubpeoplesearchtest.util.Utils
import com.teck.githubpeoplesearchtest.web.ApiControler
import kotlinx.android.synthetic.main.activity_github_user_detail.*
import kotlinx.android.synthetic.main.activity_searching.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubUserDetailActivity : AppCompatActivity() {

    private var githubUser: GithubUser? = null

    private lateinit var githubUsers: ArrayList<GithubUser>;

    private lateinit var githubUsersListAdapter: GithubUsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_user_detail)



        val receivedModelStr:String?;
        receivedModelStr=intent.getStringExtra(AppConstants.PUT_EXTRA_GITHUB_USER)

        val gson = Gson()

         githubUser = gson.fromJson(receivedModelStr, GithubUser::class.java)


        Utils.loadImage(this@GithubUserDetailActivity,githubUser?.avatarUrl,ivAvatar)

        githubUsers= ArrayList();
        githubUsersListAdapter= GithubUsersListAdapter(this@GithubUserDetailActivity, githubUsers, null);

        rvFollowers.layoutManager= LinearLayoutManager(this@GithubUserDetailActivity);
        rvFollowers.adapter = githubUsersListAdapter

        setTitle(githubUser?.getLogin()+" "+getString(R.string.detail))


        if(githubUser?.getUrl()!=null)
        loadUserProfile(githubUser?.getUrl())


    }


    private fun  loadUserProfile(profileUrl:String?){

        progProfile.visibility=View.VISIBLE
        ApiControler.loadUserProfile(object : Callback<GithubUser>{

            override fun onFailure(call: Call<GithubUser>?, t: Throwable?) {

                progProfile.visibility=View.GONE

                loadFollowers(githubUser?.followersUrl)

            }

            override fun onResponse(call: Call<GithubUser>?, response: Response<GithubUser>?) {

                progProfile.visibility=View.GONE

                loadFollowers(githubUser?.followersUrl)

                if(response?.isSuccessful!! &&response?.body()!=null){

                    populateData(response.body());
                }

            }
        },profileUrl)

    }

    private fun populateData(userData: GithubUser?) {

        tvEmail.setText(getString(R.string.email)+": "+userData?.getEmail())
        tvName.setText(getString(R.string.name)+": "+userData?.getName())

    }


    private fun loadFollowers(url:String?){

        progFollowers.visibility= View.VISIBLE
        ApiControler.loadFollowers(object : Callback<ArrayList<GithubUser>>{


            override fun onFailure(call: Call<ArrayList<GithubUser> >?, t: Throwable?) {

                progFollowers.visibility=View.GONE
                MyLogger.d(t?.message)
            }

            override fun onResponse(call: Call<ArrayList<GithubUser>>?, response: Response<ArrayList<GithubUser>>?) {

                progFollowers.visibility=View.GONE


                response?.body()?.let { githubUsers.addAll(it) }
                githubUsersListAdapter.notifyDataSetChanged()

            }
        },url
        );
    }
}
