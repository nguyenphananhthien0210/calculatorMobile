package hcmute.edu.vn.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result, dataCompute;
    MaterialButton buttonDelete, buttonBackSpace, buttonPercent;
    MaterialButton buttonDivide, buttonMultiply, buttonSubtract, buttonAdd, buttonEqual;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonClearAC, buttonDot;
    String dataToCalculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        dataCompute = findViewById(R.id.data_compute);

        assignId(buttonDelete, R.id.button_delete);
      //  assignId(buttonBackSpace, R.id.button_backSpace);
        assignId(buttonPercent, R.id.button_percent);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonSubtract, R.id.button_subtract);
        assignId(buttonAdd, R.id.button_add);
        assignId(buttonEqual, R.id.button_equal);
        assignId(button0, R.id.button_zero);
        assignId(button1, R.id.button_one);
        assignId(button2, R.id.button_two);
        assignId(button3, R.id.button_three);
        assignId(button4, R.id.button_four);
        assignId(button5, R.id.button_five);
        assignId(button6, R.id.button_six);
        assignId(button7, R.id.button_seven);
        assignId(button8, R.id.button_eight);
        assignId(button9, R.id.button_nine);
        assignId(buttonDot, R.id.button_dot);
        assignId(buttonClearAC, R.id.button_clearAC);

        buttonBackSpace=findViewById(R.id.button_backSpace);
        //setting action event for button back space
        buttonBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onDeleteResult();
            }
        });
    }

    void assignId(MaterialButton btn, int id){
        // Assign ID variable for button Material common
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
         dataToCalculate= dataCompute.getText().toString();


        if(buttonText.equals("AC")){
            // AC delete all operator from calculator
            onCLearResult();
            return;
        }
        if(buttonText.equals("C")){
            return;
        }

        if(buttonText.equals("=")){
            result.setText(onEqual(dataToCalculate));
            return;
        }else{
            if(isLastZero(dataToCalculate)){
                dataCompute.setText(buttonText);
                return;
            }
            dataToCalculate = dataToCalculate+buttonText;
        }
        dataCompute.setText(dataToCalculate);
    }


    public void onCLearResult(){
        //setting dataCompute Textview and Dat to calculate
        dataToCalculate="0";
        dataCompute.setText("0");
        result.setText("0");

    }
    public void onDeleteResult(){
        if(dataToCalculate.length()==1){
            onCLearResult();
            return;
        }
        dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        dataCompute.setText(dataToCalculate);

    }
    public boolean isLastZero(String str){
        if(String.valueOf(str.charAt(0)).equals("0")){
            return true;
        }
        return false;
    }
    public String onEqual(String data){
        //setting when data to compute longer
        if(data.length()>20){
            result.setTextSize(32);

        }
        else{
            result.setTextSize(64);
        }
        try{
            //Use the mozilla library from rhino
           Context context  = Context.enter();
           //The method call to enter a new context => essential a container of execution of a javascript code.

            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
        // Rhino library to evaluate JavaScript expressions and manipulate the resulting values in Java code.
    }
}