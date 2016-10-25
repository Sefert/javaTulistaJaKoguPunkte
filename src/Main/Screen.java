package Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sefert on 2.10.2016.
 * Koodjupp tekitab raami sisu.
 *  JPanel is a Swing's lightweight container which is used to group a set of components together.
 *  JPanel is a pretty simple component which, normally, does not have a GUI (except when it is being set an opaque background or has a visual border).
 *  http://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples
 *  JPanel - Generic class used to gather other elements together.
 *  This is more important with working with the visual layout or one of the provided layout managers e.g. gridbaglayout, etc.
 *  For example, you have a textbox that is bigger then the area you have reserved.
 *  Put the textbox in a scrolling pane and put that pane into a JPanel.
 *  Then when you place the JPanel, it will be more manageable in terms of layout.
 */
public class Screen extends JPanel {
    public void paint (Graphics g){
        super.paint(g);
        Graphics2D g2d=(Graphics2D) g;
    }

}
