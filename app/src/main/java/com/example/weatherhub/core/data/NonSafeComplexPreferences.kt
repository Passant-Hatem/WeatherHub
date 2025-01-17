package com.example.weatherhub.core.data


import android.util.Log
import androidx.annotation.Keep
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.weatherhub.core.mics.emitFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Keep
class NonSafeComplexPreferences(
    val dataStore: DataStore<Preferences>,
    val json: Json = Json { ignoreUnknownKeys = true }
) {
    val TAG = "ComplexPreferences";
    inline fun <reified T> saveItem(item: T, key: String): Flow<T> =
        emitFlow {
            val prefKey = stringPreferencesKey(key)
            dataStore.edit { it[prefKey] = json.encodeToString(item) }
            item
        }.catch { error: Throwable ->
            Log.e(TAG, "saveItem: $item", error)
        }

    inline fun <reified T> getItemOnce(key: String, default: T? = null): Flow<T?> =
        dataStore.data.map { pref: Preferences ->
            val prefKey = stringPreferencesKey(key)
            val item = pref[prefKey]
            if (item != null) {
                json.decodeFromString<T>(item)
            } else {
                default
            }
        }.catch { emit(default) }.take(1)


    fun removeItem(key: String): Flow<Boolean> = emitFlow {
        Log.d(TAG, "removeItem: $key")
        val prefKey = stringPreferencesKey(key)
        dataStore.edit { it.remove(prefKey) }
        true
    }

    fun clear(): Flow<Boolean> = emitFlow {
        dataStore.edit { it.clear() }
        true
    }
}