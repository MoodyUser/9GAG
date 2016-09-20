package me.storm.ninegag.ui.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import me.storm.ninegag.R;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by storm on 14-4-16.
 */
public class PreferenceFragment extends android.preference.PreferenceFragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);

/*
        Preference versionPreference = findPreference(getString(R.string.pref_key_version));
        PackageInfo packageInfo;
        try {
            packageInfo = getActivity().getPackageManager().getPackageInfo(
                    getActivity().getPackageName(), PackageManager.GET_CONFIGURATIONS);
            versionPreference.setTitle("v" + packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

*/



    }




}
