package ui.guicode;

import model.AlreadyWatchedList;
import model.Movie;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
//CITATIONS:
// ListDemo.java was used as a basis for methods in this class, this can be found at the link below
//LINK: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
// ButtonDemo.java was used as a basis for methods in this class, this can be found at the link below
//LINK: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java

//Contains methods that creates and displays the DisplayAwl Window.
public class DisplayAwlListener extends JPanel implements ListSelectionListener {
    protected AlreadyWatchedList awl;

    private final JList listAWL;
    private final DefaultListModel listModelAWL;

    //EFFECTS: Formats window and makes new selectable and scrolling list and adds them to the container
    public DisplayAwlListener(AlreadyWatchedList awl) {
        super(new BorderLayout());

        this.awl = awl;

        listModelAWL = new DefaultListModel();

        for (Movie m : this.awl.getAlreadyWatchedList()) {
            listModelAWL.addElement(convertToString(m));
        }

        listAWL = new JList(listModelAWL);
        listAWL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listAWL.setSelectedIndex(0);
        listAWL.addListSelectionListener(this);
        listAWL.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(listAWL);

        add(listScrollPane, BorderLayout.CENTER);
    }

    //EFFECTS: returns the given movie as a string.
    public String convertToString(Movie m) {
        return (m.getName() + " " + m.getGenre() + " " + m.getRating() + "/10");
    }

    //Ignore
    @Override
    public void valueChanged(ListSelectionEvent e) {}
}
