package pl.coderslab.emailSender;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

}
