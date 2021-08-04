import calculator.CalculatorApplication;
import calculator.CalculatorConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Base64;
import java.util.HashMap;

public class Tests {
    public static final String BASE_URI = "http://0.0.0.0:8080/";
    private Client client;
    private CalculatorApplication app;

    private String getValidBasicAuth() {
        return "Basic " + Base64.getEncoder().encodeToString("ef:ef".getBytes());
    }

    private String getInvalidBasicAuth() {
        return "Basic " + Base64.getEncoder().encodeToString("ef:ed".getBytes());
    }

    @Before
    public void Setup() throws Exception {
        client = ClientBuilder.newClient();
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
    public void testDivideByZero(){
         Response response=client.target(BASE_URI)
                 .path("div")
                 .queryParam("first",2)
                 .queryParam("second",0)
                 .request()
                 .get();
         assertEquals(400,response.getStatus());
    }

    @Test
    public void testAudit() {
        // put a function in the history
        assertEquals(new Integer(10),
                client.target(BASE_URI)
                        .path("mul")
                        .queryParam("first", 2)
                        .queryParam("second", 5)
                        .request()
                        .get(Integer.class));

        // Should require creds
        assertEquals(401, client.target(BASE_URI).path("audit").request().get().getStatus());

        // Should reject bad creds
        assertEquals(401,
                client.target(BASE_URI)
                        .path("audit")
                        .request()
                        .header(HttpHeaders.AUTHORIZATION, getInvalidBasicAuth())
                        .get()
                        .getStatus());

        // Should give history with valid creds
        assertEquals("[{\"op\":\"mul\",\"first\":2,\"second\":5,\"answer\":10}]",
                client.target(BASE_URI)
                        .path("audit")
                        .request()
                        .header(HttpHeaders.AUTHORIZATION, getValidBasicAuth())
                        .get(String.class));
    }

    @Test
    public void testMultiply() {
            long response=client.target(BASE_URI)
                    .path("mul")
                    .queryParam("first", 2)
                    .queryParam("second", 5)
                    .request()
                    .get(long.class);
            assertEquals(10,response);
    }
}
