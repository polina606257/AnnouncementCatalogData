package service;

import domain.Ad;
import domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private JavaMailSender sender;

    private List<String> findBySuitableAd(Ad ad) {
        return authorRepository.findBySuitableAd(ad);
    }

    public void sendEmailWithSuitableAd(Ad ad) {

        List<String> emailList = findBySuitableAd(ad);

        MimeMessage message = sender.createMimeMessage();

        try {
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
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        sender.send(message);
    }
}
