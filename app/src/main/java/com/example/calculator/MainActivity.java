package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    boolean zeroIsNew = true;
    EditText editText;
    String operation = "";
    String oldNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
    }

    public void clickNumber(View view) {
        //если стоит дефолтный ноль, то при нажатии на кнопку он сотртется
        if(zeroIsNew){
            editText.setText("");
            zeroIsNew = false;
        }
        String number = editText.getText().toString();
        switch (view.getId()){
            case R.id.bu1: number = number + "1"; break;
            case R.id.bu2: number = number + "2"; break;
            case R.id.bu3: number = number + "3"; break;
            case R.id.bu4: number = number + "4"; break;
            case R.id.bu5: number = number + "5"; break;
            case R.id.bu6: number = number + "6"; break;
            case R.id.bu7: number = number + "7"; break;
            case R.id.bu8: number = number + "8"; break;
            case R.id.bu9: number = number + "9"; break;
            case R.id.bu0: number = number + "0"; break;
            case R.id.buPlusMinus:
                //если изначально number == ""
                if(numberIsNull(number)){
                    number = "-";
                }else{
                    //если в начале '-', то number запишется без первого символа,
                    //substring возьмет наш "-" как первое значание и запишет строку после первого символа
                    // иначе добавится символ '-', а после него присоединится number
                    if(plusMinusIsPresent(number)){
                        number = number.substring(1);
                    } else{
                        number = "-" + number;
                    }
                }

                break;

            case R.id.buDot:
                if (dotIsPresent(number)){
                    //если точка true(уже есть в числе), то действия не будет
                } else {
                    number = number + ".";
                }
                break;
        }
        editText.setText(number);
    }



    public void operation(View view) {
        zeroIsNew = true;
        oldNum = editText.getText().toString();
        switch (view.getId()) {
            case R.id.buPlus: operation = "+"; break;
            case R.id.buMinus: operation = "-"; break;
            case R.id.buMultiplu: operation = "*"; break;
            case R.id.buDevide:operation = "/" ; break;
        }
        editText.setText(oldNum + operation);
    }

    public void clickEqual(View view) {
        String newNumber = editText.getText().toString();
        double result = 0.0;
        switch(operation){
            case "*": result = Double.parseDouble(oldNum) * Double.parseDouble(newNumber); break;
            case "/": result = Double.parseDouble(oldNum) / Double.parseDouble(newNumber); break;
            case "+": result = Double.parseDouble(oldNum) + Double.parseDouble(newNumber); break;
            case "-": result = Double.parseDouble(oldNum) - Double.parseDouble(newNumber); break;
        }
        editText.setText(result+"");
    }
    public void cliclClear(View view) {
        editText.setText("0");
        zeroIsNew = true;
        operation ="";
    }

    public void clickPersent(View view) {
        //при пустой операции(только при нажатии процента без +,-,*,/), будем получать 1% от числа
        if (operation == ""){
            String number = editText.getText().toString();
            double temp = Double.parseDouble(number)/100;
            number = temp+"";
            editText.setText(number);
        }else {
            String newNumber = editText.getText().toString();
            double result = 0.0;
            switch(operation){
                case "*": result = Double.parseDouble(oldNum) * Double.parseDouble(newNumber)/100; break;
                case "/": result = Double.parseDouble(oldNum) / Double.parseDouble(newNumber)*100; break;
                case "+": result = Double.parseDouble(oldNum) + (Double.parseDouble(oldNum) * (Double.parseDouble(newNumber)/100)); break;
                case "-": result = Double.parseDouble(oldNum) - Double.parseDouble(oldNum) * (Double.parseDouble(newNumber)/100); break;
            }
            editText.setText(result+"");

        }
    }
    private boolean numberIsNull(String number) {
        return (number.equals(""));
    }
    private boolean plusMinusIsPresent(String number) {
        if(number.charAt(0) == '-'){
            return true;
        }else {
            return false;
        }
    }

    //проверка на то была ли введена точка. Если да, то повторно её ввести нельзя.
    private boolean dotIsPresent(String number) {
        if (number.indexOf(".") == -1) {
            return false;
        } else
            return true;
    }


}
