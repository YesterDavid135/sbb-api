package frontend;

import javax.swing.*;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class MainWindow extends JFrame {

    public MainWindow() {
        this.setSize(400, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabpane = new JTabbedPane
                (JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT );

        JPanel stationBoard = new StationBoardPanel();
        tabpane.add("Stationboard", stationBoard);
        tabpane.add("Connections", new JPanel());

        this.add(tabpane);

        this.setVisible(true);

    }
}
