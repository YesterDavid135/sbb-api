package frontend;

import dto.JourneyDTO;
import logic.StationBoardManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

public class StationBoardPanel extends JPanel implements ActionListener {

    StationBoardManager manager = new StationBoardManager();
    JTextField loc = new JTextField();
    JButton search = new JButton("Search");

    ArrayList<JourneyDTO> stationboard = new ArrayList<>();
    JTable table = new JTable();
    private final DefaultTableModel model = new DefaultTableModel(new Object[]{"To","Platform", "Departure", "Train", "id"}, 0);


    public StationBoardPanel() {
        this.setLayout(new BorderLayout());

        search.addActionListener(this);

        JPanel northPanel = new JPanel(new GridLayout(1,2));
        northPanel.add(loc);
        northPanel.add(search);

        add(northPanel, BorderLayout.NORTH);

        ScrollPane scroll = new ScrollPane();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                String s = (String) table.getValueAt(i, 4);
                for (JourneyDTO journey :stationboard) if (journey.getName().equals(s)) new JourneyWindow(journey);
            };
        });

        scroll.add(table);
        add(scroll, BorderLayout.CENTER);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        updateTable();
    }

    private void updateTable(){
       stationboard = manager.getStationboard(loc.getText());
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            model.removeRow(0);
        }
        for (JourneyDTO journey : stationboard) {
            Vector<String> row = new Vector<>();
            row.addElement(journey.getTo());
            row.addElement("Pl. " + journey.getDeparture().getPlatform());
            row.addElement( journey.getDeparture().getDeparture().toLocalTime().toString().substring(0, 5) +
                    (journey.getDeparture().getDelay() == 0? "" : " + " + journey.getDeparture().getDelay() + "min"));
            row.addElement(journey.getCategory() + " " + journey.getNumber());
            row.addElement(journey.getName());


            model.addRow(row);
        }
        table.setModel(model);
        table.getColumnModel().getColumn(0).setWidth(0);

    }
}
