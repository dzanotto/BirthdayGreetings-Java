package cleancode.domain;

public interface MessageSender {
    void sendMessage(String sender, String subject, String body, String recipient);
}
