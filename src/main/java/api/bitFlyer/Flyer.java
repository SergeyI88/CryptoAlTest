package api.bitFlyer;

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
