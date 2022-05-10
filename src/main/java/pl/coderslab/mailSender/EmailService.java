package pl.coderslab.mailSender;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

}
