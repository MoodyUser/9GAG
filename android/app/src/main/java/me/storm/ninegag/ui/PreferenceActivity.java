package me.storm.ninegag.ui;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import me.storm.ninegag.R;
import me.storm.ninegag.ui.fragment.PreferenceFragment;
import me.storm.ninegag.view.swipeback.SwipeBackActivity;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by storm on 14-4-16.
 */
public class PreferenceActivity extends SwipeBackActivity {
    @InjectView(R.id.lang_icons)
    LinearLayout langIcons;
    @InjectView(R.id.git_like_switch)
    Switch likeSwitch;
    @InjectView(R.id.logout_button)
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.action_settings);
        setContentView(R.layout.activity_preferences);
       // getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferenceFragment())
         //       .commit();
    }
    @OnCheckedChanged(R.id.git_like_switch) void githubLikes(CompoundButton buttonView,
                                                             boolean isChecked) {

        if(isChecked){

        }else{

        }

        Log.e("PreferenceFragment", "git_like_switch OnCheckedChanged");
    }

    @OnClick(R.id.logout_button) void logoutOnClick() {
        Log.e("PreferenceFragment","logout_button onclick");
    }

    private PhotoViewAttacher mAttacher;
}
