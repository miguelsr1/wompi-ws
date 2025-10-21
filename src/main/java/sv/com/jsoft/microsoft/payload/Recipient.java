package sv.com.jsoft.microsoft.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Recipient {
    private EmailAddress emailAddress;
}
