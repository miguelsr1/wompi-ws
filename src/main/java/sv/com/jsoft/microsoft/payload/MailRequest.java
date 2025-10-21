package sv.com.jsoft.microsoft.payload;

import java.util.List;

public class MailRequest {
    private String to;
    private String subject;
    private String body;
    private List<AttachmentRequest> attachments;
}
