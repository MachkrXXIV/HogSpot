package com.team.hogspot.model

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.team.hogspot.model.user.User
import com.team.hogspot.model.user.UserDao
import com.team.hogspot.util.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of app's models
@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            Log.d("UserRoomDatabase", "Getting UserRoomDatabase")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "user_database"
                ).addCallback(UserDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
    private class UserDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabaseWithMockUsers(database.userDao())
                }
            }
        }

        suspend fun populateDatabaseWithMockUsers(userDao: UserDao) {
            // Delete all content here.
            Log.d("UserRoomDatabase", "Populating UserRoomDatabase")
            userDao.deleteAll()

            // Add sample users.
            val user1 = User(1, "XXXking_bobXXX", "papayabanana@example.com", emptyList())
            val user2 = User(2, "stuartNotLittle", "stuieParaTue@example.com", emptyList())
            userDao.insert(user1)
        }
    }
}