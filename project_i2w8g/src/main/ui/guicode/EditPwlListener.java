package ui.guicode;

import model.Movie;
import model.PlanToWatchList;
import persistence.PlanToWLJsonReader;
import persistence.PlanToWLJsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;
//CITATIONS:
// ButtonDemo.java was used as a basis for methods in this class, this can be found at the link below
//LINK: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java
// TextInputDemo.java was used as a basis for methods in this class, this can be found at the link below
//LINK: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextInputDemoProject/src/components/TextInputDemo.java
// LabelDemo.java was used as a basis for methods in this class, this can be found at the link below
//LINK: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/LabelDemoProject/src/components/LabelDemo.java

//Contains methods that display text fields, take user input, create a window and buttons for adding to the plan to
//watch list.
public class EditPwlListener extends JPanel implements ActionListener, FocusListener {
    PlanToWatchList planToWatchList;

    private JLabel label;
    JTextField nameField;
    JTextField genreField;
    final int gap = 10;

    private static final String JSON_STORE_PWL = "./data/planToWatchList.json";
    private final PlanToWLJsonReader jsonReaderPWL;
    private final PlanToWLJsonWriter jsonWriterPWL;

    //EFFECTS: Formats window, sets the already watched list, adds fields/buttons to window,
    // and adds them to the container
    public EditPwlListener() {

        planToWatchList = new PlanToWatchList();
        this.jsonReaderPWL = new PlanToWLJsonReader(JSON_STORE_PWL);
        this.jsonWriterPWL = new PlanToWLJsonWriter(JSON_STORE_PWL);

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel panelEditPWL = new JPanel() {
            public Dimension getMaximumSize() {
                Dimension pref = getPreferredSize();
                return new Dimension(Integer.MAX_VALUE,
                        pref.height);
            }
        };
        panelEditPWL.setLayout(new BoxLayout(panelEditPWL,
                BoxLayout.PAGE_AXIS));
        panelEditPWL.add(createEntryFields());
        panelEditPWL.add(createButtons());

        add(panelEditPWL);
    }

    //EFFECTS: Creates a button and assigns it to the actionPreformedAddMovie Listener and returns a
    // formatted JComponent that includes the button
    protected JComponent createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        JButton buttonAdd = new JButton("Add Movie");
        buttonAdd.addActionListener(this::actionPreformedAddMovie);
        panel.add(buttonAdd);

        JButton buttonRemove = new JButton("Remove Movie");
        buttonRemove.addActionListener(this::actionPreformedRemoveMovie);
        panel.add(buttonRemove);

        JButton buttonDisplay = new JButton("Display Plan To Watch List");
        buttonDisplay.addActionListener(this::actionPerformedDisplay);
        panel.add(buttonDisplay);

        JButton buttonSave = new JButton("Save Plan To Watch List");
        buttonSave.addActionListener(this::actionPreformedSavePWL);
        panel.add(buttonSave);

        JButton buttonLoad = new JButton("Load Plan To Watch List");
        buttonLoad.addActionListener(this::actionPreformedLoadPWL);
        panel.add(buttonLoad);

        panel.setBorder(BorderFactory.createEmptyBorder(0, 0,
                gap - 5, gap - 5));
        return panel;
    }

    //EFFECTS: Creates and sets up the window and content pane, displays the window, and displays the fields for the
    // user to input stuff in
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Plan To Watch List Editor");

        EditPwlListener paneEditPWL = new EditPwlListener();
        paneEditPWL.setOpaque(true);
        frame.setContentPane(paneEditPWL);

        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: Takes the text that has been typed into the fields to create a Movie and adds it to the
    // plan to watch list
    public void actionPreformedAddMovie(ActionEvent e) {
        String name = nameField.getText();
        String genre = genreField.getText();
        Movie movie = new Movie(name, genre, 0);
        planToWatchList.addMovie(movie);
    }

    //EFFECTS: Takes the text that has been typed into the fields to create a Movie and removes it from the
    // plan to watch list
    public void actionPreformedRemoveMovie(ActionEvent e) {
        String name = nameField.getText();
        String genre = genreField.getText();
        Movie movie = new Movie(name, genre, 0);
        planToWatchList.removeMovie(movie);

    }

    //MODIFIES: this
    //EFFECTS: Reads json file and sets it to the this
    public void actionPreformedLoadPWL(ActionEvent e) {
        try {
            this.planToWatchList = jsonReaderPWL.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //MODIFIES: jsonWriterPWL
    //EFFECTS: Writes this to jsonWriterPWL
    public void actionPreformedSavePWL(ActionEvent e) {
        try {
            jsonWriterPWL.openPWL();
            jsonWriterPWL.writePWL(this.planToWatchList);
            jsonWriterPWL.closePWL();
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }
    }

    //EFFECTS: Creates and sets up the window and content pane, displays the window, and displays the already watched
    // list by creating a DisplayAwlListener.
    public void actionPerformedDisplay(ActionEvent e) {
        JFrame frame = new JFrame("Plan To Watch List");

        label = new JLabel();
        label.setText("Name:     Genre:    Rating:");
        frame.add(label);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);

        DisplayPwlListener displayPwlListenerPane = new DisplayPwlListener(this.planToWatchList);
        displayPwlListenerPane.setOpaque(true);
        panel.add(displayPwlListenerPane);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: Creates a JComponent with 3 text fields and associates each field with an input and then returns the
    // JComponent.
    protected JComponent createEntryFields() {
        JPanel panelEditPWL = new JPanel();

        String[] labelStrings = {"Name: ", "Genre: "};

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        setUpFields();
        fields[fieldNum++] = nameField;
        fields[fieldNum++] = genreField;

        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i], JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panelEditPWL.add(labels[i]);
            panelEditPWL.add(fields[i]);

            JTextField tf = null;
            tf = (JTextField)fields[i];
            tf.addActionListener(this);
            tf.addFocusListener(this);
        }


        return panelEditPWL;
    }

    //EFFECTS: set up the fields
    public void setUpFields() {
        nameField = new JTextField();
        nameField.setColumns(20);
        nameField.setText("");

        genreField = new JTextField();
        genreField.setColumns(20);
        genreField.setText("");
    }

    //Ignore
    @Override
    public void focusGained(FocusEvent e) {}

    //Ignore
    @Override
    public void focusLost(FocusEvent e) {}
}
