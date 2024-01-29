package ba.fsre.bakeryapp;

public class Sastojci {
    private double brasno;
    private double voda;
    private double sol;
    private double kvasac;
    private double aditiv;
    private double ulje;
    private double secer;
    private double dodatnasmjesa;

    public Sastojci() {
    }

    public Sastojci(double brasno, double voda, double sol) {
        this.brasno = brasno;
        this.voda = voda;
        this.sol = sol;
    }

    public Sastojci(double brasno, int voda, int sol) {
        this.brasno = brasno;
        this.voda = voda;
        this.sol = sol;
    }

    public Sastojci(double brasno, double voda, double sol, double kvasac, double aditiv) {
        this.brasno = brasno;
        this.voda = voda;
        this.sol = sol;
        this.kvasac = kvasac;
        this.aditiv = aditiv;
    }

    public Sastojci(double brasno, int voda, int sol, int kvasac, int aditiv) {
        this.brasno = brasno;
        this.voda = voda;
        this.sol = sol;
        this.kvasac = kvasac;
        this.aditiv = aditiv;
    }

    public Sastojci(double brasno, double voda, double sol, double kvasac, double aditiv, double ulje, double secer) {
        this.brasno = brasno;
        this.voda = voda;
        this.sol = sol;
        this.kvasac = kvasac;
        this.aditiv = aditiv;
        this.ulje = ulje;
        this.secer = secer;
    }

    public Sastojci(double brasno, double voda, double sol, double kvasac, double aditiv, double ulje, double secer, double dodatnasmjesa) {
        this.brasno = brasno;
        this.voda = voda;
        this.sol = sol;
        this.kvasac = kvasac;
        this.aditiv = aditiv;
        this.ulje = ulje;
        this.secer = secer;
        this.dodatnasmjesa = dodatnasmjesa;
    }

    public Sastojci(double brasno, int voda, int sol, int kvasac, int aditiv, int ulje, int dodatnasmjesa) {
        this.brasno = brasno;
        this.voda = voda;
        this.sol = sol;
        this.kvasac = kvasac;
        this.aditiv = aditiv;
        this.ulje = ulje;
        this.dodatnasmjesa = dodatnasmjesa;
    }

    public String sKruha(double rezultat){
        this.brasno=rezultat*0.60;
        this.voda=rezultat*0.365;
        this.sol=rezultat*0.01219;
        this.kvasac=rezultat*0.0091463;
        this.aditiv=rezultat*0.0012195;
        return null;
    }

    public String sPeciva(double rezultat){
        this.brasno=rezultat*0.602409639;
        this.voda=rezultat*0.313253012;
        this.sol=rezultat*0.012048193;
        this.kvasac=rezultat*0.009036145;
        this.aditiv=rezultat*0.001204819;
        this.ulje=rezultat*0.036144578;
        this.secer=rezultat*0.024096386;
        return null;
    }

    public double getBrasno() {
        String formattedValue = String.format("%.2f", brasno);
        return Double.valueOf(formattedValue);
    }

    public void setBrasno(double brasno) {
        this.brasno = brasno;
    }

    public double getVoda() {
        String formattedValue = String.format("%.2f", voda);
        return Double.valueOf(formattedValue);
    }

    public void setVoda(int voda) {
        this.voda = voda;
    }

    public double getSol() {
        String formattedValue = String.format("%.2f", sol);
        return Double.valueOf(formattedValue);
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public double getKvasac() {
        String formattedValue = String.format("%.2f", kvasac);
        return Double.valueOf(formattedValue);
    }

    public void setKvasac(int kvasac) {
        this.kvasac = kvasac;
    }

    public double getAditiv() {
        String formattedValue = String.format("%.2f", aditiv);
        return Double.valueOf(formattedValue);
    }

    public void setAditiv(int aditiv) {
        this.aditiv = aditiv;
    }

    public double getUlje() {
        String formattedValue = String.format("%.2f", ulje);
        return Double.valueOf(formattedValue);
    }

    public void setUlje(int ulje) {
        this.ulje = ulje;
    }

    public double getDodatnasmjesa() {
        String formattedValue = String.format("%.2f", dodatnasmjesa);
        return Double.valueOf(formattedValue);
    }

    public void setDodatnasmjesa(int dodatnasmjesa) {
        this.dodatnasmjesa = dodatnasmjesa;
    }

    public double getSecer() {
        String formattedValue = String.format("%.2f", secer);
        return Double.valueOf(formattedValue);
    }

    public void setSecer(double secer) {
        this.secer = secer;
    }
}
