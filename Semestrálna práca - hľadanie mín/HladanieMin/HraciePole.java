import java.util.ArrayList;     //importuje kniznicu pre pracu s ArrayListami
/**
 * Trieda HraciePole sluzi na vykreslovanie a ukladanie pozicii jednotlivych policok. 
 * HraciePole je zoskupenie policok do 2-rozmerneho pola poli. Na ukladanie pozicii sa
 * vyuzivaju ArrayListy z dovodu ich dynamickosti a lubovolneho zvacsovania a zmensovania
 * zoznamu, co je pri poli (array) nemozne - ma fixny pocet prvkov. Trieda dokaze graficky 
 * (textovo) v terminali vykreslit celu hraciu plochu. Okrem ineho ma za ulohu nahodne generovanie
 * min kazdy jeden krat, co sa program zapne. 
 * 
 * @author Michal Pažitný
 * @version 4.12.2022
 */
public class HraciePole {
    private int pocetRiadkov;                                                   //pocet riadkov v hracom poli
    private int pocetStlpcov;                                                   //pocet stlpcov v hracom poli
    private int pocetMin;                                                       //pocet min v hracom poli
    private int nahodneCislo;                                                   //nahodne cislo pre generovanie min v hracom poli
    private int pocetNezaminovanychPolicok;                                     //atribut ktory uchovava hodnotu, kolko policok je mozno odhalit bez toho aby boli miny
    private ArrayList<SuradnicePolicka> miestaOznacenia = new ArrayList<>();    //Inicializacia Arraylistu suradnic policok, ktore boli oznacene ako miny
    private ArrayList<SuradnicePolicka> miestaMin;                              //ArrayList suradnic policok, kde sa nachadzaju miny
    private Policko[][] hraciePole;                                             //2 rozmerne pole poli policok (objektovy typ Policko[][]), ktore vytvaraju hracie pole

    /**
     * Parametrický konštruktor s parametrami pocetStlpcov, pocetRiadkov a pocetMin vytvori novu hraciu plochu.
     * Kazdy parameter ma svoje opodstatnenie: pocetStlpcov uklada kolko by malo mat hracie pole stlpcov, to iste
     * je pocetRiadkov a aj pocetMin. V konstruktore sa dalej vola metoda, ktora nastavuje celkovy pocet policok,
     * ktore mozu byt odhalene bez toho aby boli miny.
     */
    public HraciePole(int pocetStlpcov, int pocetRiadkov, int pocetMin) {
        this.pocetStlpcov = pocetStlpcov;
        this.pocetRiadkov = pocetRiadkov;
        this.pocetMin = pocetMin;
        this.setPocetNezaminovanychPolicok((pocetStlpcov * pocetRiadkov) - pocetMin);
    }

    /**
     * Vrati, kolko riadkov ma hracie pole (datovy typ integer)
     */
    public int getPocetRiadkov() {
        return this.pocetRiadkov;    //vrati hodnotu poctu riadkov
    }
    
    /**
     * Nastavi pocet riadkov v hracom poli
     */
    public void setPocetRiadkov(int pocetRiadkov) {
        this.pocetRiadkov = pocetRiadkov;  //nastavi novu hodnotu poctu riadkov
    }

    /**
     * Vrati, kolko stlpcov ma hracie pole (datovy typ integer)
     */
    public int getPocetStlpcov() {
        return this.pocetStlpcov;    //vrati hodnotu poctu stlpcov
    }
    
    /**
     * Nastavi pocet stlpcov v hracom poli
     */
    public void setPocetStlpcov(int pocetStlpcov) {
        this.pocetStlpcov = pocetStlpcov;       //nastavi novu hodnotu poctu stlpcov
    }
    
    /**
     * Vrati, kolko min ma hracie pole (datovy typ integer)
     */
    public int getPocetMin() {
        return this.pocetMin;    //vrati hodnotu poctu min
    }
    
