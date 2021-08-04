package calculator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/")
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
    public long div(@QueryParam("first") long first,@QueryParam("second") long second)  {
        if(second==0){
            throw new ClientErrorException("Div by 0 not allowed",400);
        }
        long answer = first/second;
        logs.add(new MathFormat("div",first,second,answer));
        return answer;
    }

    @GET
    @Path("audit")
    public List<MathFormat> audit() {
        return logs;
    }

    @GET
    @Path("")
    public String ping() {
        return "Hi there!! Welcome to Calculator";
    }
}
