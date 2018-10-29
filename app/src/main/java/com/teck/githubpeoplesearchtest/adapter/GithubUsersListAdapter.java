package com.teck.githubpeoplesearchtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.teck.githubpeoplesearchtest.R;
import com.teck.githubpeoplesearchtest.model.GithubUser;
import com.teck.githubpeoplesearchtest.util.Utils;
import com.teck.githubpeoplesearchtest.view_holder.ProductViewHolder;
import java.util.ArrayList;


public class GithubUsersListAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private final ArrayList<GithubUser> mValues;
    private final UserSelectListener mListener;
    private Context context;

    public GithubUsersListAdapter(Context context , ArrayList<GithubUser> items, UserSelectListener listener) {
        mValues = items;
        mListener = listener;
        this.context=context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_github_user, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        GithubUser dataObject = mValues.get(position);

        holder.tvName.setText(dataObject.getLogin());

        Utils.loadImage(context,dataObject.getAvatarUrl(),holder.ivAvatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onUserSelected(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

}
