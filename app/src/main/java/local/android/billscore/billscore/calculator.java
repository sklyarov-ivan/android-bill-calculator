package local.android.billscore.billscore;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class calculator extends Activity {

    private static final String BILL_TOTAL = "BILL_TOTAL";
    private static final String CUSTOM_PERSENT = "CUSTOM_PERSENT";

    private double currentBillTotal;
    private int currentCustomPersent;
    private EditText tip10EditText;
    private EditText tip15EditText;
    private EditText tip20EditText;
    private EditText total15EditText;
    private EditText total20EditText;
    private EditText total10EditText;
    private EditText billEditText;
    private TextView customTipTextView;
    private EditText tipCustomEditText;
    private EditText totalCustomEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        if (savedInstanceState == null) {
            currentBillTotal = 0.0;
            currentCustomPersent = 18;
        } else {
            currentBillTotal = savedInstanceState.getDouble(BILL_TOTAL);
            currentCustomPersent = savedInstanceState.getInt(CUSTOM_PERSENT);
        }
        tip10EditText = (EditText) findViewById(R.id.tip10EditText);
        tip15EditText = (EditText) findViewById(R.id.tip15EditText);
        tip20EditText = (EditText) findViewById(R.id.tip20EditText);
        total10EditText = (EditText) findViewById(R.id.total10EditText);
        total15EditText = (EditText) findViewById(R.id.total15EditText);
        total20EditText = (EditText) findViewById(R.id.total20EditText);

        customTipTextView = (TextView) findViewById(R.id.customTipTextView);
        tipCustomEditText = (EditText) findViewById(R.id.tipCustomEditText);
        totalCustomEditText = (EditText) findViewById(R.id.totalCustomEditText);
        billEditText = (EditText) findViewById(R.id.billEditText);

        billEditText.addTextChangedListener(billEditTextWatcher);

        SeekBar customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }


    private void updateStandard()
    {

        double tenPercentTip = currentBillTotal * .1;
        double tenPercentTotal = currentBillTotal + tenPercentTip;
        tip10EditText.setText(String.format("%.02f",tenPercentTip));
        total10EditText.setText(String.format("%.02f",tenPercentTotal));

        double fiftinPercentTip = currentBillTotal * .15;
        double fiftinPercentTotal = currentBillTotal + fiftinPercentTip;
        tip15EditText.setText(String.format("%.02f",fiftinPercentTip));
        total15EditText.setText(String.format("%.02f",fiftinPercentTotal));

        double twentyPercentTip = currentBillTotal * .2;
        double twentyPercentTotal = currentBillTotal + twentyPercentTip;
        tip20EditText.setText(String.format("%.02f",twentyPercentTip));
        total20EditText.setText(String.format("%.02f",twentyPercentTotal));
    }

    private void updateCustom()
    {
        customTipTextView.setText(currentCustomPersent+"%");
        double customTipAmount = currentBillTotal*currentCustomPersent*.01;
        double customTotalAmount = currentBillTotal+customTipAmount;
        tipCustomEditText.setText(String.format("%.02f",customTipAmount));
        totalCustomEditText.setText(String.format("%.02f", customTotalAmount));
    }

    private OnSeekBarChangeListener customSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            currentCustomPersent = seekBar.getProgress();
            updateCustom();
        }
    };

    private TextWatcher billEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            try
            {
                currentBillTotal = Double.parseDouble(charSequence.toString());
            }
            catch (NumberFormatException e)
            {
                currentBillTotal = 0.0;
            }
            updateStandard();
            updateCustom();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putDouble(BILL_TOTAL,currentBillTotal);
        outState.putDouble(CUSTOM_PERSENT,currentCustomPersent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
