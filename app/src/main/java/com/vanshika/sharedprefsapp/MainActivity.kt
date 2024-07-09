package com.vanshika.sharedprefsapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vanshika.sharedprefsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding ?= null
    var sharedPreferences  : SharedPreferences ?= null
    var editor : SharedPreferences.Editor ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        sharedPreferences = getSharedPreferences(resources.getString(R.string.app_name),
            MODE_PRIVATE)
        editor = sharedPreferences?.edit()
        binding?.etEnterYourName?.setText(sharedPreferences?.getString("name","Default"))
        binding?.etEnterYourRollNo?.setText(sharedPreferences?.getLong("rollNo",123).toString())
        binding?.rbMale?.isChecked = sharedPreferences?.getBoolean("gender",false)?:false
        binding?.btnSave?.setOnClickListener {
            if (binding?.etEnterYourName?.text?.toString().isNullOrEmpty()){
                binding?.etEnterYourName?.error = resources.getString(R.string.enter_your_name)
            }else if (binding?.etEnterYourRollNo?.text?.toString().isNullOrEmpty()){
                binding?.etEnterYourRollNo?.error = resources.getString(R.string.enter_roll_no)
            }else if (binding?.rgGender?.checkedRadioButtonId == -1){
                Toast.makeText(this, resources.getString(R.string.select_one), Toast.LENGTH_SHORT).show()
            }else{
                editor?.putString("name",binding?.etEnterYourName?.text?.toString())
                var isMale = if (binding?.rbMale?.isChecked == true){
                    true
                }else{
                    false
                }
                editor?.putBoolean("gender",isMale)
                editor?.putLong("rollNo",binding?.etEnterYourRollNo?.text?.toString()?.toLongOrNull() ?: 0L)
                editor?.apply()
            }
        }
    }
}