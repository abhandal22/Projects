package ui.guicode;

import model.Movie;
import model.PlanToWatchList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
//CITATIONS:
// ListDemo.java was used as a basis for methods in this class, this can be found at the link below
//LINK: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
// ButtonDemo.java was used as a basis for methods in this class, this can be found at the link below
//LINK: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java

//Contains methods that creates and displays the DisplayPwl Window.
public class DisplayPwlListener extends JPanel implements ListSelectionListener {
    protected PlanToWatchList planToWatchList;

    private final JList listPWL;
    private final DefaultListModel listModelPWL;

    //EFFECTS: Formats window and makes new selectable and scrolling list and adds them to the container
    public DisplayPwlListener(PlanToWatchList pwl) {
        super(new BorderLayout());

        this.planToWatchList = pwl;

        listModelPWL = new DefaultListModel();

        for (Movie m : this.planToWatchList.getPlanToWatchList()) {
            listModelPWL.addElement(convertToString(m));
        }

        listPWL = new JList(listModelPWL);
        listPWL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPWL.setSelectedIndex(0);
        listPWL.addListSelectionListener(this);
        listPWL.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(listPWL);

        add(listScrollPane, BorderLayout.CENTER);
    }

    //EFFECTS: returns the given movie as a string.
    public String convertToString(Movie m) {
        return (m.getName() + " " + m.getGenre() + " " + m.getRating() + "/NA");
    }

    //Ignore
    @Override
    public void valueChanged(ListSelectionEvent e) {}
}
