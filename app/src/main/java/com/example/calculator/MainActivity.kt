package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {


        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        onNumberClicked()
        onOperatorClicked()



    }





    private fun onOperatorClicked() {

        binding.btnJam.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()
                &&
                binding.txtJavab.text.isEmpty()) {

                val myChar = binding.txtExpression.text.last()
                if (
                    myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'
                ) {

                    appendText("+")

                }

            }

        }


        binding.btnMenha.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()

                &&
                binding.txtJavab.text.isEmpty()) {

                val myChar = binding.txtExpression.text.last()
                if (
                    myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'
                ) {

                    appendText("-")

                }

            }

        }

        binding.btnZarb.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()

                &&
                binding.txtJavab.text.isEmpty()) {

                val myChar = binding.txtExpression.text.last()
                if (
                    myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'
                ) {

                    appendText("*")

                }

            }

        }

        binding.btnTaghsim.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()
                &&
                binding.txtJavab.text.isEmpty()   ) {

                val myChar = binding.txtExpression.text.last()
                if (
                    myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'
                ) {

                    appendText("/")

                }

            }

        }

        binding.btnParantezBaz.setOnClickListener {
            appendText("(")
        }

        binding.btnParantezBaste.setOnClickListener {


            if (binding.txtExpression.text.isNotEmpty()&&
                binding.txtJavab.text.isEmpty() ){


                appendText(")")
            }
        }

        binding.btnAC.setOnClickListener {

            binding.txtExpression.text = ""
            binding.txtJavab.text = ""

        }

        binding.btnPakidan.setOnClickListener {


            if ( binding.txtJavab.text.isEmpty()) {


                val oldText = binding.txtExpression.text.toString()

                if (oldText.isNotEmpty()) {

                    binding.txtExpression.text = oldText.substring(0, oldText.length - 1)

                }
            }

        }


        binding.btnMosavi.setOnClickListener {

            try {

                val expression = ExpressionBuilder(binding.txtExpression.text.toString()).build()
                val result = expression.evaluate()

                val longResult = result.toLong()

                // 135.0 == 135
                if (result == longResult.toDouble()) {
                    binding.txtJavab.text = longResult.toString()
                } else {
                    binding.txtJavab.text = result.toString()
                }

            } catch (e: Exception) {

                binding.txtExpression.text = ""
                binding.txtJavab.text = ""
                Toast.makeText(this, "ارور رخ داد !", Toast.LENGTH_LONG).show()

            }

        }


    }

    private fun onNumberClicked() {

        binding.btn0.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()
                &&
                binding.txtJavab.text.isEmpty() ) {
                appendText("0")
            }

        }

        binding.btn1.setOnClickListener {
            appendText("1")
        }

        binding.btn2.setOnClickListener {
            appendText("2")
        }

        binding.btn3.setOnClickListener {
            appendText("3")
        }

        binding.btn4.setOnClickListener {
            appendText("4")
        }

        binding.btn5.setOnClickListener {
            appendText("5")
        }

        binding.btn6.setOnClickListener {
            appendText("6")
        }

        binding.btn7.setOnClickListener {
            appendText("7")
        }

        binding.btn8.setOnClickListener {
            appendText("8")
        }

        binding.btn9.setOnClickListener {
            appendText("9")
        }




        binding.btnDot.setOnClickListener {
            if (binding.txtExpression.text.isEmpty() || binding.txtJavab.text.isNotEmpty()) appendText("0.")
            else if (binding.txtExpression.text.last() != '.') {
                var countOperators = 0
                var countDots = 0
                binding.txtExpression.text.forEach {
                    if (it == '+' || it == '-' || it == '*' || it == '/') countOperators++
                    if (it == '.') countDots++
                }
                if (countDots <= countOperators) appendText(".")
            }
        }







    }

    private fun appendText(newText: String) {

        if (binding.txtJavab.text.isNotEmpty()) {
            binding.txtExpression.text = ""
        }

        binding.txtJavab.text = ""
        binding.txtExpression.append(newText)

        val viewTree: ViewTreeObserver = binding.horizontalScrollViewTxtExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewTxtExpression.scrollTo(binding.txtExpression.width, 0)
            }
        })

    }




}