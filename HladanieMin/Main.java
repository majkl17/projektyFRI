/**
 * Trieda Main obsahuje main metodu na spustanie hry v blueJ ale aj mimo nej.
 * 
 * @author Michal Pažitný
 * @version 07.01.2023
 */
public class Main {

    private HraMiny hra;   //atribut hra s objekotvym typom HraMiny
    
    /**
     * Main metoda s parametrom pole Stringov vytvara novu instanciu hry a spusta ju. 
     */
    public static void main(String[] parametre) {
        System.out.println("-------------------");
        System.out.println("|Hra hladanie min |");
        System.out.println("|By Michal Pazitny|");
        System.out.println("--------------------\n");
        HraMiny hra = new HraMiny();        //referencia na objekt HraMiny
        hra.zapniHru();                     //volanie metody z triedy HraMiny, ktora spusta hru

    }
    
    /**
     * Metoda triedy Main sluzi na vypinanie hry
     */
    public static void vypnutieHry() {
        System.exit(0);
    }
}
