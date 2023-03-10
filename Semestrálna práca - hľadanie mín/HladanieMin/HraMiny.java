import java.util.Scanner;   //imporuje kniznicu pre vstup do konzole
/**
 * Trieda HraMiny obsahuje graficku cast hry a menezuje vstup do konzole/terminalu. 
 * Kontroluje ci prebieha hra; rozhoduje o tom, ci hrac vyhral/prehral; nastavuje
 * obtiaznosti lahka/stredna/narocna/vlastna. V skratke je to taky menezer celej hry.
 * 
 * @author Michal Pažitný 
 * @version 04.01.2023
 */
public class HraMiny {
    
    private static boolean prebiehaHra = true;                      //atribut triedy s pridelenou fixnou hodnotou true
    private static Scanner vstupHraca = new Scanner(System.in);     //inicializuje scanner pre vpisovanie do konzole
    private static SuradnicePolicka vyberPrvejPozicie ;              //atribut vyber prvej pozicie s objektovym typom SuradnicePolicka
    //private HraciePole tabulka;
    
    /**
     * Bezparametricky konstruktor triedy HraMiny, ktory hru vytvori a nahra do atributu vyberPrvejPozicie hodnotu prvej pozicie, aku si hrac vybral.
     * Podla toho sa dalej odvija aj spawnovanie min v triede HraciePole a tak dalej.
     */
    public HraMiny() {
        this.vyberPrvejPozicie = vyberPrvejPozicie;     //nahra do atributu vyber prvej pozicie
    }
    
