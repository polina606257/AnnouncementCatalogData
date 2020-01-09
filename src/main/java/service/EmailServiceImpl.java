package service;

import domain.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import repository.EmailRepository;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Class EmailrServiceImpl implements all methods from {@link EmailService}
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Service
public class EmailServiceImpl implements EmailService {

    /**
     * Field emailRepository is an object of {@link EmailRepository}, it can find author emails for mailing suitable ad
     */
    @Autowired
    private EmailRepository emailRepository;

    /**
     * Field sender is JavaMailSender object that provides basic functionality for sending simple mails
     */
    @Autowired
    private JavaMailSender sender;


    /**
     * Method gets author emails from database for mailing suitable advertisement when parameters new created
     * advertisement {@link domain.Ad} match to parameters of suitable ad {@link domain.SuitableAd}
     * @param ad new created advertisement
     * @return list of emails with suitable ad where we will send advertisement
     */
    private List<String> findBySuitableAd(Ad ad) {
        return emailRepository.findBySuitableAd(ad.getName(), ad.getPrice(), ad.getCategory().getCategory());
    }


    /**
     * Method send emails to all author who has inquiry for suitable ad
     * @param ad new input Ad
     */
    public void sendEmailWithSuitableAd(Ad ad) {

        List<String> emailList = findBySuitableAd(ad);
        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            emailList. forEach(em -> {
                try {
                    helper.setTo(em);
                    helper.setSubject("SuitableAd for you");
                    helper.setText("<html>\n" + "<body>\n" + "<p>" + ad.getName() + "\n" + ad.getText() + "\n" +
                            ad.getPrice() + "</p>\n" + "</body>\n" + "</html>", true);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                sender.send(message);
            });
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
