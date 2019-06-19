package com.kutluoglu.foursquaredemo.main.features.venueDetail;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.kutluoglu.foursquaredemo.R;
import com.kutluoglu.foursquaredemo.base.BaseFragment;
import com.kutluoglu.foursquaredemo.main.features.venueList.ItemListActivity;
import com.kutluoglu.presentation.models.venue.ViewVenue;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends BaseFragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The ViewVenue content this fragment is presenting.
     */
    private ViewVenue mItem;
    private CollapsingToolbarLayout appBarLayout;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = getArguments().getParcelable(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.getVenueName());
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = this.getActivity();
        appBarLayout = activity.findViewById(R.id.toolbar_layout);
        if (mItem != null && appBarLayout != null) {
            appBarLayout.setTitle(mItem.getVenueName());
        }
    }
}
