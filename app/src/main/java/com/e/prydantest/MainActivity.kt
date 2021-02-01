package com.e.prydantest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.e.prydantest.database.MyDatabase
import com.e.prydantest.network.ServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var myDatabase: MyDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myDatabase = Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java, MyDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()

        btnLogin.setOnClickListener {
            if (edt_username.text!!.isNotEmpty()) {
                if (edt_password.text!!.isNotEmpty()) {
                    val compositeDisposable = CompositeDisposable()
                    compositeDisposable.add(
                        ServiceBuilder.buildService().login(
                            edt_username.text.toString(),
                            edt_password.text.toString()
                        ).observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                { response ->
                                    Toast.makeText(
                                        this@MainActivity,
                                        response.errorMessage,
                                        Toast.LENGTH_LONG
                                    ).show()

                                    Thread {
                                        myDatabase!!.daoAccess()!!.insertTodo(response.user)
                                        Log.e("-->","getFromRoomDatabase->"+myDatabase!!.daoAccess()!!.fetchAllLogin()!!.get(0))

                                    }.start()

                                },
                                { t ->
                                    Toast.makeText(
                                        this@MainActivity,
                                        t.message,
                                        Toast.LENGTH_LONG
                                    ).show()
                                })
                    )
                } else {
                    Toast.makeText(
                        this,
                        getText(R.string.error_invalid_password),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {

                Toast.makeText(
                    this,
                    getText(R.string.error_invalid_username),
                    Toast.LENGTH_SHORT
                ).show()


            }
        }
    }
}