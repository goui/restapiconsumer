package fr.goui.restapiconsumer;

import android.app.Application;
import android.content.Context;

import fr.goui.restapiconsumer.network.NetworkService;

/**
 *
 */
public class MyApplication extends Application {

    private NetworkService mNetworkService;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public NetworkService getNetworkService() {
        if (mNetworkService == null) {
            mNetworkService = NetworkService.Factory.create();
        }
        return mNetworkService;
    }
}
