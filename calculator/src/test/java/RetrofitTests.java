import calculator.CalculatorApplication;
import calculator.CalculatorConfiguration;
import calculator.retrofit.RetrofitFactory;
import calculator.retrofit.RetrofitService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Response;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.HashMap;

public class RetrofitTests {
    public static final String BASE_URI = "http://0.0.0.0:8080/";
    private CalculatorApplication app;

    @Before
    public void Setup() throws Exception {
        HashMap<String,String> props= new HashMap<>();
        props.put("authentication.method","BASIC");
        app=new CalculatorApplication(new CalculatorConfiguration(props));
        app.start();
    }

    @After
    public void tearDown() throws Exception {
        app.stop();
    }


    @Test
    public void testRetrofitPing() throws IOException {
        RetrofitService newClient = RetrofitFactory.build(BASE_URI);
        Response<String> response=newClient.ping().execute();
        assertEquals("Hi there!! Welcome to Calculator",response.body());
    }

    @Test
    public void testRetrofitAdd() throws IOException {
        RetrofitService newClient = RetrofitFactory.build(BASE_URI);
        Response<Long> response=newClient.add(2,5).execute();
        assertEquals(new Long(7), response.body());
    }
}
