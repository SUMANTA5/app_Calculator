package com.sumanta.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var Toolbar:androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toolbar = findViewById(R.id.toolBar)
        setupToolbar()


        //number
        tvOne.setOnClickListener{ appendonExpresstion("1",true)}
        tvTow.setOnClickListener{ appendonExpresstion("2",true)}
        tvThree.setOnClickListener{ appendonExpresstion("3",true)}
        tvFour.setOnClickListener{ appendonExpresstion("4",true)}
        tvFive.setOnClickListener{ appendonExpresstion("5",true)}
        tvSix.setOnClickListener{ appendonExpresstion("6",true)}
        tvSeven.setOnClickListener{ appendonExpresstion("7",true)}
        tvEight.setOnClickListener{ appendonExpresstion("8",true)}
        tvNine.setOnClickListener{ appendonExpresstion("9",true)}
        tvZero.setOnClickListener{ appendonExpresstion("0",true)}
        tvDot.setOnClickListener{ appendonExpresstion(".",true)}


        //Operators
        tvPlus.setOnClickListener{ appendonExpresstion("+",false)}
        tvMinus.setOnClickListener{ appendonExpresstion("-",false)}
        tvMul.setOnClickListener{ appendonExpresstion("*",false)}
        tvDivide.setOnClickListener{ appendonExpresstion("/",false)}
        tvOpen.setOnClickListener{ appendonExpresstion("(",false)}
        tvClose.setOnClickListener{ appendonExpresstion(")",false)}

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }
        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if (string.isNotEmpty()){
                tvExpression.text = string.substring(0,string.length-1)
            }
            tvResult.text = ""
        }
        tvEquals.setOnClickListener {
            try {
              val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()){
                    tvResult.text = longResult.toString()
                }else{
                    tvResult.text = result.toString()
                }
            }catch (e:Exception){
                Log.d("Exception","message : " + e.message)
            }
        }
    }

    fun appendonExpresstion(string: String,canClear: Boolean){

        if (tvResult.text.isNotEmpty()){
            tvExpression.text = ""
        }
        if (canClear){
            tvResult.text = ""
            tvExpression.append(string)
        }else{
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }



    fun setupToolbar() {
        setSupportActionBar(Toolbar)
        supportActionBar?.title = "Calculator"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
