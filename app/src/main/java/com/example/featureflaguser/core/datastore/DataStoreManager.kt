package com.example.featureflaguser.core.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(
    name = "user_preferences"
)
private val TOKEN_KEY = stringPreferencesKey("token")
class DataStoreManager @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    suspend fun saveToken(token: String){
        context.dataStore.edit { preferences->
            preferences[TOKEN_KEY] = token

        }
    }
    fun getToken(): Flow<String?>{
        return context.dataStore.data.map { preferences->
            preferences[TOKEN_KEY]
        }
    }

    suspend fun clearToken(){
        context.dataStore.edit {preferences->
            preferences.remove(TOKEN_KEY)
        }
    }

}