package database.kotlin.flow9.net.spbasic.settings

import android.app.PendingIntent.getActivity
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceManager

import database.kotlin.flow9.net.spbasic.R

class SettingsFragment : PreferenceFragmentCompat() {

    private var mPrefs: SharedPreferences? = null

    override fun onCreatePreferences(bundle: Bundle, s: String) {
        addPreferencesFromResource(R.xml.settings)
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity())
        mPrefs!!.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == "check_box_preference_1") {

            }
        }
    }


}
