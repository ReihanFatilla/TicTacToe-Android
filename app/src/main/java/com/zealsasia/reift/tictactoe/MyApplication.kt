package com.zealsasia.reift.tictactoe

import android.app.Application
import com.zealsasia.reift.tictactoe.di.dataSourceModule
import com.zealsasia.reift.tictactoe.di.networkModule
import com.zealsasia.reift.tictactoe.di.ticTacToeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    ticTacToeModule, networkModule, dataSourceModule
                )
            )
        }
    }
}