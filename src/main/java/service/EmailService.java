package service;

import domain.Ad;

import java.math.BigDecimal;

/**
 * EmailService interface serves for mailing suitable ad process, binds realization part with user
 * @author Polina Shcherbinina
 * @version 1.1
 */
public interface EmailService {

    /**
     * Method send emails to all author who has inquiry for suitable ad
     * {@link repository.EmailRepository#findBySuitableAd(String, BigDecimal, String)}
     * @param ad new input Ad
     */
    void sendEmailWithSuitableAd(Ad ad);
}
