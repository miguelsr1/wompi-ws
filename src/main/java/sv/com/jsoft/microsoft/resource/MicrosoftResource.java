package sv.com.jsoft.microsoft.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.com.jsoft.microsoft.payload.TokenResponse;
import sv.com.jsoft.microsoft.service.MicrosoftService;

@Path("/api/microsoft")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MicrosoftResource {

    @Inject
    MicrosoftService microsoftService;

}
