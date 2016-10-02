package Main;

import javax.swing.*;

/**
 * Created by Sefert on 2.10.2016.
 * Koodijupp tekitab raami.
 */
public class Frame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("SpaceGeometry");//raami tekitamise konstruktor
        frame.setSize(800, 700);//määrab akna suuruse
        frame.setResizable(false);//ei võimalda töö ajal akna suurust muuta
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//akna sulgemisel suletakse ka protsess
        frame.setFocusable(true); //võimaldab saada kasutaja inputi
        frame.setLocationRelativeTo(null);//tsentreerib akna ekraanil
        frame.setVisible(true);//ekraani näitamiseks
    }
}
