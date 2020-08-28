package com.example.apnaniwas.apnaniwasDB.connection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestClient {
    public static <S> S createService(Class<S> serviceClass) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> chain.proceed(chain.request()
                        .newBuilder()
                        .addHeader("key", VariableBag.MEMBER_URL_KEY)
                        .build()))
                .build();

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(VariableBag.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = builder
                .build();
        return retrofit.create(serviceClass);


    }
}


