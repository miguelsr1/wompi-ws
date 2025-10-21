package sv.com.jsoft.microsoft.payload;

import lombok.Data;


@Data
public class SendMailRequest {
    private Message message;
    private String saveToSentItems;

    public SendMailRequest() {
        this.message = new Message();
    }
}