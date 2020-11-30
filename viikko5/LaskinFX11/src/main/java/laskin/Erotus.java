
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {
    
    private int syote;
    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    private Button undo;

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.syote = 0;
        
    }
    
    @Override
    public void suorita() {
        try {
            this.syote = Integer.parseInt(syotekentta.getText());
        } catch (NumberFormatException e) {
        }
        
        sovellus.miinus(syote);
        asetaKenttienArvot();
    }

    @Override
    public void peru() {
        sovellus.plus(syote);
        asetaKenttienArvot();
    }
    
    private void asetaKenttienArvot() {
        int laskunTulos = sovellus.tulos();
        
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        
        if (laskunTulos==0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);
    }
}
