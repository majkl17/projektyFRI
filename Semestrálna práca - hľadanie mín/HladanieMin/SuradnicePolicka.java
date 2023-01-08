/**
 * Trieda SuradnicePolicka sluzi na pridelenie suradnic polickam a pracu s nimi v ramci hracieho pola. 
 * Jej metody sa vyuzivaju hlavne v ArrayListoch v triede HraciePole.
 * 
 * @author Michal Pažitný
 * @version 4.12.2022
 */
public class SuradnicePolicka {
    private int riadok;     //suradnica riadku
    private int stlpec;     //suradnica stlpca

    /**
     * Bezparametricky konstruktor triedy SuradnicePolicka, ktory vytvara/prideluje suradnice jednotlivym polickam na hracom poli. 
     */
    public SuradnicePolicka() {
        this.riadok = riadok;   //hodnota sa prideluje v metodach v triede HraciePole napr pri generovani nahodneho cisla
        this.stlpec = stlpec;   //hodnota sa prideluje v metodach v triede HraciePole napr pri generovani nahodneho cisla
    }

    /**
     * Vracia suradnicu policka (na ktorom riadku sa nachadza)
     */
    public int getRiadok() {
        return this.riadok;      //vrati hodnotu atributu riadok (integer)
    }

    /**
     * Nastavuje suradnicu riadka policka v hracom poli
     */
    public void setRiadok(int riadok) {
        this.riadok = riadok;   //nahra atributu riadok novu hodnotu z parametra riadok
    }

    /**
     * Vracia suradnicu policka (na ktorom riadku sa nachadza)
     */
    public int getStlpec() {
        return this.stlpec;       //vrati hodnotu atributu stlpec (integer)
    }

    /**
     * Nastavuje suradnicu stlpca policka v hracom poli
     */
    public void setStlpec(int stlpec) {
        this.stlpec = stlpec;      //nahra atributu stlpec novu hodnotu z parametra stlpec
    }

    /**
     * Vypise suradnice policka (riadok a stlpec) vo forme pola. Cize napriklad policko sa nachadza na suradniciach [6, 2].
     */
    public String toString() {
        return "[" + riadok + ", " + stlpec + "]";  //v stringu vypise [riadok, stlpec] (v cislach)
    }
}