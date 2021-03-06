package bitstamp;

import entity.MyList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;

public interface Orders {
    @GET("/api/v2/transactions/{currency_pair}")
    Call<MyList<Trade>> getData(@Path("currency_pair") String resourceName);
}