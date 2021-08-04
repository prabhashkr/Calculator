package calculator.exceptionmappers;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CalculatorExceptionMapper implements ExceptionMapper<ClientErrorException> {
    @Override
    public Response toResponse(ClientErrorException exception) {
        return Response.status(400).entity(exception.getMessage()).type(MediaType.APPLICATION_JSON).build();
    }
}
