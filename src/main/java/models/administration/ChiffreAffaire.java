package models.administration;

public class ChiffreAffaire {
    private final String month;
    private final double totalMontant;

    public ChiffreAffaire(String month, double totalMontant) {
        this.month = month;
        this.totalMontant = totalMontant;
    }

    public double getTotalMontant() {
        return totalMontant;
    }

    public double totalMontantProperty() {
        return totalMontant;
    }

    public String getMonth() {
        return month;
    }

}