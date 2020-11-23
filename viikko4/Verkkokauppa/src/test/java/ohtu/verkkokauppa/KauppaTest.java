package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);  
        // ...
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
//        Pankki pankki = mock(Pankki.class);

//        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

//        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
//        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanKunTuotteitaOnKaksiErilaista() {
        when(viite.uusi()).thenReturn(77);
        
        when(varasto.saldo(2)).thenReturn(100);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kauramaito", 3));
        
        when(varasto.saldo(3)).thenReturn(52);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "pulla", 2));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        
        k.tilimaksu("zahra", "85763");
        
        verify(pankki).tilisiirto(eq("zahra"), anyInt(), eq("85763"), anyString(), eq(5));
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanKunTuotteitaOnKaksiSamanlaista() {
        when(viite.uusi()).thenReturn(77);
        
        when(varasto.saldo(2)).thenReturn(100);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kauramaito", 3));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        
        k.tilimaksu("zahra", "85763");
        
        verify(pankki).tilisiirto(eq("zahra"), anyInt(), eq("85763"), anyString(), eq(6));
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanKunTuotteitaOnKaksijaToistaEiOleVarastossa() {
        when(viite.uusi()).thenReturn(77);
        
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kauramaito", 3));
        
        when(varasto.saldo(3)).thenReturn(52);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "pulla", 2));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(3);
        k.lisaaKoriin(2);
        
        k.tilimaksu("zahra", "85763");
        
        verify(pankki).tilisiirto(eq("zahra"), anyInt(), eq("85763"), anyString(), eq(2));
    }
    
}