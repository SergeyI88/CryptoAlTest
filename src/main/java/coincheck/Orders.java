package coincheck;

import coincheck.Trade;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Orders {
    @GET("/api/trades/")
    Call<Trade> getData(@Query("pair") String resourceName);
}