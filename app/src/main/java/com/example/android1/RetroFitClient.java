package com.example.android1;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



    public class RetroFitClient {
        private static  final String BASE_URL = "http://10.100.3.141:8080/api/";
        private static RetroFitClient mInstance;
        private Retrofit retrofit;

        private RetroFitClient () {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        public static synchronized RetroFitClient getInstance() {
            if (mInstance == null) {
                mInstance = new RetroFitClient();
            }
            return mInstance;
        }

        public API getAPI () {
            return retrofit.create(API.class);
        }

    }

