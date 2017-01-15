/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication9;

/**
 *
 * @author Jawes
 */
import javax.swing.JButton;

public class Field {

    JButton Button;
    Boolean state,pressed;

    Field(boolean s) {
        Button = new JButton();
        state = s;
        pressed=false;
    }
}
