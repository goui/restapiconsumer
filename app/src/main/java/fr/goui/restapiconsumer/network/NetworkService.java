package fr.goui.restapiconsumer.network;

import java.util.List;

import fr.goui.restapiconsumer.model.User;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 *
 */
public interface NetworkService {

    String BASE_URL = "http://localhost:8080/";

    @GET("userFacade/getAllUsers")
    Observable<List<User>> listUsers();

    class Factory {
        public static NetworkService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(NetworkService.class);
        }
    }
}
