package sv.com.jsoft.microsoft.payload;

import lombok.Data;

import java.util.List;

@Data
public class MailRequest {
    private String to;
    private String subject;
    private String body;
    private List<AttachmentRequest> attachments;
}