    /**
     * Nastavi pocet min v hracom poli
     */
    public void setPocetMin(int pocetMin) {
        this.pocetMin = pocetMin;       //nastavi novu hodnotu poctu min
    }

    /**
     * Vracia miesta policok, ktore boli oznacene z arraylistu
     */
    public ArrayList<SuradnicePolicka> getMiestaOznacenia() {
        return this.miestaOznacenia;     //vrati miesta oznacenia na hracom poli z arraylistu
    }

    /**
     * Nastavuje celkovy pocet policok, ktore treba v hracom poli odhalit ale, nesmu obsahovat minu.
     */
    public void setPocetNezaminovanychPolicok(int pocetNezaminovanychPolicok) {
        this.pocetNezaminovanychPolicok = pocetNezaminovanychPolicok;   //nastavuje pocet nezaminovanych policok
    }
    
    /**
     * Vracia celkovy pocet policok, ktore treba v hracom poli odhalit, ale nesmu obsahovat minu.
     */
    public int getPocetNezaminovanychPolicok() {
        return this.pocetNezaminovanychPolicok;      //vracia pocet nezaminovanych policok
    }
    
    /**
     * Vygeneruje nahodne cislo. Metoda sa vyuziva pri nahodnom spawnovani min. 
     */
    public int getNahodneCislo(int maximum) {
        this.nahodneCislo = (int)((Math.random() * (maximum)) + 0);  //explicitne pretypovanie double na int
        return this.nahodneCislo;        //vrati nahodne cislo (int)
    }

    /**
     *Spocita v dvojrozmernom hracom poli pocet vsetkych odhalenych policok a vrati ich pocet ako integer.
     *Je tu pouzity vnoreny cyklus for ne prechadzanie celej "tabulky" - cize riadkov a stlpcov.
     */
    public int zratajPocetOdhalenychPolicok(Policko[][] hraciePole) {
        int pocet = 0;      //hodnota pocet zacina na nule
        for (int i = 0; i < pocetRiadkov; i++) {
            for (int j = 0; j < pocetStlpcov; j++) {
                if (hraciePole[i][j].getOdhalena ()) {
                    pocet++;
                }
            }
        }
        return pocet;       //vrati pocet odhalenych policok
    }

    /**
     *Spocita v dvojrozmernom hracom poli pocet vsetkych oznacenych policok a vrati ich pocet ako integer.
     *Je tu pouzity vnoreny cyklus for ne prechadzanie celej "tabulky" - cize riadkov a stlpcov.
     */
    public int zratajPocetOznacenychPolicok(Policko[][] hraciePole) {
        int pocet = 0;      //hodnota pocet zacina na nule
        for (int i = 0; i < pocetRiadkov; i++) {
            for (int j = 0; j < pocetStlpcov; j++) {
                if (hraciePole[i][j].getOznacena ()) {
                    pocet++;
                }
            }
        }
        return pocet;        //vrati pocet oznacenych policok
    }

    /**
     * Vrati hracie pole zlozene z 2-rozmerneho pola poli policok
     */
    public Policko[][] getHraciePole() {
        return this.hraciePole;     //vrati herne pole
    }

