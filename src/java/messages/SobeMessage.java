/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import beans.Soba;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author malenicn
 */
public class SobeMessage implements Serializable {

    static final long serialVersionUID = 42L;

    private List<Soba> sobe;

    public List<Soba> getSobe() {
        return sobe;
    }

    public void setSobe(List<Soba> sobe) {
        this.sobe = sobe;
    }

}
