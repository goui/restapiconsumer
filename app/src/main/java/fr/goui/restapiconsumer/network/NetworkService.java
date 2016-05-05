package fr.goui.restapiconsumer.network;

import java.io.IOException;
import java.util.List;

import fr.goui.restapiconsumer.model.User;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 *
 */
public interface NetworkService {

    String BASE_URL = "http://192.168.1.12:8080/";

    @GET("userFacade/getAllUsers")
    Observable<List<User>> getAllUsers();

    @POST("userFacade/subscribe")
    Observable<Boolean> createUser(@Body User user);
    
    class Factory {
        public static NetworkService create() {
            // Define the interceptor, add authentication headers
            Interceptor interceptor = new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Basic dXNlcjoxMjM0NTY=")
                            .addHeader("Content-Type", "application/json")
                            .addHeader("X-Requested-With", "XMLHttpRequest")
                            .build();
                    return chain.proceed(newRequest);
                }
            };

            // Add the interceptor to OkHttpClient
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.interceptors().add(interceptor);
            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
            return retrofit.create(NetworkService.class);
        }
    }
}
