package database.kotlin.flow9.net.spbasic.settings

import android.os.Bundle
import android.support.v7.preference.ListPreference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceScreen

import database.kotlin.flow9.net.spbasic.R


class KakaoSettings : PreferenceFragmentCompat() {

    private var soundPreference: ListPreference? = null
    private val keywordSoundPreference: ListPreference? = null
    private val keywordScreen: PreferenceScreen? = null

    override fun onCreatePreferences(bundle: Bundle, s: String) {
        addPreferencesFromResource(R.xml.kakao_settings)
        soundPreference = findPreference("sound") as ListPreference

    }
}
