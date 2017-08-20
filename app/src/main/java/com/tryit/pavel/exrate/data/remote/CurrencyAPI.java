package com.tryit.pavel.exrate.data.remote;

import com.tryit.pavel.exrate.data.model.eu.ExchangeRatesEU;
import com.tryit.pavel.exrate.data.model.usd.ExchangeRatesUSD;
import com.tryit.pavel.exrate.data.model.pln.ExchangeRatesPLN;
import com.tryit.pavel.exrate.data.model.gbp.ExchangeRatesGBP;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pavel on 2016-07-25.
 */
public interface CurrencyAPI {
    String url = "http://api.fixer.io/";

    @GET("latest")
    Call<ExchangeRatesEU> getRatesEU();
    @GET("latest?base=PLN")
    Call<ExchangeRatesPLN> getRatesPLN();
    @GET("latest?base=USD")
    Call<ExchangeRatesUSD> getRatesUSD();
    @GET("latest?base=GBP")
    Call<ExchangeRatesGBP> getRatesGBP();


    class factory {

        private static CurrencyAPI service;

        public static CurrencyAPI getInstance() {

            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                service = retrofit.create(CurrencyAPI.class);

                return service;
            } else {
                return service;
            }
        }
    }
}
