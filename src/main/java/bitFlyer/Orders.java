package bitFlyer;

import entity.MyList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface Orders {
    @GET("/v1/executions")
    Call<MyList<Trade>> getData(@Query("product_code") String resourceName);
}