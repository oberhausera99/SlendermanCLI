
package Palya;
import palyaelem.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Ez az osztály felelős a pályával kapcsolatos műveletekért. Adattagjai: tereptagyak, eredeti, papirok. Ezek
 * 2 dimenzós tömbök amik Stringeket tartalmaznak (a tereptárgyak szimbólumait elhelyezkedésük szerint). Maga
 * a játéktér 15x15 méretű, de itt 17x17-es tömböket hoztam létre, mert a pályának egy *-szimbólumokból álló
 * szegélyt is létrehoztam. (Ennek csak esztétikai szerepe van, hogy lehessen látni a pályahatárt). A tereptárgyak
 * tömb elemei keerülnek folyamatosan frissítésre és kiiratásra. Ezekről a Jatekos és Slenderman oszályokban bővebben
 * Az 'elhelyezett' egy lista a pályán szereplő tereptárgy objektumokról. A papírok kiosztásánál is használatos
 *
 */

public class Palya {

    public String[][] tereptargyak;
    public final String[][] eredeti;
    public String[][] papirok;
    public List<Tereptargy> elhelyezett;

    /**
     *
     * @throws Exception
     * A pálya konstruktora egy előre megírt txt-fájlt vár, ami a pályaelemek elrendezését írja le szimbólumokkal
     */
    public Palya() throws Exception {
        Scanner sc = new Scanner(new File("map.txt"));
        tereptargyak = new String[17][17];
        eredeti = new String[17][17];
        papirok = new String[17][17];
        while (sc.hasNextLine()) {
            for (int i = 0; i < tereptargyak.length; i++) {
                String[] line = sc.nextLine().trim().split(",");
                for (int j = 0; j < line.length; j++) {
                    this.tereptargyak[i][j] = line[j];
                    this.eredeti[i][j] = line[j];
                    this.papirok[i][j] = line[j];
                }
            }
        }

        this.elhelyezett = new ArrayList<>();
    }

    public String[][] getTereptargyak() {
        return tereptargyak;
    }

    public void setTereptargyak(String[][] param) {
        this.tereptargyak = param;
    }

    /**
     * A pálya kiiratása. Ez a Main osztályban zajik
     * @return a pályaelemek szimbólumainak kiiratása
     */
    public String toString() {
        String kiiratas = "";
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 17; j++) {
                kiiratas += this.tereptargyak[i][j];
            }
            kiiratas += "\n";
        }

        return kiiratas;
    }


    /**
     * Segédfunkció, a main metódusban ki van kommentezve. Azokat a tereptárgy objektumokat listázza ki, amik tartalmaznak
     * papírt.
     */
    public void kiiratas() {
        for(int i = 0; i < this.elhelyezett.size()-1; i++) {
            System.out.print(this.elhelyezett.get(i) + " ");
        }
        System.out.println("\n");
    }


    /**
     * Végigiterál a pálya elemein, és a szimbólumok elrendezése szerint feltölti az 'elhelyezett' listát. Ehhez a
     * tereptárgy osztály gyerekeinek add metódusát hívja meg. Erről részletesebben a tereptárgyak osztályban és
     * gyerekosztályaiban
     * @param palya az aktuális pálya
     */
    public void tereptargyatListaz(Palya palya) {
        Haz.add(palya);
        Szikla.add(palya);
        Fa.add(palya);
        Auto.add(palya);
        Hordo.add(palya);
        Teherauto.add(palya);
        this.elhelyezett.add(new Tereptargy("ZaroObjektum", 0,0,0,0));
    }


    /**
     * A papírok elrejtése az 'elhelyezett' lista elemein. Véletlenszerűen eltávolít elemeket, amíg végül 8 elemet
     * tartalmaz csak a lista. Ezeken lesznek elrejtve papírok
     */
    public void papirtKioszt() {
        while(this.elhelyezett.size() > 9) {
            int random = (int)(Math.random() * (this.elhelyezett.size()-1) + 0);
            this.elhelyezett.remove(random);
        }
         /* for(int i = 0; i < this.elhelyezett.size(); i++) {
            System.out.println(elhelyezett.get(i));
        } */
    }




}






