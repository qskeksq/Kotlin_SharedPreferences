package database.kotlin.flow9.net.spbasic

import android.content.Context
import android.content.SharedPreferences

class PropertyManager private constructor() {

    // SharedPreference 객체
    private val mSP: SharedPreferences
    // SharedPreference.Editor 객체
    private val mEditor: SharedPreferences.Editor
    // 저장할 파일명
    private val SP_NAME = "my_sharedPreference"

    companion object {

        // 싱긅턴 패턴을 통해 하나의 객체만 생성할 수 있도록 합니다
        private var pm: PropertyManager? = null

        fun getInstance(): PropertyManager {
            if (pm == null) {
                pm = PropertyManager()
            }
            return pm as PropertyManager
        }
    }

    init {
        mSP = GlobalApp.context!!.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        mEditor = mSP.edit()
    }

    // 문자열 저장
    fun putString(context: Context, key: String, value: String) {
        mEditor.putString(key, value)
        // 저장 후 commit()을 호출해야 실제 값이 저장됩니다
        mEditor.commit()
    }

    // 정수값 저장
    fun putInt(context: Context, key: String, value: Int) {
        mEditor.putInt(key, value)
        mEditor.commit()
    }

    // boolean값 저장
    fun putBoolean(context: Context, key: String, value: Boolean) {
        mEditor.putBoolean(key, value)
        mEditor.commit()
    }

    // 문자열 데이터 조회
    fun getString(context: Context, key: String): String {
        return mSP.getString(key, "")
    }

    // 정수 데이터 조회
    fun getInt(context: Context, key: String): Int {
        return mSP.getInt(key, 0)
    }

    // boolean 데이터 조회
    fun getBoolean(context: Context, key: String): Boolean {
        return mSP.getBoolean(key, false)
    }

    // value 삭제
    fun delete(context: Context, key: String) {
        mEditor.remove(key)
        mEditor.commit()
    }

}
