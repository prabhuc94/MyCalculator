package chandran_prabhu.mycalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_home.*
import android.text.Selection.getSelectionStart
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder


class HomeActivity : AppCompatActivity(), View.OnClickListener,View.OnLongClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    override fun onStart() {
        super.onStart()
        typingArea.requestFocus()
        typingArea.showSoftInputOnFocus = false

        oneBtn.setOnClickListener(this)
        twoBtn.setOnClickListener(this)
        threeBtn.setOnClickListener(this)
        fourBtn.setOnClickListener(this)
        fiveBtn.setOnClickListener(this)
        sixBtn.setOnClickListener(this)
        sevenBtn.setOnClickListener(this)
        eightBtn.setOnClickListener(this)
        nineBtn.setOnClickListener(this)
        zeroBtn.setOnClickListener(this)
        dotBtn.setOnClickListener(this)
        addBtn.setOnClickListener(this)
        minusBtn.setOnClickListener(this)
        divideBtn.setOnClickListener(this)
        crossBtn.setOnClickListener(this)
        deleteBtn.setOnClickListener(this)
        eqaBtn.setOnClickListener(this)

        deleteBtn.setOnLongClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.oneBtn->appender(1)
            R.id.twoBtn->appender(2)
            R.id.threeBtn->appender(3)
            R.id.fourBtn->appender(4)
            R.id.fiveBtn->appender(5)
            R.id.sixBtn->appender(6)
            R.id.sevenBtn->appender(7)
            R.id.eightBtn->appender(8)
            R.id.nineBtn->appender(9)
            R.id.zeroBtn->appender(0)
            R.id.dotBtn->appender(".")
            R.id.addBtn->appender("+")
            R.id.minusBtn->appender("-")
            R.id.crossBtn->appender("*")
            R.id.divideBtn->appender("÷")
            R.id.deleteBtn->deappender()
            R.id.eqaBtn->calculator(typingArea.text)
        }
    }

    override fun onLongClick(v: View?): Boolean {
        when(v!!.id){
            R.id.deleteBtn->
            {
                typingArea.text.clear()
                valueArea.text = null
                return true
            }
        }
        return false
    }

    private fun appender(value : Any?){
        typingArea.text.append("${value!!}")
    }

    private fun deappender(){
        val input = typingArea.selectionStart
        val myText = typingArea.text.toString()
        if (input >= 0) {
            val before = myText.substring(0, input)
            val after = myText.substring(input)
            if (before.isNotEmpty()) {
                val newBefore = before.substring(0, before.length - 1)
                val outputText = newBefore + after
                typingArea.setText(outputText)
                typingArea.setSelection(input - 1)
            } else{
                valueArea.text = null
            }
        } else {
            valueArea.text = null
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculator(value: Any?){
        if (value != null) {
            val expression = ExpressionBuilder("${value!!}").build()
            try {
                valueArea.text = "${expression.evaluate()}"
            } catch (arithmetic: ArithmeticException) {
                valueArea.text = "Error"
            }
        }
    }
}
