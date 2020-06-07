package com.example.shopitee.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopitee.models.ItemCartModel
import dagger.Provides
import javax.inject.Singleton


@Database(entities = arrayOf(ItemCartModel::class), version = 2)
abstract class ShopiteeDatabase: RoomDatabase() {

    private val DB_NAME: String = "word_database"

    abstract fun cartDao(): CartDao


    companion object {
        @Volatile
        private var INSTANCE: ShopiteeDatabase? = null


        fun getDatabase(context: Context): ShopiteeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShopiteeDatabase::class.java,
                        "word_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
/*
        @JvmOverloads
        fun <R> getContinuation(onFinished: BiConsumer<R?, Throwable?>, dispatcher: CoroutineDispatcher = Dispatchers.Default): Continuation<R> {
            return object : Continuation<R> {
                override val context: CoroutineContext
                    get() = dispatcher

                @RequiresApi(Build.VERSION_CODES.N)
                override fun resumeWith(result: Result<R>) {
                    onFinished.accept(result.getOrNull(), result.exceptionOrNull())
                }
            }
        }

        fun getDatabaseAsync(context: Context): CompletableFuture<ShopiteeDatabase> =
                GlobalScope.future { getDatabase(context) }

 */
    }


}