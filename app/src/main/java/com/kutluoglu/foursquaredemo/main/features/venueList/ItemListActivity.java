package com.kutluoglu.foursquaredemo.main.features.venueList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.kutluoglu.foursquaredemo.R;
import com.kutluoglu.foursquaredemo.base.BaseActivity;
import com.kutluoglu.foursquaredemo.login.LoginActivity;
import com.kutluoglu.foursquaredemo.main.features.venueDetail.ItemDetailActivity;
import com.kutluoglu.foursquaredemo.main.features.venueDetail.ItemDetailFragment;
import com.kutluoglu.presentation.models.venue.ViewVenue;
import com.kutluoglu.presentation.resource.ResourceState;
import com.kutluoglu.presentation.viewModels.getVenues.GetVenuesViewModel;
import com.kutluoglu.presentation.viewModels.login.LoginViewModel;
import com.kutluoglu.presentation.viewModels.logout.LogoutViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends BaseActivity implements HasSupportFragmentInjector {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    private GetVenuesViewModel venuesViewModel;
    private LoginViewModel loginViewModel;
    private LogoutViewModel logoutViewModel;
    private View recyclerView;
//    static int currentPos = 0;
//    static boolean itemClick;
//    private String adapterPosition = "AdapterPosition";
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        AndroidInjection.inject(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        fab = findViewById(R.id.fab);
        fab.hide();

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;

        /*if (savedInstanceState != null) {
            currentPos = savedInstanceState.getInt(adapterPosition);
            itemClick = false;
        }*/

        initializeViewModel();
    }

    private void initializeViewModel() {
        venuesViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(GetVenuesViewModel.class);

        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LoginViewModel.class);

        logoutViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LogoutViewModel.class);

        observeLoginLiveData();
    }

    private void observeLoginLiveData() {
        loginViewModel.getIsUserLoginLiveData().observe(this, resource -> {
            if (resource != null) {
                if (resource.getStatus() == ResourceState.SUCCESS) {
                    if (resource.getData()) {
                        loginViewModel.loginCompleted();
                        observeVenueLiveData();
                        observeLogoutLiveData();
                        fab.show();
                    } else openLoginActivity();
                } else if (resource.getStatus() == ResourceState.ERROR) {
                    openLoginActivity();
                }
            }
        });

        loginViewModel.isUserLogin();
    }

    private void observeVenueLiveData() {
        venuesViewModel.getVenueLiveData().observe(this, listResource -> {
            if (listResource.getStatus().equals(ResourceState.LOADING)) {
                showSpinner();
            } else if (listResource.getStatus().equals(ResourceState.SUCCESS)) {
                dismissSpinner();
                if(listResource.getData() != null) {
                    setupRecyclerView((RecyclerView) recyclerView, listResource.getData());
                }

            } else {
                dismissSpinner();
                Log.e("Data", listResource.getMessage());
            }
        });
        venuesViewModel.showVenues();
    }

    private void openLoginActivity() {
//        currentPos = 0;
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void observeLogoutLiveData() {
        logoutViewModel.getLogoutLiveData().observe(this, resource -> {
            if (resource != null) {
                if (resource.getStatus() == ResourceState.SUCCESS) {
                    logoutViewModel.logoutCompleted();
                    showLogoutSnackbar();
                } else if (resource.getStatus() == ResourceState.ERROR) {

                }
            }
        });

        setFabClickListener();
    }

    private void showLogoutSnackbar() {
        Snackbar snackbar = Snackbar.make(getWindow().getDecorView(), "Logging Out...", Snackbar.LENGTH_LONG);
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                closeTheApp();
            }
        });

        snackbar.show();
    }

    private void setFabClickListener() {
        fab.setOnClickListener(view -> {
            logoutViewModel.logout();
        });

    }

    private void closeTheApp() {
//        currentPos = 0;
        finish();
    }
    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<ViewVenue> viewList) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, viewList, mTwoPane));
//        recyclerView.smoothScrollToPosition(currentPos);
    }

    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        *//*if(itemClick) {
            outState.putInt(adapterPosition, currentPos);
        }*//*

        outState.putInt(adapterPosition, currentPos);
    }*/

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        private  final List<ViewVenue> mVenues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                itemClick = true;
                ViewVenue item = (ViewVenue) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
//                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.getId());
                    arguments.putParcelable(ItemDetailFragment.ARG_ITEM_ID, item);
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item);
                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      List<ViewVenue> items,
                                      boolean twoPane) {
            mVenues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mVenues.get(position).getVenueName());
//            holder.mContentView.setText(mVenues.get(position).getId());
            holder.mContentView.setText("");

            holder.itemView.setTag(mVenues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
//            currentPos = position;
        }

        @Override
        public int getItemCount() {
            return mVenues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = view.findViewById(R.id.id_text);
                mContentView = view.findViewById(R.id.content);
            }
        }
    }
}
