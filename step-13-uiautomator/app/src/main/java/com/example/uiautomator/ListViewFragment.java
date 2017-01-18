package com.example.uiautomator;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uiautomator.model.Country;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment {


    @BindView(R.id.list_country)
    ListView mCountryListView;
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

    public ListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        ButterKnife.bind(this, view);
        mCountryListView.setAdapter(new MyAdapter(getActivity(), R.layout.item_country, countries));
        return view;
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
            CheckBox cbVisited = (CheckBox) convertView.findViewById(R.id.check_visited);
            if (position % 2 == 0) {
                cbVisited.setChecked(true);
            }
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
