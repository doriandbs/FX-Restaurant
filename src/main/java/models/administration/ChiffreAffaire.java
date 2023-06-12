package models.administration;

import javafx.beans.property.*;

public class ChiffreAffaire {
    //private final StringProperty month;
    private final String month;
    private final double totalMontant;

    public ChiffreAffaire(String month, double totalMontant) {
        this.month = month;
        this.totalMontant = totalMontant;
    }
/**
    public String getMonth() {
        return month.get();
    }

    public void setMonth(String month) {
        this.month.set(month);
    }

    public String monthProperty() {
        return month;
    }*/
    public double getTotalMontant() {
        return totalMontant;
    }

    public double totalMontantProperty() {
        return totalMontant;
    }

    public String getMonth() {
        return month;
    }
/**
 private final StringProperty month1;
 private final StringProperty month2;
 private final StringProperty month3;
 private final StringProperty month4;
 private final StringProperty month5;
 private final StringProperty month6;
 private final StringProperty month7;
 private final StringProperty month8;
 private final StringProperty month9;
 private final StringProperty month10;
 private final StringProperty month11;
 private final StringProperty month12;
 private final double totalMontant;

 public chiffreAffaire(StringProperty month1, StringProperty month2, StringProperty month3, StringProperty month4, StringProperty month5, StringProperty month6, StringProperty month7, StringProperty month8, StringProperty month9, StringProperty month10, StringProperty month11, StringProperty month12, double totalMontant) {
 this.month1 = month1;
 this.month2 = month2;
 this.month3 = month3;
 this.month4 = month4;
 this.month5 = month5;
 this.month6 = month6;
 this.month7 = month7;
 this.month8 = month8;
 this.month9 = month9;
 this.month10 = month10;
 this.month11 = month11;
 this.month12 = month12;
 this.totalMontant = totalMontant;
 }

 public String getMonth1() {
 return month1.get();
 }

 public void setMonth1(String month1) {
 this.month1.set(month1);
 }

 public StringProperty month1Property() {
 return month1;
 }

 public String getMonth2() {
 return month2.get();
 }

 public void setMonth2(String month2) {
 this.month2.set(month2);
 }

 public StringProperty month2Property() {
 return month2;
 }

 public String getMonth3() {
 return month3.get();
 }

 public void setMonth3(String month3) {
 this.month3.set(month3);
 }

 public StringProperty month3Property() {
 return month3;
 }

 public String getMonth4() {
 return month4.get();
 }

 public void setMonth4(String month4) {
 this.month4.set(month4);
 }

 public StringProperty month4Property() {
 return month4;
 }

 public String getMonth5() {
 return month5.get();
 }

 public void setMonth5(String month5) {
 this.month5.set(month5);
 }

 public StringProperty month5Property() {
 return month5;
 }

 public String getMonth6() {
 return month6.get();
 }

 public void setMonth6(String month6) {
 this.month6.set(month6);
 }

 public StringProperty month6Property() {
 return month6;
 }

 public String getMonth8() {
 return month8.get();
 }

 public void setMonth8(String month8) {
 this.month8.set(month8);
 }

 public StringProperty month8Property() {
 return month8;
 }

 public String getMonth9() {
 return month9.get();
 }

 public void setMonth9(String month9) {
 this.month9.set(month9);
 }

 public StringProperty month9Property() {
 return month9;
 }

 public String getMonth10() {
 return month10.get();
 }

 public void setMonth10(String month10) {
 this.month10.set(month10);
 }

 public StringProperty month10Property() {
 return month10;
 }
 */
}
