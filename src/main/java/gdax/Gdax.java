package gdax;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Gdax {
    public static Orders get() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.gdax.com/products/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(Orders.class);
    }
}
