package com.teck.githubpeoplesearchtest.view_holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.teck.githubpeoplesearchtest.R;
import com.teck.githubpeoplesearchtest.model.GithubUser;

public class ProductViewHolder  extends RecyclerView.ViewHolder  {


    public GithubUser mItem;
    public final TextView tvName;
    public final ImageView ivAvatar;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

       tvName= itemView.findViewById(R.id.tvUserName);
        ivAvatar=itemView.findViewById(R.id.ivAvatar);
    }
}
