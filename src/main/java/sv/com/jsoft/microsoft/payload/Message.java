package sv.com.jsoft.microsoft.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Message {
    private String subject;
    private Body body;
    private List<Recipient> toRecipients;
    private List<Attachment> attachments;

    public Message() {
        this.body = new Body();
        this.toRecipients = new ArrayList<>();
        this.attachments = new ArrayList<>();
    }
}
