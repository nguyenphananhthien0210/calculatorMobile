package hcmute.edu.vn.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result, solution;
    MaterialButton buttonDelete, buttonBracket, buttonPercent;
    MaterialButton buttonDivide, buttonMultiply, buttonSubtract, buttonAdd, buttonEqual;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);

        assignId(buttonDelete, R.id.button_delete);
        assignId(buttonBracket, R.id.button_bracket);
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
        assignId(buttonC, R.id.button_c);

    }

    void assignId(MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();

        if(buttonText.equals("C")){
            solution.setText("");
            result.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            solution.setText(result.getText());
            return;
        }

        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solution.setText(dataToCalculate);
    }

    String getResult(String data){
        return "Calculated";
    }
}