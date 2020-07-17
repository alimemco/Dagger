package com.alirnp.dagger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alirnp.dagger.model.Repository;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.Holder>{

    List<Repository> repositoryList ;

    public RepositoryAdapter(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.bind(repositoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        private final ImageView mImageView ;
        private final TextView mTextView ;

        public Holder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.iem_repository_image);
            mTextView = itemView.findViewById(R.id.iem_repository_text);
        }
        void bind(Repository repository){

            Picasso.get().load(repository.getOwner().getAvatarUrl()).into(mImageView);
            mTextView.setText(repository.getName());
        }
    }
}
