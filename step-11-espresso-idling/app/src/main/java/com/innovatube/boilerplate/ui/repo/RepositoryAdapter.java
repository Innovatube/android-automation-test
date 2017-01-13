package com.innovatube.boilerplate.ui.repo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.innovatube.boilerplate.R;
import com.innovatube.boilerplate.data.model.Repository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by quanlt on 11/01/2017.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryHolder> {
    private List<Repository> repositories;

    public RepositoryAdapter() {
        repositories = new ArrayList<>();
    }

    @Override
    public RepositoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_repository, parent, false);
        return new RepositoryHolder(view);
    }

    public void addRepos(List<Repository> repositories) {
        this.repositories.addAll(repositories);
        notifyItemRangeInserted(0, repositories.size());
    }

    @Override
    public void onBindViewHolder(RepositoryHolder holder, int position) {
        holder.mNameTextView.setText(repositories.get(position).getName());
        holder.mDescriptionTextView.setText(repositories.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public class RepositoryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_name)
        TextView mNameTextView;
        @BindView(R.id.text_description)
        TextView mDescriptionTextView;

        public RepositoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
