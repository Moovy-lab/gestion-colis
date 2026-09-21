package sahelys.gestioncolis.back.enums;

/**
 * Cette énumération représente les différents statuts par lesquels un colis peut passer.
 *
 * @author PC2
 */

public enum StatutColis {
    ENREGISTRE,
    PRISE_EN_CHARGE,
    EN_TRANSIT,
    ARRIVE_AGENCE,
    LIVRE;

    public boolean peutTransitionnerVers(StatutColis suivant) {
        return suivant != null && suivant.ordinal() == this.ordinal() + 1;
    }
}
