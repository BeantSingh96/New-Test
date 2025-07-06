package com.example.sonaproject.core.utils


import android.content.Context
import android.util.AttributeSet
import android.view.Gravity.CENTER_VERTICAL
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sonaproject.R
import androidx.core.content.withStyledAttributes

class CounterView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var minValue = 0
    private var maxValue = Int.MAX_VALUE
    private var currentValue = 0
    private var step = 1

    private var btnMinus: AppCompatImageView
    private var btnPlus: AppCompatImageView
    private var tvCount: AppCompatTextView

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_counter, this, true)

        btnMinus = findViewById(R.id.btnMinus)
        btnPlus = findViewById(R.id.btnPlus)
        tvCount = findViewById(R.id.tvCount)

        setupButtons()
        parseAttributes(attrs)
    }

    private fun setupButtons() {
        btnMinus.setOnClickListener { decrement() }
        btnPlus.setOnClickListener { increment() }
        updateButtonStates()
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        attrs?.let {
            context.withStyledAttributes(it, R.styleable.CounterView) {
                minValue = getInt(R.styleable.CounterView_minValue, 0)
                maxValue = getInt(R.styleable.CounterView_maxValue, Int.MAX_VALUE)
                currentValue = getInt(R.styleable.CounterView_startValue, 0)
                step = getInt(R.styleable.CounterView_step, 1)
            }
        }

        setValue(currentValue)
    }

    fun increment() {
        setValue(currentValue + step)
    }

    fun decrement() {
        setValue(currentValue - step)
    }

    fun setValue(newValue: Int) {
        currentValue = newValue.coerceIn(minValue, maxValue)
        tvCount.text = currentValue.toString()
        updateButtonStates()

        onValueChangeListener?.onValueChanged(currentValue)
    }

    fun getValue(): Int = currentValue

    private fun updateButtonStates() {
        btnMinus.isEnabled = currentValue > minValue
        btnPlus.isEnabled = currentValue < maxValue


        btnMinus.setBackgroundDrawable(
            context.drawable(
                if (btnMinus.isEnabled) R.drawable.dr_stroke_enable
                else R.drawable.dr_stroke_disable
            )
        )
        btnPlus.setBackgroundDrawable(
            context.drawable(
                if (btnMinus.isEnabled) R.drawable.dr_stroke_enable
                else R.drawable.dr_stroke_disable
            )
        )
    }

    private var onValueChangeListener: OnValueChangeListener? = null

    fun setOnValueChangeListener(listener: OnValueChangeListener) {
        this.onValueChangeListener = listener
    }

    interface OnValueChangeListener {
        fun onValueChanged(newValue: Int)
    }
}