package com.example.espresso;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.espresso.model.Country;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewActivity extends AppCompatActivity {
    @BindView(R.id.list_country)
    ListView mCountryList;
    @BindView(R.id.text_selected)
    TextView mSelectedTextView;
    Country[] countries = new Country[]{
            new Country("Afghanistan", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Afghanistan.png"),
            new Country("Albania", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Albania.png"),
            new Country("Macedonia", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Macedonia.png"),
            new Country("Madagascar", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Madagascar.png"),
            new Country("Andorra", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Andorra.png"),
            new Country("Angola", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Angola.png"),
            new Country("Argentina", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Argentina.png"),
            new Country("Mali", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Mali.png"),
            new Country("Mexico", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Mexico.png"),
            new Country("Austria", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Austria.png"),
            new Country("Armenia", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Armenia.png"),
            new Country("Azerbaijan", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Azerbaijan.png"),
            new Country("Bahamas", "https://www.countries-ofthe-world.com/flags-normal/flag-of-Bahamas.png"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);
        ArrayAdapter adapter = new MyAdapter(this, R.layout.item_country, countries);
        mCountryList.setAdapter(adapter);
        mCountryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectedTextView.setText(((Country) parent.getItemAtPosition(position)).name);
            }
        });
    }

    class MyAdapter extends ArrayAdapter<Country> {
        public MyAdapter(Context context, int resource, Country[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Country country = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_country, parent, false);
            }
            TextView tvName = (TextView) convertView.findViewById(R.id.text_name);
            ImageView ivFlag = (ImageView) convertView.findViewById(R.id.image_flag);
            ivFlag.setContentDescription("Flag of " + country.name);
            Glide.with(getContext()).load(country.flag)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivFlag);
            tvName.setText(country.name);
            ivFlag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), country.name, Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }
    }
}
