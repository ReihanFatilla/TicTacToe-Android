package com.zealsasia.reift.tictactoe

import android.app.Application
import com.zealsasia.reift.tictactoe.di.dataSourceModule
import com.zealsasia.reift.tictactoe.di.networkModule
import com.zealsasia.reift.tictactoe.di.ticTacToeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(ticTacToeModule, networkModule, dataSourceModule)
        }
    }
}