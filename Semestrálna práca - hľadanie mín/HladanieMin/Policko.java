/**
 * Trieda Policko je urcena pre hracie pole, ktore je 2-rozmerne a vytvorene z jednotlivych policok. 
 * Kazde policko ma inu hodnotu (moze nadobudat odhalenu, oznacenu alebo neodhalenu podobu).
 * Sluzi hlavne na zistovanie a zmenu stavu policka. Kľúčové slovo "extends" rozširuje triedu (označuje, že trieda je zdedená z inej triedy).
 * To znamená že táto trieda zdedila metódy z triedy HraciePole. Tento krok odovodnujem tym, ze pri volani metod z druhej triedy 
 * pomocou referencie program jednoducho spadol na nullpointer exception. Stavalo sa to hlavne pri volani metod oznac a odoberOznacenie.
 * Toto riešenie som našiel na internetovej stránke aj s vysvetlením: https://codegym.cc/groups/posts/java-extends-keyword-with-examples
 * 
 * @author Michal Pažitný
 * @version 4.12.2022
 */
public class Policko extends HraciePole {       
    private boolean odhalena;           //odhalena predstavuje odhalene policko v hracom poli.
    private boolean mina;               //mina predstavuje minove policko v hracom poli.
    private boolean oznacena;           //oznacena predstavuje oznacene policko v hracom poli.
    private int pocetOkolnychMin;       //uchovava informaciu o pocte min v okoli policka
    //private HraciePole hraciePole;    //objektovy typ HraciePole s premennou hraciePole (referencia na objekt HraciePole)
    private SuradnicePolicka suradnice; //objektovy typ SuradnicePolicka s premennou suradnice (referencia na objekt SuradnicePolicka)

    /**
     * Parametricky konstruktor triedy Policko sluzi na vytvorenie jedneho policka hracieho pola. Hodnoty atributov nie su vopred definovane a menia sa v zavislosti od metod a vypoctov.
     * Vytvori sa policko s hodnotami: ci je mina, odhalene alebo oznacene a so suradnicami.
     * - pocetRiadkov predstavuje kolko riadkov sa nachadza na hernom poli.
     * - pocetStlpcov predstavuje kolko stlpcov sa nachadza na hernom poli.
     * - pocetMin predstavuje, kolko min sa nachadza v hernom poli.
     */
    public Policko(int pocetRiadkov, int pocetStlpcov, int pocetMin, boolean odhalena, boolean mina, boolean oznacena, SuradnicePolicka suradnice) {
        /*
         * Klucove slovo super sa viaze na klucove slovo extends.
         * Super odkazuje na nadradené objekty. Používa sa na volanie metód nadtriedy (HraciePole) a na prístup ku konštruktoru nadtriedy.
         * K tomuto riešeniu som sa dostal pomocou internetovej stránky aj s vysvetlením: https://codegym.cc/groups/posts/super-keyword-in-java
         * Super musi byt ako prvy prikaz v konstruktore subtriedy.
         */
        super(pocetRiadkov, pocetStlpcov, pocetMin); //viaže sa na klucove slovo extends
        this.odhalena = odhalena;
        this.mina = mina;
        this.oznacena = oznacena;
        this.suradnice = suradnice;
        /*HraciePole hraciePole = new HraciePole(pocetRiadkov, pocetStlpcov, pocetMin);*/   //padá program pri volani metod tymto sposobom (nullpointer exception) - vyriesilo to zdedenie extends
    }

    /**
     * Vrati, ci je policko odhalene (hodnota true/false)
     */
    public boolean getOdhalena () {
        return this.odhalena;   //vrati hodnotu atributu odhalena
    }

    /**
     * Nastavi hodnotu atributu "odhalena" na pozadovanu hodnotu (true/false)
     */
    public void setOdhalena(boolean odpoved) {
        this.odhalena = odpoved;    //nastavi atributu odhalena hodnotu parametra odpoved
    }

