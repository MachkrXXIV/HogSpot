package com.team.hogspot.model

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.team.hogspot.model.geospot.Difficulty
import com.team.hogspot.model.geospot.GeoSpot
import com.team.hogspot.model.geospot.GeoSpotDao
import com.team.hogspot.model.relations.UserGeoSpotCrossRef
import com.team.hogspot.model.user.User
import com.team.hogspot.model.user.UserDao
import com.team.hogspot.util.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime

// Annotates class to be a Room Database with a table (entity) of app's models
@Database(entities = [User::class, GeoSpot::class, UserGeoSpotCrossRef::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun geoSpotDao(): GeoSpotDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            Log.d("AppRoomDatabase", "Getting AppRoomDatabase")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "hogspot_database"
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
                    populateDatabaseWithMockData(database.userDao(),database.geoSpotDao())
                }
            }
        }

        suspend fun populateDatabaseWithMockData(userDao: UserDao, spotDao: GeoSpotDao) {
            // Delete all content here.
            Log.d("UserRoomDatabase", "Populating UserRoomDatabase")
            userDao.deleteAll()

            // Add sample users.
            val user1 = User(1, "XXXking_bobXXX", "papayabanana@example.com", emptyList())
            val user2 = User(2, "stuartNotLittle", "stuieParaTue@example.com", emptyList())
            userDao.insert(user1)
            userDao.insert(user2)

            val spot1 = GeoSpot(1, 1, "The Big Tree", "tree.jpg", "A big tree", Difficulty.MEDIUM, "Look up", 0.0, 0.0, LocalDateTime.now())
            val spot2 = GeoSpot(2, 2, "The Small Tree", "tree.jpg", "A small tree", Difficulty.EASY, "Look down", 0.0, 0.0, LocalDateTime.now())
            spotDao.insert(spot1)
            spotDao.insert(spot2)
        }
    }
}