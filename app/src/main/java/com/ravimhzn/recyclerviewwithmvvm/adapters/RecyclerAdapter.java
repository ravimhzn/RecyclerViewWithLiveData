package com.ravimhzn.recyclerviewwithmvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ravimhzn.recyclerviewwithmvvm.R;
import com.ravimhzn.recyclerviewwithmvvm.models.Places;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myCustomRecyclerViewHolder> {

    private List<Places> mPlaces = new ArrayList<>();
    private Context context;

    public RecyclerAdapter(List<Places> mPlaces, Context context) {
        this.mPlaces = mPlaces;
        this.context = context;
    }

    @NonNull
    @Override
    public myCustomRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list,
                parent, false);
        myCustomRecyclerViewHolder mViewHolder = new myCustomRecyclerViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myCustomRecyclerViewHolder holder, int position) {
        // Set the name of the 'NicePlace'
        ((myCustomRecyclerViewHolder) holder).mName.setText(mPlaces.get(position).getTitle());

        // Image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(defaultOptions)
                .load(mPlaces.get(position).getImageUrl())
                .into(((myCustomRecyclerViewHolder) holder).mImage);
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public class myCustomRecyclerViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView mImage;
        private TextView mName;

        public myCustomRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
        }
    }
}