    /**
     * Vrati, ci je policko mina (hodnota true/false)
     */
    public boolean getMina () {
        return this.mina;   //vrati hodnotu atributu mina
    }
    
    /**
     * Nastavi hodnotu atributu "mina" na pozadovanu hodnotu (true/false). To znamena ci je policko mina alebo nie.
     */
    public void setMina(boolean odpoved) {
        this.mina = odpoved;    //nastavi atributu mina hodnotu parametra odpoved
    }

    /**
     * Vrati, ci je policko oznacene (hodnota true/false)
     */
    public boolean getOznacena () {
        return this.oznacena;   //vrati hodnotu atributu oznacena
    }

    /**
     * Nastavi hodnotu atributu "oznacena" na pozadovanu hodnotu (true/false). To znamena ci je policko oznacene alebo nie.
     */
    public void setOznacena(boolean odpoved) {
        this.oznacena = odpoved;    //nastavi atributu oznacena hodnotu parametra odpoved
    }
    
    /**
     * Vrati aky je pocet min v okolitych polickach
     */
    public int getPocetOkolnychMin() {
        return this.pocetOkolnychMin;   //vrati hodnotu atributu pocetOkolnychMin
    }
    
    /**
     * Nastavi hodnotu atributu "pocetOkolnychMin" na pozadovanu hodnotu (integer pocetOkolnychMin). 
     */
    public void setPocetOkolnychMin(int pocetOkolnychMin) {
        this.pocetOkolnychMin = pocetOkolnychMin;   //nastavi atributu pocetOkolnychMin hodnotu parametra pocetOkolnychMin
    }

    /**
     * Oznaci policko pri splneni podmienky: policko nesmie byt odhalene, v opacnom pripade sa v na terminali vypise, ze policko uz bolo odhalene.
     */
    public void oznac(SuradnicePolicka suradnice) {
        if (!this.odhalena) {
            this.oznacena = true;                   //ak sa splnila podmienka, policko sa oznaci
            getMiestaOznacenia().add(suradnice);    //prida suradnice oznaceneho policka do arraylistu miestaOznacenia v metode getMiestaOznacenie z triedy HraciePole
                                                    //vdaka zdedeniu "extends" nepotrebujem pisat referenciu na druhu triedu 
        } else {
            System.out.println("Toto policko uz bolo odhalene");
        }
    }

    /**
     * Odoberie oznacenie (vlajocku) policka pri splneni podmienky: policko nesmie byt odhalene, v opacnom pripade sa v na terminali vypise, ze policko uz bolo odhalene.
     */
    public void odoberOznacenie (SuradnicePolicka suradnice) {
        if (!this.odhalena) {
            this.oznacena = false;                      //ak sa splnila podmienka, policku sa odoberie oznacenie
            getMiestaOznacenia().remove(suradnice);     //odstrani suradnice oznaceneho policka z arraylistu miestaOznacenia v metode getMiestaOznacenie z triedy HraciePole
                                                        //vdaka zdedeniu "extends" nepotrebujem pisat referenciu na druhu triedu
        } else {
            System.out.println("Toto policko uz bolo odhalene");
        }
    }

    /**
     * Hodnotu policka (neodhalene, oznacene, odhalene, mina) konvertuje na String a na terminali vypise nasledovne
     * Odhalena - bud 0 ak v okoli nie su ziadne miny, alebo sa zobrazi pocet min v okoli policka (1,2,3,4,...)
     * Mina - zobrazi sa ako znak *
     * Oznacena - zobrazi sa ako znak !
     * Neodhalena - zobrazi sa ako znak ?
     */
    public String toString() {
        String str;
        if (this.odhalena) {
            str = Integer.toString(this.pocetOkolnychMin);       //prekonvertuje integer na String
        } else {
            str = "?";
        }
        
        if (this.mina && this.odhalena) {
            str = "*";
        } else if (this.mina && !this.odhalena) {
            str = "?";
        }
        
        if (this.oznacena) {
            str = "!";
        }
        return str;     //vrati hodnotu lokalnej premennej str
    }
}