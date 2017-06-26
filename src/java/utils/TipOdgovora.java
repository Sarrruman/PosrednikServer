/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author malenicn
 */
public enum TipOdgovora {

    LOGIN, IZMENA_KOR_POD, REZERVACIJA;

    public static TipOdgovora fromInteger(int x) {
        switch (x) {
            case 0:
                return LOGIN;
            case 1:
                return IZMENA_KOR_POD;
            case 2:
                return REZERVACIJA;
        }
        return null;
    }

}
