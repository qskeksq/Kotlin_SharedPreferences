package database.kotlin.flow9.net.spbasic

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

/**
 * Preference based on File System not on XML file.
 * Ready() to prepare for input
 * Commit() to write
 * Read() to read
 */
class TextPref @Throws(IOException::class)
constructor(private val mPath: String) {
    private var mBuf: StringBuilder? = null

    init {
        val file = File(mPath)
        if (!file.exists()) {
            val fos = FileOutputStream(file)
            fos.write(HEADER.toByteArray())
            fos.close()
        }
    }

    fun Reset() {
        val file = File(mPath)
        if (file.exists()) {
            file.delete()
        }
    }

    fun Ready(): Boolean {
        try {
            val fis = FileInputStream(mPath)
            val avail = fis.available()
            val data = ByteArray(avail)
            while (fis.read(data) != -1);
            fis.close()
            mBuf = StringBuilder(avail)
            mBuf!!.append(String(data))
        } catch (e: IOException) {
            return false
        }

        return true
    }

    fun CommitWrite(): Boolean {
        val file = File(mPath)
        try {
            val fos = FileOutputStream(file)
            fos.write(mBuf!!.toString().toByteArray())
            fos.close()
        } catch (e: IOException) {
            return false
        }

        mBuf = null
        return true
    }

    fun EndReady() {
        mBuf = null
    }

    private fun FindIdx(name: String): Int {
        val key = "__$name="
        val idx = mBuf!!.indexOf(key)
        return if (idx == -1) {
            -1
        } else {
            idx + key.length
        }
    }

    fun WriteString(name: String, value: String) {
        val idx = FindIdx(name)
        if (idx == -1) {
            mBuf!!.append("__")
            mBuf!!.append(name)
            mBuf!!.append("=")
            mBuf!!.append(value)
            mBuf!!.append("\n")
        } else {
            val end = mBuf!!.indexOf("\n", idx)
            mBuf!!.delete(idx, end)
            mBuf!!.insert(idx, value)
        }
    }

    fun ReadString(name: String, def: String): String {
        val idx = FindIdx(name)
        if (idx == -1) {
            return def
        } else {
            val end = mBuf!!.indexOf("\n", idx)
            return mBuf!!.substring(idx, end)
        }
    }

    fun WriteInt(name: String, value: Int) {
        WriteString(name, Integer.toString(value))
    }

    fun ReadInt(name: String, def: Int): Int {
        val s = ReadString(name, "__none")
        if (s == "__none") {
            return def
        }
        try {
            return Integer.parseInt(s)
        } catch (e: NumberFormatException) {
            return def
        }

    }

    fun WriteLong(name: String, value: Long) {
        WriteString(name, java.lang.Long.toString(value))
    }

    fun ReadLong(name: String, def: Long): Long {
        val s = ReadString(name, "__none")
        if (s == "__none") {
            return def
        }
        try {
            return java.lang.Long.parseLong(s)
        } catch (e: NumberFormatException) {
            return def
        }

    }

    fun WriteBoolean(name: String, value: Boolean) {
        WriteString(name, if (value) "1" else "0")
    }

    fun ReadBoolean(name: String, def: Boolean): Boolean {
        val s = ReadString(name, "__none")
        if (s == "__none") {
            return def
        }
        try {
            return if (s == "1") true else false
        } catch (e: NumberFormatException) {
            return def
        }

    }

    fun WriteFloat(name: String, value: Float) {
        WriteString(name, java.lang.Float.toString(value))
    }

    fun ReadFloat(name: String, def: Float): Float {
        val s = ReadString(name, "__none")
        if (s == "__none") {
            return def
        }
        try {
            return java.lang.Float.parseFloat(s)
        } catch (e: NumberFormatException) {
            return def
        }

    }

    fun BulkwriteReady(length: Int) {
        mBuf = StringBuilder(length)
        mBuf!!.append(HEADER)
        mBuf!!.append("\n")
    }

    fun BulkWrite(name: String, value: String) {
        mBuf!!.append("__")
        mBuf!!.append(name)
        mBuf!!.append("=")
        mBuf!!.append(value)
        mBuf!!.append("\n")
    }

    fun DeleteKey(name: String) {
        val idx = FindIdx(name)
        if (idx != -1) {
            val end = mBuf!!.indexOf("\n", idx)
            mBuf!!.delete(idx - (name.length + 3), end + 1)
        }
    }

    companion object {
        val HEADER = "__Text Preference File__\n"
    }

}
