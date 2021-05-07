package sg.edu.rp.c346.id20041162.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
EditText amountText,paxText,discountText;
ToggleButton svsToggle,gstToggle;
RadioGroup paymentRadio;
Button splitBtn, resetBtn;
TextView billDisplay, eachPayDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountText = findViewById(R.id.amountInput);
        paxText = findViewById(R.id.numPaxInput);
        discountText = findViewById(R.id.discountInput);
        svsToggle = findViewById(R.id.serviceToggle);
        gstToggle = findViewById(R.id.gstToggle);
        paymentRadio = findViewById(R.id.paymentGroup);
        splitBtn = findViewById(R.id.splitBtn);
        resetBtn = findViewById(R.id.resetBtn);
        billDisplay = findViewById(R.id.billDisplay);
        eachPayDisplay = findViewById(R.id.eachPayDisplay);



        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                double tax = 0.0;
                double total = 0.0;
                double discount = 0.0;
                int pax = Integer.parseInt(paxText.getText().toString());
                String billDisplayText = "";
                double amount = Double.parseDouble(amountText.getText().toString());

                if (svsToggle.isChecked()){
                    tax += 0.1;
                }
                if (gstToggle.isChecked()){
                    tax += 0.07;
                }
                total += amount+(amount*tax);

                discount = Double.parseDouble(discountText.getText().toString());
                if (TextUtils.isEmpty(discountText.getText().toString())){
                    discountText.setError("Your message");
                    return;
                }
                else{
                    total -= total*(discount/100);
                }

                billDisplayText += total;
                String eachPayText = "";
                eachPayText += total/pax;

                int checkedRadioID = paymentRadio.getCheckedRadioButtonId();
                if(checkedRadioID == R.id.cashRadio){
                    eachPayText += " in cash";

                }
                else{
                    eachPayText += " via PayNow to 912345678";
                }


                billDisplay.setText(billDisplayText);


                eachPayDisplay.setText(eachPayText);

            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                amountText.getText().clear();
                paxText.getText().clear();
                discountText.getText().clear();
                svsToggle.setChecked(false);
                gstToggle.setChecked(false);

                paymentRadio.clearCheck();

                billDisplay.setText("");
                eachPayDisplay.setText("");
            }
        });

    }
}