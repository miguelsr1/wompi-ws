package sv.com.jsoft.microsoft.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SendMailRequest {
    private Message message;
    private String saveToSentItems;

    public SendMailRequest() {
        this.message = new Message();
    }
}

@Data
class Message {
    private String subject;
    private Body body;
    private List<Recipient> toRecipients;
    private List<Attachment> attachments;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public List<Recipient> getToRecipients() {
        return toRecipients;
    }

    public void setToRecipients(List<Recipient> toRecipients) {
        this.toRecipients = toRecipients;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}

@Data
class Body {
    private String contentType;
    private String content;

    // Getters and setters
}

@Data
class Recipient {
    private EmailAddress emailAddress;

    // Getters and setters
}

@Data
class EmailAddress {
    private String address;

    // Getters and setters
}

@Data
class Attachment {
    @JsonProperty("@odata.type")
    private String odataType;
    private String name;
    private String contentType;
    private String contentBytes;

    // Getters and setters
}