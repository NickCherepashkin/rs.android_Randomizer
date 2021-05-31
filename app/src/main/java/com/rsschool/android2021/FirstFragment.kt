package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var minET: EditText? = null
    private var maxET: EditText? = null

    private lateinit var action: IFragmentAction

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        // находим edittext по id
        minET = view.findViewById(R.id.min_value)
        // находим edittext по id
        maxET = view.findViewById(R.id.max_value)

        // в переменную вводим считанное максимальное значение
        val max = Integer.parseInt(/*maxET?.text.toString()*/"60")

        action = activity as IFragmentAction

        generateButton?.setOnClickListener {
            // в переменндим считанное минимальное значение
            if(minET?.text.toString().isEmpty()) {
                Toast.makeText(view.context, "Введите минимальное значение", Toast.LENGTH_LONG).show()
            } else if (maxET?.text.toString().isEmpty()) {
                Toast.makeText(view.context, "Введите максимальное значение", Toast.LENGTH_LONG).show()
            } else {
                val min = Integer.parseInt(minET?.text.toString())
                val max = Integer.parseInt(maxET?.text.toString())
                action.sendValues(min, max)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}

/*interface ActionFirstFragment{
    fun sendValues(vararg numbers: Int)
}*/