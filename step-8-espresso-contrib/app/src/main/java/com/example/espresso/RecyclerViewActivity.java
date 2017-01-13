package com.example.espresso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.espresso.CharacterAdapter.CharacterClickListener;
import com.example.espresso.model.Character;
import com.example.espresso.util.DataFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity implements CharacterClickListener{
    @BindView(R.id.recycler_character)
    RecyclerView mCharacterRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        mCharacterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCharacterRecyclerView.setAdapter(new CharacterAdapter(DataFactory.characters, this));

    }


    @Override
    public void onCharacterClick(Character character) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.ARG_CHARACTER, character);
        startActivity(intent);
    }
}
