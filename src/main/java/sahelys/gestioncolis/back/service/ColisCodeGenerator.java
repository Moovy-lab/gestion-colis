package sahelys.gestioncolis.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sahelys.gestioncolis.back.repository.ColisRepository;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Cette classe génère le numéro de suivi et le code de retrait uniques d'un colis.
 *
 * @author PC2
 */

@Component
@RequiredArgsConstructor
public class ColisCodeGenerator {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String CARACTERES_ALEATOIRES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int NB_ESSAIS_MAX = 10;

    private final ColisRepository colisRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    public String genererNumeroSuivi() {
        for (int essai = 0; essai < NB_ESSAIS_MAX; essai++) {
            String candidat = "COL-" + LocalDate.now().format(DATE_FORMAT) + "-" + genererSuffixeAleatoire();
            if (!colisRepository.existsByNumeroSuivi(candidat)) {
                return candidat;
            }
        }
        throw new IllegalStateException("Impossible de générer un numéro de suivi unique");
    }

    public String genererCodeRetrait() {
        return String.format("%06d", secureRandom.nextInt(1_000_000));
    }

    private String genererSuffixeAleatoire() {
        StringBuilder suffixe = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            suffixe.append(CARACTERES_ALEATOIRES.charAt(secureRandom.nextInt(CARACTERES_ALEATOIRES.length())));
        }
        return suffixe.toString();
    }
}
