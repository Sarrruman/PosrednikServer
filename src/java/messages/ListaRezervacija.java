/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import beans.Rezervacija;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author malenicn
 */
public class ListaRezervacija implements Serializable {

    static final long serialVersionUID = 42L;
    private List<Rezervacija> rezervacije;

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacija) {
        this.rezervacije = rezervacija;
    }

}
