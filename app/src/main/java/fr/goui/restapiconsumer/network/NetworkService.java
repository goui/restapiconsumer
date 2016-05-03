package fr.goui.restapiconsumer.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public interface NetworkService {

    String BASE_URL = "";

    class Factory {
        public static NetworkService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(NetworkService.class);
        }
    }
}
