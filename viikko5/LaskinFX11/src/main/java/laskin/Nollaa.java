
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    
    private int tulos;
    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    private Button undo;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
        this.tulos = 0;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        
    }
    
    @Override
    public void suorita() {
        try {
            this.tulos = Integer.parseInt(tuloskentta.getText());
        } catch (NumberFormatException e) {
        }
        
        sovellus.nollaa();
        asetaKenttienArvot();
    }

    @Override
    public void peru() {
        sovellus.plus(tulos);
        asetaKenttienArvot();
    }
    
    private void asetaKenttienArvot() {
        syotekentta.setText("");
        tuloskentta.setText("" + 0);
        nollaa.disableProperty().set(true);
        
        undo.disableProperty().set(false);
    }
}
