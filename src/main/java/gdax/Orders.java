package gdax;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface Orders {
    @GET("/products/{product-id}/trades")
    Call<List<Trade>> getData(@Path("product-id") String resourceName);
}