package sv.com.jsoft.microsoft.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Attachment {
    @JsonProperty("@odata.type")
    private String odataType;
    private String name;
    private String contentType;
    private String contentBytes;
}
