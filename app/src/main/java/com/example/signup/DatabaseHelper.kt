package com.example.signup

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "notes.db",null , 1) {
    private val databaseSQLite : SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table user (email text PRIMARY KEY , mobile INTEGER , location text , password text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun saveUser(newUser : User): Boolean{
        val contentValues = ContentValues()
        contentValues.put("email",newUser.email)
        contentValues.put("mobile", newUser.mobile)
        contentValues.put("location",newUser.mobile)
        contentValues.put("password", newUser.password)
        if(databaseSQLite.insert("user",null,contentValues) < 0){
            return false
        }
        return true
    }

    fun readUser(email : String) : User?{
        var user : User? = null
        val cursor : Cursor = databaseSQLite.rawQuery("SELECT * FROM user WHERE email = '$email'", null)
        if(cursor.count < 1){
            Log.e("DB read", " No User")
        }
        else {
            while (cursor.moveToNext()){
                val email = cursor.getString(0)
                val mobile = cursor.getInt(1)
                val location = cursor.getString(2)
                val password = cursor.getString(3)
               user = User(email,mobile,location, password )
            }
        }
        return user
    }
}