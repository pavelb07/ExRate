package com.tryit.pavel.exrate;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.appindexing.Action;
import com.tryit.pavel.exrate.data.model.eu.ExchangeRatesEU;
import com.tryit.pavel.exrate.data.model.gbp.ExchangeRatesGBP;
import com.tryit.pavel.exrate.data.model.pln.ExchangeRatesPLN;
import com.tryit.pavel.exrate.data.model.usd.ExchangeRatesUSD;
import com.tryit.pavel.exrate.data.remote.CurrencyAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.change)
    Button change;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.result)
    TextView result;
    @BindView(R.id.editText)
    EditText sum;
    @BindView(R.id.rate)
    TextView rate;


    double rat;
    String from;
    String to;
    String[] Array1 = new String[4];
    String[] Array2 = new String[3];
    String[] myString;
    int length;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        myString = getResources().getStringArray(R.array.Currency);
        length=myString.length;

        /*CurrencyAPI.factory.getInstance().getRates().enqueue(new Callback<ExchangeRatesEU>() {
            @Override
            public void onResponse(Call<ExchangeRatesEU> call, Response<ExchangeRatesEU> response) {
                rat = response.body().getRates().getPLN().doubleValue();
                rate.setText("Exchange rate: "+rat);
            }

            @Override
            public void onFailure(Call<ExchangeRatesEU> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed to open", Toast.LENGTH_LONG).show();
            }
        });*/
    }


    @OnClick(R.id.button)
    void currency1() {
new MaterialDialog.Builder(this)
                .title(R.string.string_arr_title)
                .items(myString)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        int q = which;
                        from = myString[q];
                        button.setText(from);



                        return true;
                    }
                })
                .positiveText(R.string.choose)
                .show();
    }

    @OnClick(R.id.button2)
    void currency2() {
        int j = 0;
        for (int i = 0; i < length; i++) {
            if (button.getText().toString() != myString[i]) {
                Array2[j] = myString[i];
                j++;
            }
            else{
                continue;
            }
        }

        switch (button.getText().toString()){
            case "EUR":
                new MaterialDialog.Builder(this)
                        .title(R.string.string_arr_title)
                        .items(Array2)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                int q = which;
                                to = Array2[q];
                                button2.setText(to);

                                CurrencyAPI.factory.getInstance().getRatesEU().enqueue(new Callback<ExchangeRatesEU>() {
                                    @Override
                                    public void onResponse(Call<ExchangeRatesEU> call, Response<ExchangeRatesEU> response) {
                                        switch (to) {
                                            case "PLN":
                                                rat = response.body().getRates().getPLN();
                                                break;
                                            case "GBP":
                                                rat = response.body().getRates().getGBP();
                                                break;
                                            case "USD":
                                                rat = response.body().getRates().getUSD();
                                                break;
                                        }
                                        rate.setText("Exchange rate: " + rat);
                                    }

                                    @Override
                                    public void onFailure(Call<ExchangeRatesEU> call, Throwable t) {
                                        Toast.makeText(MainActivity.this, "Failed to open, missing internet connection", Toast.LENGTH_LONG).show();
                                    }
                                });

                                return true;
                            }
                        })
                        .positiveText(R.string.choose)
                        .show();
                break;
            case "USD":
                new MaterialDialog.Builder(this)
                        .title(R.string.string_arr_title)
                        .items(Array2)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                int q = which;
                                to = Array2[q];
                                button2.setText(to);

                                CurrencyAPI.factory.getInstance().getRatesUSD().enqueue(new Callback<ExchangeRatesUSD>() {
                                    @Override
                                    public void onResponse(Call<ExchangeRatesUSD> call, Response<ExchangeRatesUSD> response) {
                                        switch (to) {
                                            case "PLN":
                                                rat = response.body().getRates().getPLN();
                                            case "GBP":
                                                rat = response.body().getRates().getGBP();
                                                break;
                                            case "EUR":
                                                rat = response.body().getRates().getEUR();
                                                break;
                                        }
                                        rate.setText("Exchange rate: " + rat);
                                    }

                                    @Override
                                    public void onFailure(Call<ExchangeRatesUSD> call, Throwable t) {
                                        Toast.makeText(MainActivity.this, "Failed to open, missing internet connection", Toast.LENGTH_LONG).show();
                                    }
                                });

                                return true;
                            }
                        })
                        .positiveText(R.string.choose)
                        .show();
                break;
            case "PLN":
                new MaterialDialog.Builder(this)
                        .title(R.string.string_arr_title)
                        .items(Array2)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                int q = which;
                                to = Array2[q];
                                button2.setText(to);

                                CurrencyAPI.factory.getInstance().getRatesPLN().enqueue(new Callback<ExchangeRatesPLN>() {
                                    @Override
                                    public void onResponse(Call<ExchangeRatesPLN> call, Response<ExchangeRatesPLN> response) {
                                        switch (to) {
                                            case "EUR":
                                                rat = response.body().getRates().getEUR();
                                                break;
                                            case "GBP":
                                                rat = response.body().getRates().getGBP();
                                                break;
                                            case "USD":
                                                rat = response.body().getRates().getUSD();
                                                break;
                                        }
                                        rate.setText("Exchange rate: " + rat);
                                    }

                                    @Override
                                    public void onFailure(Call<ExchangeRatesPLN> call, Throwable t) {
                                        Toast.makeText(MainActivity.this, "Failed to open, missing internet connection", Toast.LENGTH_LONG).show();
                                    }
                                });

                                return true;
                            }
                        })
                        .positiveText(R.string.choose)
                        .show();
                break;
            case "GBP":
                new MaterialDialog.Builder(this)
                        .title(R.string.string_arr_title)
                        .items(Array2)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                int q = which;
                                to = Array2[q];
                                button2.setText(to);

                                CurrencyAPI.factory.getInstance().getRatesGBP().enqueue(new Callback<ExchangeRatesGBP>() {
                                    @Override
                                    public void onResponse(Call<ExchangeRatesGBP> call, Response<ExchangeRatesGBP> response) {
                                        switch (to) {
                                            case "EUR":
                                                rat = response.body().getRates().getEUR();
                                                break;
                                            case "USD":
                                                rat = response.body().getRates().getUSD();
                                                break;
                                            case "PLN":
                                                rat = response.body().getRates().getPLN();
                                                break;
                                        }
                                        rate.setText("Exchange rate: " + rat);
                                    }

                                    @Override
                                    public void onFailure(Call<ExchangeRatesGBP> call, Throwable t) {
                                        Toast.makeText(MainActivity.this, "Failed to open, missing internet connection", Toast.LENGTH_LONG).show();
                                    }
                                });

                                return true;
                            }
                        })
                        .positiveText(R.string.choose)
                        .show();
                break;
        }


    }

    @OnClick(R.id.change)
    void exchange() {

        String money = sum.getText().toString();
        if (!money.trim().isEmpty()) {
            double suma = Double.parseDouble(money);
            result.setText("Result: " + (suma * rat));

        }
        else{
            Toast.makeText(MainActivity.this,R.string.missing,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.tryit.pavel.exrate/http/host/path")
        );
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.tryit.pavel.exrate/http/host/path")
        );
    }
}