    /**
     * Metoda triedy HraMiny zodpovedajuca za menezovanie vyberu obtiaznosti a spravneho generovania hracieho pola
     */
    public static HraciePole vyberObtiaznosti() {
        int obtiaznost;     //lokalna premenna obtiaznost
        boolean platnaObtiaznost = false;   //vychodzia hodnota platnejObtiaznosti je nastavena na false, lebo musi prejst niekolkymi podmienkami aby hra mohla pokracovat dalej
        HraciePole hraciePole = new HraciePole(0, 0, 0);  //vytvori sa hracie pole s 0 riadkami, 0 stlpcami, 0 minami
        int x;  //lokalna premenna x prestavujuca riadky pri vlastnej obtiaznosti, ktoru si hrac vytvara sam
        int y;  //lokalna premenna y prestavujuca stlpce pri vlastnej obtiaznosti, ktoru si hrac vytvara sam
        int kolko;  ////lokalna premenna kolko prestavujuca miny pri vlastnej obtiaznosti, ktoru si hrac vytvara sam

        //pokial nie je platna obtiaznost, stale prebieha loop na zistovanie obtiaznosti, aku si hrac praje
        while (!platnaObtiaznost) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("|Prosim vyber si svoju obtiaznost:                                 |");
            System.out.println("|1 - lahka (10x10 hracia doska s 15timi minami)                    |");
            System.out.println("|2 - stredna (16x16 hracia doska so 45timi minami)                 |");
            System.out.println("|3 - narocna (30x30 hracia doska so 120timi minami)                |");
            System.out.println("|4 - vlastna (lubovolna kombinacia min a STVORCOVEHO hracieho pola)|");
            System.out.println("--------------------------------------------------------------------");
            obtiaznost = Integer.parseInt(vstupHraca.nextLine());
                //ak hrac zada nieco ine ako je napisane, bude vyzvany aby tuto akciu opakoval a napisal vstup v spravnom formate
            if (obtiaznost != 1 && obtiaznost != 2 && obtiaznost != 3 && obtiaznost != 4) {
                System.out.println("----------------------------------------------------------------");
                System.out.println("|Prosim vyber si svoju obtiaznost napisanim cisla 1 / 2 / 3 / 4|");
                System.out.println("----------------------------------------------------------------");
            } else {
                /*
                * Ak hrac zada platne cislo zo zoznamu, hra sa spusti a vygeneruje prislusne hracie pole.
                */
                if (obtiaznost == 1) {
                    hraciePole = new HraciePole(10, 10, 15);    //vytvori sa hracie pole s 10 riadkami, 10 stlpcami, 15 minami 
                    platnaObtiaznost = true;                    //hodnota platnejObtiaznosti sa nastavi na true a hra pokracuje dalej
                } else if (obtiaznost == 2) {
                    hraciePole = new HraciePole(16, 16, 45);    //vytvori sa hracie pole s 16 riadkami, 16 stlpcami, 45 minami
                    platnaObtiaznost = true;                    //hodnota platnejObtiaznosti sa nastavi na true a hra pokracuje dalej
                } else if (obtiaznost == 3) {
                    hraciePole = new HraciePole(30, 30, 120);   //vytvori sa hracie pole s 30 riadkami, 30 stlpcami, 120 minami
                    platnaObtiaznost = true;                    //hodnota platnejObtiaznosti sa nastavi na true a hra pokracuje dalej
                } else {
                    System.out.println("----------------------------------------");
                    System.out.println("|Prosim zadaj kolko chces mat riadkov: |");
                    System.out.println("----------------------------------------");
                    x = Integer.parseInt(vstupHraca.nextLine());    //ulozi do premennej x pocet riadkov
                    System.out.println("----------------------------------------");
                    System.out.println("|Prosim zadaj kolko chces mat stlpcov: |");
                    System.out.println("----------------------------------------");
                    y = Integer.parseInt(vstupHraca.nextLine());    //ulozi do premennej y pocet stlpcov
                    System.out.println("--------------------------------------------------");
                    System.out.println("|Prosim zadaj kolko chces mat min v hracom poli: |");
                    System.out.println("--------------------------------------------------");
                    kolko = Integer.parseInt(vstupHraca.nextLine());    //ulozi do premennej kolko pocet min
                    hraciePole = new HraciePole(x, y, kolko);       //vytvori hracie pole na zaklade vstupu hraca
                    platnaObtiaznost = true;        //hodnota platnejObtiaznosti sa nastavi na true a hra pokracuje dalej
                }
            }
        }
        return hraciePole;  //vrati hracie pole
    }
    
    /**
     * Metoda triedy HraMiny nastavi prvy vyber policka hraca  
     */
    public static void setPrvyVyber(int riadok, int stlpec) {
        SuradnicePolicka suradnice = new SuradnicePolicka();    //vytvori referenciu na objekt SuradnicePolicka s nazvom suradnice
        suradnice.setRiadok(riadok);                            //suradniciam nastavi riadok
        suradnice.setStlpec(stlpec);                            //suradniciam nastavi stlpec
        vyberPrvejPozicie = suradnice;                          //do atributu vyberu prvej pozicie nahra pociatocne suradnice
    }

    /**
     * Metoda triedy HraMiny zapne novu hru min. Jednotlive useky kodu su presnejsie vysvetlene priamo pri alogritmoch a prikazoch.
     */
    public static void zapniHru() {
        HraciePole hraciePole = vyberObtiaznosti();     //pri prvom spusteni sa vola metoda vyber obtiaznosti, to je prve co clovek uvidi

        boolean platnyVyberPrvejPozicie  = false;   //vychodzia hodnota vyberu prvej pozicie je nastavena na false, lebo musi prejst niekolkymi podmienkami pre spravne spustenie
        System.out.println("\n--------------------------------------------------------------"); 
        System.out.println("|Napis vyber prvej pozicie policka od 0 do " + hraciePole.getPocetStlpcov() + " (napriklad 1-1)|");
        System.out.println("--------------------------------------------------------------");
        //Slucka (loop) bude prebiehat, pokial platny vyber pozicie nebude mat hodnotu true
        while (!platnyVyberPrvejPozicie ) {
            //Kontrola platnosti vyberu prvej pozicie
            String vyberPrvejPozicieString  = vstupHraca.nextLine();    //String do ktoreho sa uklada vstup hraca a prepne na dalsi riadok, kam moze hrac pisat
            /*ak sa vstup hraca nezhoduje s formatom ktory je ocakavany (napriklad 1-1) alebo enterne prazdny riadok, bude vyzvany aby tento krok zopakoval
             * podla Java Regular Expression (https://www.javatpoint.com/java-regex) - kontrola vstupu
             */
            if (!vyberPrvejPozicieString .matches("\\d{1,2}-\\d{1,2}") || vyberPrvejPozicieString .isEmpty()) {
                System.out.println("------------------------------------------------------------------");    
                System.out.println("|Zly vstup. Prosim napis vstup v spravnom formate (napriklad 1-1)|");
                System.out.println("------------------------------------------------------------------"); 
            } else {

                String[] suradnicePrvejPozicie  = vyberPrvejPozicieString .split("-"); //vstup sa splitne a program bude ignorovat pomlcku

                /*
                * Ak sa splni tato podmienka, cize hrac si zvoli miesto mimo hracej plochy, program hodi out of bounds exception.
                * Ak sa ale podmienka splni platny vyber prvej pozicie sa nastavi na true, nastavi sa prvy vyber, spawnu sa miny,
                * nastavi sa hracie pole a vyplnia sa prazdne policka kde nie su bomby nulami.
                */
                if (Integer.parseInt(suradnicePrvejPozicie [0]) < 0 || Integer.parseInt(suradnicePrvejPozicie [0]) >= hraciePole.getPocetRiadkov() ||
                    Integer.parseInt(suradnicePrvejPozicie [1]) < 0 || Integer.parseInt(suradnicePrvejPozicie [1]) >= hraciePole.getPocetStlpcov()) {
                    throw new ArrayIndexOutOfBoundsException();
                } else {
                    platnyVyberPrvejPozicie  = true;

                    setPrvyVyber(Integer.parseInt(suradnicePrvejPozicie [0]), Integer.parseInt(suradnicePrvejPozicie [1])); //nastavi sa prvy vyber hraca
                    hraciePole.spawnovanieMin(vyberPrvejPozicie );  //rozmiestnia sa miny
                    hraciePole.setHraciePole(); //nastavi sa hracie pole
                    hraciePole.vyplnPrazdnePolicka(vyberPrvejPozicie , hraciePole.getHraciePole()); //vyplnia sa prazdne policka nulami
                }
            }
        }

        /*
         * Vykresli sa v konzole/terminali navod na obsluhu hry
         * Hra sa ovlada tromi prikazmi :
         * Odhal - odhali policko na vybranej pozicii
         * Oznac - oznaci policko na vybranej pozicii
         * Odznac - odznaci policko na vybranej pozcii
         * Mal sa tu nachadzat aj exit, ale ta funkcia nefungovala spravne a program z nej padal aj bez jej zavolania
         */
        System.out.println("\n---------------------------------------------");
        System.out.println("Ako ovladat hru:");
        System.out.println("odhal 1-1\todhali policko na pozicii 1-1");
        System.out.println("oznac 1-1\toznaci policko na pozicii 1-1");
        System.out.println("odznac 1-1\todznaci policko na pozicii 1-1");
        
        /*
         * Zatial co uspesne hra prebieha sa na obrazovke vzdy vypise aktualny stav hry. Teda kolko min je v hracom poli a kolko oznaceni danemu hracovi ostava.
         * Potom hned vykresli hracie pole. Dalej vyzve hraca aby pouzil jeden z prikazov odhal/oznac/odznac. 
         */
        while (prebiehaHra) {
            System.out.println();
            System.out.println("Pocet min: " + hraciePole.getPocetMin());
            System.out.println("Zostavajuce oznacenia: " + (hraciePole.getPocetMin() - hraciePole.zratajPocetOznacenychPolicok(hraciePole.getHraciePole())));
            hraciePole.vykresliHraciePole(hraciePole.getHraciePole());  //vykresli hracie pole

            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.println("|Napis poziciu policka ktore chces odhal(iť) / oznac(iť) / odznac(iť) - (napriklad oznac 6-5)|");
            System.out.println("----------------------------------------------------------------------------------------------");

            String[] splitniVstup = new String[2];  //rozdeli vstup hraca do pola Stringov s parametrom 2 (dva objekty - napriklad: oznac 1-1)

            boolean platnyVstup = false; //lokalna premenna platnyvstup ma vychodziu hodnotu nastavenu na false a to preto, lebo musi prejst niekolkymi podmienkami pre uspesne pokracovanie
            //pokial platnyVstup ma hodnotu false, opat sa splitne vstup aby vedel program s informaciou pracovat
            while (!platnyVstup) {
                String vstupHracaStr = vstupHraca.nextLine();
                splitniVstup = vstupHracaStr.split(" ");
                    /*Opat sa kontroluje ci zadany prikaz od uzivatela dodrzuje format podla Java Regular Expression (https://www.javatpoint.com/java-regex) alebo len poslal prazdny vstup. 
                     *Ak teda zadal zly vstup, hra ho vyzve aby tuto akciu opakoval. Pokial hrac zada platny vstup, kotroluje sa
                     *dalej, ci to je v rozmedzi hernej plochy. Ak nie je hra hodi out of bounds exception, ak je, hodnota platneho vstupu
                     *sa zmeni na true.
                     */
                if (!vstupHracaStr.matches("(odhal|oznac|odznac)\\s\\d{1,2}-\\d{1,2}") || vstupHracaStr.isEmpty()) {
                    System.out.println("---------------------------------------------------------------------------------------------------------");
                    System.out.println("|Zly vstup - prosim napis poziciu policka ktore chces odhalit / oznacit / odznacit (napriklad oznac 3-6)|");
                    System.out.println("---------------------------------------------------------------------------------------------------------");
                } else { 
                    String[] suradnice = splitniVstup[1].split("-"); //splitne vstup na dve cisla a pomlcku odignoruje
                    int riadok = Integer.parseInt(suradnice[0]);    //do lokalnej premennej sa ulozi cislo na prvej(nultej v poli) pozicii
                    int stlpec = Integer.parseInt(suradnice[1]);    //do lokalnej premennej sa ulozi cislo na druhej(prvej v poli) pozicii

                    if (riadok < 0 || riadok >= hraciePole.getPocetRiadkov() || stlpec < 0 || stlpec >= hraciePole.getPocetStlpcov()) {
                        throw new ArrayIndexOutOfBoundsException();
                    } else {
                        platnyVstup = true; //zmeni hodnotu premennej na true
                    }
                }
            }

            String prikaz = splitniVstup[0];    //lokalnej premennej typu String sa priradi rozdelenie vstupu
            String[] suradky = splitniVstup[1].split("-");  //rozdeli vstup na dve cisla a bude ignorovat pomlcku
            int riadok = Integer.parseInt(suradky[0]);   //do lokalnej premennej riadok sa ulozi prve cislo zo vstupu
            int stlpec = Integer.parseInt(suradky[1]);  //do lokalnej premennej stlpec sa ulozi druhe cislo zo vstupu
            SuradnicePolicka pozicia = new SuradnicePolicka();      //vytvori sa nova referencia na objekt SuradnicePolicka
            pozicia.setRiadok(riadok);      //pozicii sa priradi riadok
            pozicia.setStlpec(stlpec);      //pozicii sa priradi stlpec
            //pomocou vetvenia switch case rozdelime jednotlive prikazy a ich funkcie
            switch (prikaz) {
                case "oznac":
                    hraciePole.getHraciePole()[riadok][stlpec].oznac(pozicia);      //oznaci na hracej ploche vybrane policko
                    break;
                case "odznac":
                    hraciePole.getHraciePole()[riadok][stlpec].odoberOznacenie (pozicia);   //odoberie oznacenie policku na hracej ploche
                    break;
                case "odhal":
                /*
                 * Ak po zadani prikazu odhal hrac vyberie nahodou policko s minou, hra sa skonci a zobrazi sa cele hracie pole s odhalenymi minami.
                 * Ak hrac "neslapne" na minu, hra pokracuje a odhali sa jedno, popripade viac policok ak v okoli sa nenachadza ziadna mina. Pokial sa
                 * hracovi podari vyriesit cele hracie pole, hra sa ukonci a vykresli sa hracie pole s odhalenymi minami. 
                 */    
                    if (hraciePole.getHraciePole()[riadok][stlpec].getMina ()) {
                        hraciePole.odhalVsetkyPolicka(hraciePole.getHraciePole());      //odhali vsetky policka na hracej ploche
                        hraciePole.vykresliHraciePole(hraciePole.getHraciePole());      //vykresli hracie pole
                        System.out.println("--------------------------------");
                        System.out.println("|Slapol si na minu! KONIEC HRY!|");
                        System.out.println("--------------------------------");
                        prebiehaHra = false;    //hra sa skonci
                        break;
                    } else {
                        hraciePole.vyplnPrazdnePolicka(pozicia, hraciePole.getHraciePole());       //vyplni rekurziou prazdne policka 
                        if (hraciePole.zratajPocetOdhalenychPolicok(hraciePole.getHraciePole()) == hraciePole.getPocetNezaminovanychPolicok()) {
                            hraciePole.odhalVsetkyPolicka(hraciePole.getHraciePole());  ////odhali vsetky policka na hracej ploche
                            System.out.println("\nHracia doska je odhalena:");
                            hraciePole.vykresliHraciePole(hraciePole.getHraciePole());  //vykresli hracie pole
                            System.out.println("------------");
                            System.out.println("|VYHRAL SI!|");
                            System.out.println("------------");
                            prebiehaHra = false;    //hra sa skonci
                            break;
                        }
                    }
            }
        }
    }
}
