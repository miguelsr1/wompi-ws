package sv.com.jsoft.microsoft.client;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sv.com.jsoft.microsoft.payload.SendMailRequest;

@RegisterRestClient(configKey = "graph-microsoft-api")
public interface MicrosfotMailClient {

    @POST
    @Path("v1.0/users/{from}/sendMail")
    Response sendMail(@PathParam("from") String from, SendMailRequest request);
}
