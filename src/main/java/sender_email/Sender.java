package sender_email;

import configuration.ConfigApp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.Paths;


public class Sender {
    public static void main(String[] args) throws MessagingException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MailApp.class);

//        sendSimpleEmail(context);
        sendEmailWithAttachments(context);
    }

    private static void sendSimpleEmail(AnnotationConfigApplicationContext context) {
        JavaMailSenderImpl sender = context.getBean(JavaMailSenderImpl.class);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("polina.shcherbinina@gmail.com");
        message.setSubject("Greetings");
        message.setText("Good day");

        sender.send(message);
    }

    private static void sendEmailWithAttachments(AnnotationConfigApplicationContext context) throws MessagingException {
        JavaMailSenderImpl sender = context.getBean(JavaMailSenderImpl.class);

        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("polina.shcherbinina@gmail.com");
        helper.setSubject("Greetings");
        helper.setText("<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>My First Heading</h1>\n" +
                "\n" +
                "<p>My first paragraph.</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>", true);

        File file = Paths.get("JPA_Mini_Book.pdf").toFile();

        helper.addAttachment("Good book", file);


        sender.send(message);
    }
}

