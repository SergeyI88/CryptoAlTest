package bitFlyer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Flyer {
    public static Orders get() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.bitflyer.jp/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Orders.class);
    }
}
