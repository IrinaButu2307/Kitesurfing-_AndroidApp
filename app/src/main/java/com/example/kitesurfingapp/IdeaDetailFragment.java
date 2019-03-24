package com.example.kitesurfingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kitesurfingapp.models.Idea;
import com.example.kitesurfingapp.services.IdeaService;
import com.example.kitesurfingapp.services.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IdeaDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    public boolean isfavorite = true;
    private Idea mItem;

    public IdeaDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.idea_detail, container, false);

        final Context context = getContext();


        final TextView mTextViewCountry = (TextView) rootView.findViewById(R.id.textView_get_country);
        final TextView mTextViewLatitude = (TextView) rootView.findViewById(R.id.textView_get_latitude);
        final TextView mTextViewLongitude = (TextView) rootView.findViewById(R.id.textView_get_longitude);
        final TextView mTextViewWindProbability = (TextView) rootView.findViewById(R.id.textView_get_wind_probability);
        final TextView mTextViewWhereToGo = (TextView) rootView.findViewById(R.id.textView_get_when_to_go);
        CheckBox mCheckbox = rootView.findViewById(R.id.toolbar_favorite);

        if (mCheckbox.isChecked())
            isfavorite = true;
        else isfavorite = false;

        mCheckbox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isfavorite == true){
                    //remove from database
                    IdeaService ideaService = ServiceBuilder.buildService(IdeaService.class);
                    Call<Void> deleteRequest = ideaService.deleteIdea(getArguments().getInt(ARG_ITEM_ID));

                    deleteRequest.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> request, Response<Void> response) {
                            Intent intent = new Intent(context, IdeaListActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Void> request, Throwable t) {
                            Toast.makeText(context, "Failed to delete item.", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    //add to favorites
                }
            }
        });

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            Activity activity = this.getActivity();
            final CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

            IdeaService ideaService = ServiceBuilder.buildService(IdeaService.class);
            Call<Idea> request = ideaService.getIdea(getArguments().getInt(ARG_ITEM_ID));

            request.enqueue(new Callback<Idea>() {
                @Override
                public void onResponse(Call<Idea> request, Response<Idea> response) {
                    mItem = response.body();

                    mTextViewCountry.setText(mItem.getCountry());
                    mTextViewLatitude.setText(mItem.getLatitude());
                    mTextViewLongitude.setText(mItem.getLongitude());
                    mTextViewWindProbability.setText(mItem.getWindProbability());
                    mTextViewWhereToGo.setText(mItem.getWhereToGo());

                    if (appBarLayout != null) {
                        appBarLayout.setTitle(mItem.getCountry());
                    }
                }

                @Override
                public void onFailure(Call<Idea> request, Throwable t) {
                    Toast.makeText(context, "Failed to retrieve item.", Toast.LENGTH_SHORT).show();
                    ;
                }
            });
        }


        return rootView;
    }


}