    /**
     * Nastavi hracie pole na pociatocne hodnoty, s tym, ze nahodne umiestni miny po celej hracej ploche
     */
    public void setHraciePole() {
        this.hraciePole = new Policko[this.pocetStlpcov][this.pocetRiadkov];     //vytvori pole s parametrami kolko stlpcov a kolko riadkov

        //vnoreny cyklus for vyplni pole nulami - teda polickami, ktore nemaju okolo seba ziadnu minu
        for (int i = 0; i < pocetStlpcov; i++) {
            for (int j = 0; j < pocetRiadkov; j++) {
                SuradnicePolicka pozicia = new SuradnicePolicka();      //vytvori poziciu pre policko
                pozicia.setRiadok(i);   //policku nastavi riadok
                pozicia.setStlpec(j);   //policku nastavi stlpec

                Policko policko = new Policko(this.pocetRiadkov, this.pocetStlpcov, this.pocetMin, false, false, false, pozicia);   //vsetky booleany sa nastavia na false, lebo nie su este rozmiestnene miny

                this.hraciePole[i][j] = policko;
            }
        }

        //cyklus for each rozmiestni po poli miny
        for (SuradnicePolicka suradnice : this.miestaMin) {
            Policko policko = new Policko(this.pocetRiadkov, this.pocetStlpcov, this.pocetMin, false, true, false, suradnice);  //vytvori policko a zaroven nastavi jeho boolean hodnotu miny na true
            this.hraciePole[suradnice.getRiadok()][suradnice.getStlpec()] = policko;     //policku prideli jeho suradnice
        }

        //vnoreny cyklus for spocita susedne miny okolo policka
        for (int i = 0; i < pocetStlpcov; i++) {
            for (int j = 0; j < pocetRiadkov; j++) {
                this.hraciePole[i][j].setPocetOkolnychMin(this.zratajOkolneMiny(i, j, this.hraciePole));   //spocita dohromady vsetky miny najdene v hracom poli
            }
        }
    }

    /**
     * Vygeneruje miny na nahodnej pozicii v hracom poli. Dava pozor na to, ci nie su na jednom policku dve a viac min.
     * Kontroluje prvy vyber hraca, aby sa nestalo, ze hned na zaciatku by si vybral bombu.
     */
    public void spawnovanieMin(SuradnicePolicka prvaPozicia) {
        this.miestaMin = new ArrayList<>();
        //slucka (loop) spawnuje miny, pokial velkost arraylistu nedosiahne potrebny pocet min
        while (this.miestaMin.size() < this.pocetMin) {
            SuradnicePolicka pozicia = new SuradnicePolicka();
            int riadok = this.getNahodneCislo(this.pocetStlpcov); //generuje nahodny riadok v hracom poli
            int stlpec = this.getNahodneCislo(this.pocetRiadkov); //generuje nahodny stlpec v hracom poli
            pozicia.setRiadok(riadok);  //tuto poziciu pripise prislusnemu riadku 
            pozicia.setStlpec(stlpec);  //tuto poziciu pripise prislusnemu stlpcu

            // get firstLocation surrounding locations
            ArrayList<SuradnicePolicka> prvaSusednaPozicia = this.getPozicieSusednychPolicok (prvaPozicia);

            /* 
             * Ak arraylist miest min neobsahuje poziciu zaminovaneho policka
             * a zaroven ak prva susedna pozicia neobsahuje poziciu zaminovaneho
             * policka, pozicia sa prida do zoznamu umiestnenia min
             */
            if (!this.miestaMin.contains(pozicia) && !prvaSusednaPozicia.contains(pozicia)) {
                this.miestaMin.add(pozicia);
            }
        }
    }

    /**
     * Vracia suradnice susednych policok pomocou vnoreneho cyklu for a arraylistu
     */
    public ArrayList<SuradnicePolicka> getPozicieSusednychPolicok (SuradnicePolicka suradnice) {
        ArrayList<SuradnicePolicka> susednePozicie = new ArrayList<>();     //vytvori zoznam susednych pozicii

        for (int i = suradnice.getRiadok() - 1; i <= suradnice.getRiadok() + 1; i++) {
            for (int j = suradnice.getStlpec() - 1; j <= suradnice.getStlpec() + 1; j++) {
                SuradnicePolicka susednaPozicia = new SuradnicePolicka();
                susednaPozicia.setRiadok(i);    //urci jej riadkovu suradnicu (poziciu)
                susednaPozicia.setStlpec(j);    //urci jej stlpcovu suradnicu (poziciu)
                susednePozicie.add(susednaPozicia); //vlozi susednu poziciu policka do zoznamu
            }
        }

        return susednePozicie;  //vrati zoznam susednych pozicii
    }

