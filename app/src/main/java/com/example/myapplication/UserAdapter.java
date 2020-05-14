package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

//messages
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private List<Matches> mMatches;
    private boolean isChat;
    FirebaseAuth fAuth;


    public UserAdapter(Context mContext,List<Matches> mMatches,boolean isChat){
        this.mMatches=mMatches;
        this.mContext=mContext;
        this.isChat=isChat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Matches match = mMatches.get(position);
        holder.username.setText(match.getFullname());


        if(match.getImage().equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(mContext).load(match.getImage()).into(holder.profile_image);
        }

        Glide.with(mContext).load(match.getImage()).into(holder.profile_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid",match.getFullname());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return mMatches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView profile_image;
        //  private ImageView img_on;
        //private ImageView img_off;

        public ViewHolder(View itemView){
            super(itemView);

            username=itemView.findViewById(R.id.username);
            profile_image=itemView.findViewById(R.id.profileImage);
            // img_on=itemView.findViewById(R.id.img_on);
            //img_off=itemView.findViewById(R.id.img_off);
        }
    }
}
