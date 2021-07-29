package calculator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/calculator")
@Produces(MediaType.APPLICATION_JSON)
public class CalculatorResource {
    private final List<MathFormat> logs;

    public CalculatorResource(){
        this.logs = new ArrayList<>();
    }

    @GET
    @Path("add")
    public long add(@QueryParam("first") long first,@QueryParam("second") long second){
        long answer = first+second;
        logs.add(new MathFormat("add",first,second,answer));
        return answer;
    }

    @GET
    @Path("sub")
    public long sub(@QueryParam("first") long first,@QueryParam("second") long second){
        long answer = first-second;
        logs.add(new MathFormat("sub",first,second,answer));
        return answer;
    }

    @GET
    @Path("mul")
    public long mul(@QueryParam("first") long first,@QueryParam("second") long second){
        long answer = first*second;
        logs.add(new MathFormat("mul",first,second,answer));
        return answer;
    }

    @GET
    @Path("div")
    public long div(@QueryParam("first") long first,@QueryParam("second") long second){
        long answer = first/second;
        logs.add(new MathFormat("div",first,second,answer));
        return answer;
    }

    @GET
    @Path("audit")
    public List<MathFormat> audit(){
        return logs;
    }
}
