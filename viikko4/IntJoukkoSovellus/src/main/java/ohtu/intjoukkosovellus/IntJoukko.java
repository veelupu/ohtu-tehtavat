
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] alkiot;      // Joukon alkiot säilytetään taulukon alkupäässä. 
    private int koko;    // Tyhjässä joukossa alkioiden määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain
        }
        alkiot = new int[kapasiteetti];
        for (int i = 0; i < alkiot.length; i++) {
            alkiot[i] = 0;
        }
        koko = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!onkoJoukossa(luku)) {
            alkiot[koko] = luku;
            koko++;
            if (koko % alkiot.length == 0) {
                taulukonKasvatus();
            }
            return true;
        }
        return false;
    }
    
    private void taulukonKasvatus() {
        int[] vanhaTaulukko = new int[alkiot.length];
        vanhaTaulukko = alkiot;
        kopioiTaulukko(alkiot, vanhaTaulukko);
        alkiot = new int[koko + kasvatuskoko];
        kopioiTaulukko(vanhaTaulukko, alkiot);
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public boolean onkoJoukossa(int luku) {
        for (int i = 0; i < koko; i++) {
            if (luku == alkiot[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int indeksi = -1;
        for (int i = 0; i < koko; i++) {
            if (luku == alkiot[i]) {
                indeksi = i; //siis luku löytyy tuosta kohdasta
                alkiot[i] = 0;
                break;
            }
        }
        if (indeksi != -1) {
            for (int j = indeksi; j < koko - 1; j++) {
                alkiot[j] = alkiot[j + 1];
            }
            koko--;
            return true;
        }
        return false;
    }

    public int getKoko() {
        return koko;
    }

    @Override
    public String toString() {
        if (koko == 0) {
            return "{}";
        } else if (koko == 1) {
            return "{" + alkiot[0] + "}";
        } else {
            String s = "{";
            for (int i = 0; i < koko - 1; i++) {
                s += alkiot[i];
                s += ", ";
            }
            s += alkiot[koko - 1];
            s += "}";
            return s;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[koko];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = alkiot[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkaus;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(bTaulu[i]);
        } 
        return erotus;
    }
}