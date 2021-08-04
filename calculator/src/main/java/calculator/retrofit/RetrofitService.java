package calculator.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET(".")
    Call<String> ping();

    @GET("/add")
    Call<Long> add(@Query("first") long first, @Query("second") long second);
}
