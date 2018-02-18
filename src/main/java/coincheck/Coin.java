package coincheck;

import coincheck.Orders;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Coin {
    public static Orders get() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coincheck.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Orders.class);
    }
}
