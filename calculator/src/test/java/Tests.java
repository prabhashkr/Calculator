import org.junit.Test;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Base64;

public class Tests {
    public static final String BASE_URI = "http://0.0.0.0:8080/";
    private final Client client=ClientBuilder.newClient();

    private String getValidBasicAuth() {
        return "Basic " + Base64.getEncoder().encodeToString("ef:ef".getBytes());
    }

    private String getInvalidBasicAuth() {
        return "Basic " + Base64.getEncoder().encodeToString("ef:ed".getBytes());
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
    public void testAudit() throws Exception {
        assertEquals(401, client.target(BASE_URI).path("audit").request().get().getStatus());

        assertEquals(401,
                client.target(BASE_URI)
                        .path("audit")
                        .request()
                        .header(HttpHeaders.AUTHORIZATION, getInvalidBasicAuth())
                        .get()
                        .getStatus());

        assertEquals(200,
                client.target(BASE_URI)
                        .path("audit")
                        .request()
                        .header(HttpHeaders.AUTHORIZATION, getValidBasicAuth())
                        .get().getStatus());
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
