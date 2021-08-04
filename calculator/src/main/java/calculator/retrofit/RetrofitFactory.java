package calculator.retrofit;

import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.Retrofit;

public class RetrofitFactory {

    public static RetrofitService build(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return retrofit.create(RetrofitService.class);
    }
}
