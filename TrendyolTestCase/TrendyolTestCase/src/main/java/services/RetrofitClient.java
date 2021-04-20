package services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public final class RetrofitClient {
    public static Retrofit instance;

    private RetrofitClient() {
    }

    public static Retrofit getInstance() {
        if (instance == null)
            instance = new Retrofit
                    .Builder()
                    .baseUrl("https://607ae5ddbd56a60017ba329c.mockapi.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
        return instance;
    }


    private static OkHttpClient getClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(100, TimeUnit.SECONDS);
        return clientBuilder.build();
    }
}
