package com.easyhz.noffice.core.datastore.datasource.user

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.easyhz.noffice.core.common.di.Dispatcher
import com.easyhz.noffice.core.common.di.NofficeDispatchers
import com.easyhz.noffice.core.datastore.di.UserDataStore
import com.easyhz.noffice.core.datastore.util.UserKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    @Dispatcher(NofficeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    @UserDataStore private val dataStore: DataStore<Preferences>
) : UserLocalDataSource {
    private val isFirstRun = booleanPreferencesKey(UserKey.IS_FIRST_RUN.key)

    override suspend fun getFirstRun(): Result<Boolean> = withContext(dispatcher) {
        runCatching {
            val preferences = dataStore.data.first()
            return@runCatching preferences[isFirstRun]
                ?: throw generateNullException(UserKey.IS_FIRST_RUN)
        }
    }

    override suspend fun updateFirstRun(newValue: Boolean): Unit = withContext(dispatcher) {
        dataStore.edit { preferences ->
            preferences[isFirstRun] = newValue
        }
    }

    private fun generateNullException(userKey: UserKey): Exception {
        return Exception("${userKey.key} is null")
    }
}