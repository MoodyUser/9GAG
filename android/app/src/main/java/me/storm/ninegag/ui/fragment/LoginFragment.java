package me.storm.ninegag.ui.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;

import butterknife.ButterKnife;
import me.storm.ninegag.R;
import me.storm.ninegag.api.GisterApi;
import me.storm.ninegag.data.GsonRequest;
import me.storm.ninegag.model.Category;
import me.storm.ninegag.ui.MainActivity;
import me.storm.ninegag.ui.adapter.DrawerAdapter;
import me.storm.ninegag.util.TaskUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this, contentView);
        //executeRequest(new GsonRequest(GisterApi.me(), UserHolder.class, responseListener(), errorListener()));
        return contentView;
    }

    class UserHolder {

    }

    private Response.Listener<UserHolder> responseListener() {
        return new Response.Listener<UserHolder>() {
            @Override
            public void onResponse(final UserHolder response) {
                TaskUtils.executeAsyncTask(new AsyncTask<Object, Object, Object>() {
                    @Override
                    protected Object doInBackground(Object... params) {
                        UserHolder user = response;
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        super.onPostExecute(o);
                    }
                });
            }
        };
    }
}
