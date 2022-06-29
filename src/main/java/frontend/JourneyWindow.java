package frontend;

import dto.JourneyDTO;
import dto.StopDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class JourneyWindow extends JFrame {

    private JourneyDTO journey;

    public JourneyWindow(JourneyDTO journey) {
        this.journey = journey;

        this.setSize(400, 450);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        final DefaultTableModel model = new DefaultTableModel(new Object[]{"Station", "Platform", "Arrival", "Departure"}, 0);


        JTable table = new JTable();
        table.setModel(model);


        for(StopDTO stop: journey.getStops()){
            if (stop.getStation().getName().equals(""))
                continue;
                Vector<String> row = new Vector<>();
                row.addElement(stop.getStation().getName());
                row.addElement("Pl. " + stop.getPlatform());
                row.addElement(stop.getArrival() == null? "-" : stop.getArrival().toLocalTime().toString().substring(0, 5) +
                        (journey.getDeparture().getDelay() == 0? "" : " + " + journey.getDeparture().getDelay() + "min"));
                row.addElement(stop.getDeparture() == null? "-" : stop.getDeparture().toLocalTime().toString().substring(0, 5) +
                        (journey.getDeparture().getDelay() == 0? "" : " + " + journey.getDeparture().getDelay() + "min"));

                model.addRow(row);
            }
            table.setModel(model);


        ScrollPane scroll = new ScrollPane();
        scroll.add(table);

        add(scroll, BorderLayout.CENTER);



        this.setVisible(true);

    }
}
