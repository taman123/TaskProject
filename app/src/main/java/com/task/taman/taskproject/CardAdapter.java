package com.task.taman.taskproject;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Xerric on 2/3/2016.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    public List<FeedDetailClass> feedDetailClasses;
    public CardAdapter(List<FeedDetailClass> feedDetailClasses){
        this.feedDetailClasses=feedDetailClasses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int pos = position;
        try {
            holder.creatorName.setText(feedDetailClasses.get(pos).getName());
            holder.feedMessage.setText(feedDetailClasses.get(pos).getMessage());
            holder.numberOfLikes.setText(feedDetailClasses.get(pos).getLikes());
            holder.getNumberOfComments.setText(feedDetailClasses.get(pos).getComments());
            holder.imageURL.setText(feedDetailClasses.get(pos).getPhoto());
            Log.i("Details", feedDetailClasses.get(pos).getLikes() + " " +feedDetailClasses.get(position).getPhoto() );
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return feedDetailClasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView creatorName;
        public TextView feedMessage;
        public TextView numberOfLikes;
        public TextView getNumberOfComments;
        public TextView imageURL;

        public ViewHolder(View itemView) {
            super(itemView);
            creatorName= (TextView) itemView.findViewById(R.id.txt_name);
            imageURL= (TextView) itemView.findViewById(R.id.txt_image);
            feedMessage= (TextView) itemView.findViewById(R.id.txt_message);
            numberOfLikes= (TextView) itemView.findViewById(R.id.txt_likes);
            getNumberOfComments= (TextView) itemView.findViewById(R.id.txt_comments);

        }
    }
}
