package Main;

import javax.swing.*;

/**
 * Created by Sefert on 2.10.2016.
 * Koodijupp tekitab raami.
 * JFrame - Used to represent the stuff a window should have.
 * This includes borders (resizeable y/n?), titlebar (App name or other message),
 * controls (minimize/maximize allowed?),
 * and event handlers for various system events like 'window close' (permit app to exit yet?).
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
