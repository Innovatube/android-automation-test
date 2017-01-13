package com.example.espresso;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.espresso.model.Character;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by quanlt on 10/01/2017.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<Character> characters;
    private CharacterClickListener mListener;
    public CharacterAdapter(Character[] character, CharacterClickListener mListener) {
        characters = Arrays.asList(character);
        this.mListener = mListener;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_character, parent, false));
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        final Character character = characters.get(position);
        holder.mNameTextView.setText(character.getName());
        holder.mRoleTextView.setText(character.getRole());
        Glide.with(holder.itemView.getContext())
                .load(character.getPortrait())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mPortraitImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCharacterClick(character);
            }
        });
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }


    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_portrait)
        ImageView mPortraitImageView;
        @BindView(R.id.text_name)
        TextView mNameTextView;
        @BindView(R.id.text_role)
        TextView mRoleTextView;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface CharacterClickListener{
        void onCharacterClick(Character character);
    }
}