    /**
     * Metoda zrata vsetky miny nachadzajuce sa v okoli policka pomocou vnoreneho cyklu for pre kazdy riadok a stlpec.
     * Je to podobny postup ako pri zistovani pozicie susednych policok.
     */
    public int zratajOkolneMiny(int riadok, int stlpec, Policko[][] hraciePole) {
        int miny = 0;   //lokalna premenna miny zacina od 0
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                //kontrola platnosti, ci nie je pozicia mimo hracej plochy
                if (i + riadok >= 0 && i + riadok < pocetStlpcov && j + stlpec >= 0 && j + stlpec < pocetRiadkov) { 
                    //ak sa na danej pozicii nachadza mina, prirata ju k lokalnej premennej miny
                    if (hraciePole[i + riadok][j + stlpec].getMina ()) {
                        miny++;
                    }
                }
            }
        }
        return miny;    //vracia celkovy pocet min naokolo policka
    }
    
    /**
     * Zobrazi vyriesene hracie pole s odhalenymi minami. 
     * Vyuziva sa pri vyhre alebo prehre. Vyuziva vnoreny
     * cyklus for. 
     */
    public void odhalVsetkyPolicka(Policko[][] hraciePole) {
        for (int i = 0; i < pocetStlpcov; i++) {
            for (int j = 0; j < pocetRiadkov; j++) {
                //ak po prehre/vyhre sa najde neobjavena mina, jej oznacenie sa zrusi a odhali sa
                if (hraciePole[i][j].getMina ()) {
                    hraciePole[i][j].setOznacena(false);
                }
                hraciePole[i][j].setOdhalena(true);
            }
        }
    }
    
    /**
     * Vykresli hernu plochu v okne terminalu/konzole
     */
    public void vykresliHraciePole(Policko[][] hraciePole) {
        System.out.print("\t"); //hodi tzv tabulatorovu medzeru
        
        for (int i = 0; i < pocetStlpcov; i++) {
            System.out.print(i + "\t");     //vykresli stlpcove oznacenia 
        }
        System.out.print("    ");
        System.out.print("\n");
        
        for (int i = 0; i < pocetStlpcov; i++) {
            if (i >= 10) {
                System.out.print(i + "|");  //vykresli pre riadkove oznacenia (jednociferne cislo) kolmu ciaru pre estetickost ( 6| )
            } else {
                System.out.print(i + "|");  //vykresli pre riadkove oznacenia (dvojciferne cislo) kolmu ciaru pre estetickost ( 10| )
            }
            for (int j = 0; j < pocetRiadkov; j++) {
                System.out.print("\t" + "[" + hraciePole[i][j] + "]");  //hodi tabulatorovu medzeru a do hranatych zatvoriek vypise obsah policka ( [0] )
            }
            System.out.print(" \n");
        }
        System.out.print("   ");
        System.out.println(); //vykresli hracie pole
    }

    /**
     * Rekurzivna metoda, ktora po odhaleni policka s nulou (ziadnou minou v okoli) odhali aj ostatne policka v okoli, s tym ze dostane sa len potial, kde sa nachadza mina.
     * Mina sa neodhali a posledne policko, ktore sa odhali bude zobrazovat informaciu o pocte min v okoli policka, tak ako to je aj v originalnych minach. To znamena ze na hracom poli 
     * bude mozne vidiet oblast napriklad aj s 5timi 0mi a 3mi ciselnymi informaciami o pocte min v okoli.
     */
    public void vyplnPrazdnePolicka(SuradnicePolicka suradnice, Policko[][] hraciePole) {
        if (!hraciePole[suradnice.getRiadok()][suradnice.getStlpec()].getOdhalena ()) {
            int pocetOkolnychMin = this.zratajOkolneMiny(suradnice.getRiadok(), suradnice.getStlpec(), hraciePole);

            if (pocetOkolnychMin > 0) {
                hraciePole[suradnice.getRiadok()][suradnice.getStlpec()].setOdhalena(true); //po kontrole podmienky sa urcite policko odhali
            } else {
                hraciePole[suradnice.getRiadok()][suradnice.getStlpec()].setOdhalena(true); //po kontrole podmienky sa urcite policko odhali
                //Kontrola vrchu hracieho pola
                if (suradnice.getRiadok() > 0) { 
                    SuradnicePolicka pozicia1 = new SuradnicePolicka(); //vytvori novu poziciu
                    pozicia1.setRiadok(suradnice.getRiadok() - 1);        //nastavi sa pozicii jej riadok
                    pozicia1.setStlpec(suradnice.getStlpec());          //nastavi sa pozicii jej stlpec
                    this.vyplnPrazdnePolicka(pozicia1, hraciePole);          //zavola sa metoda na vyplnenie prazdnych policok na urcitej pozicii v hracom poli
                }
                //Kontrola spodku hracieho pola
                if (suradnice.getRiadok() < this.pocetRiadkov - 1) { 
                    SuradnicePolicka pozicia2 = new SuradnicePolicka();
                    pozicia2.setRiadok(suradnice.getRiadok() + 1);
                    pozicia2.setStlpec(suradnice.getStlpec());
                    this.vyplnPrazdnePolicka(pozicia2, hraciePole);
                }
                //Kontrola lavej casti hracieho pola
                if (suradnice.getStlpec() > 0) { 
                    SuradnicePolicka pozicia3 = new SuradnicePolicka();
                    pozicia3.setRiadok(suradnice.getRiadok());
                    pozicia3.setStlpec(suradnice.getStlpec() - 1);
                    this.vyplnPrazdnePolicka(pozicia3, hraciePole);
                }
                //Kontrola pravej casti hracieho pola
                if (suradnice.getStlpec() < this.pocetStlpcov - 1) { 
                    SuradnicePolicka pozicia4 = new SuradnicePolicka();
                    pozicia4.setRiadok(suradnice.getRiadok());
                    pozicia4.setStlpec(suradnice.getStlpec() + 1);
                    this.vyplnPrazdnePolicka(pozicia4, hraciePole);
                }
                //Kontrola lavej hornej casti hracieho pola
                if (suradnice.getStlpec() > 0 && suradnice.getRiadok() > 0) { 
                    SuradnicePolicka pozicia5 = new SuradnicePolicka();
                    pozicia5.setRiadok(suradnice.getRiadok() - 1);
                    pozicia5.setStlpec(suradnice.getStlpec() - 1);
                    this.vyplnPrazdnePolicka(pozicia5, hraciePole);
                }
                //Kontrola pravej hornej casti hracieho pola
                if (suradnice.getStlpec() < this.pocetStlpcov - 1 && suradnice.getRiadok() > 0) { 
                    SuradnicePolicka pozicia6 = new SuradnicePolicka();
                    pozicia6.setRiadok(suradnice.getRiadok() - 1);
                    pozicia6.setStlpec(suradnice.getStlpec() + 1);
                    this.vyplnPrazdnePolicka(pozicia6, hraciePole);
                }
                //Kontrola spodnej lavej casti hracieho pola
                if (suradnice.getStlpec() > 0 && suradnice.getRiadok() < this.pocetRiadkov - 1) { 
                    SuradnicePolicka pozicia7 = new SuradnicePolicka();
                    pozicia7.setRiadok(suradnice.getRiadok() + 1);
                    pozicia7.setStlpec(suradnice.getStlpec() - 1);
                    this.vyplnPrazdnePolicka(pozicia7, hraciePole);
                }
                //Kontrola pravej spodnej casti hracieho pola
                if (suradnice.getStlpec() < this.pocetStlpcov - 1 && suradnice.getRiadok() < this.pocetRiadkov - 1) { 
                    SuradnicePolicka pozicia8 = new SuradnicePolicka();
                    pozicia8.setRiadok(suradnice.getRiadok() + 1);
                    pozicia8.setStlpec(suradnice.getStlpec() + 1);
                    this.vyplnPrazdnePolicka(pozicia8, hraciePole);
                }
            }
        }
    }
} 


