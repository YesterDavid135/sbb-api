package frontend;

import javax.swing.*;
import java.awt.*;

public class StationBoardPanel extends JPanel {

    JTextField loc = new JTextField();
    JButton search = new JButton("Search");
    public StationBoardPanel() {
        this.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new GridLayout(1,2));
        northPanel.add(loc);
        northPanel.add(search);

        add(northPanel, BorderLayout.NORTH);



    }
}
