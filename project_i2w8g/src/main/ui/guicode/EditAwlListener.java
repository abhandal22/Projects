package ui.guicode;

import model.AlreadyWatchedList;
import model.Movie;
import persistence.AlreadyWLJsonReader;
import persistence.AlreadyWLJsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
//CITATIONS:
// ButtonDemo.java was used as a basis for methods in this class, this can be found at the link below
//LINK: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java
// TextInputDemo.java was used as a basis for methods in this class, this can be found at the link below
//LINK: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextInputDemoProject/src/components/TextInputDemo.java
// LabelDemo.java was used as a basis for methods in this class, this can be found at the link below
//LINK: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/LabelDemoProject/src/components/LabelDemo.java

//Contains methods that display text fields, take user input, create a window and buttons for adding to the already
//watched list.
public class EditAwlListener extends JPanel implements ActionListener, FocusListener {

    protected AlreadyWatchedList alreadyWatchedList;

    private JLabel label;
    JTextField nameField;
    JTextField genreField;
    JTextField ratingField;
    final int gap = 10;

    private static final String JSON_STORE_AWL = "./data/alreadyWatchedList.json";
    private AlreadyWLJsonReader jsonReaderAWL;
    private AlreadyWLJsonWriter jsonWriterAWL;

    //EFFECTS: Formats window, sets the already watched list, adds fields/buttons to window,
    // and adds them to the container
    public EditAwlListener() {

        alreadyWatchedList = new AlreadyWatchedList();
        this.jsonReaderAWL = new AlreadyWLJsonReader(JSON_STORE_AWL);
        this.jsonWriterAWL = new AlreadyWLJsonWriter(JSON_STORE_AWL);

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel panel = new JPanel() {
            public Dimension getMaximumSize() {
                Dimension pref = getPreferredSize();
                return new Dimension(Integer.MAX_VALUE,
                        pref.height);
            }
        };
        panel.setLayout(new BoxLayout(panel,
                BoxLayout.PAGE_AXIS));
        panel.add(createEntryFields());
        panel.add(createButtons());

        add(panel);
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

        JButton buttonDisplay = new JButton("Display Already Watched List");
        buttonDisplay.addActionListener(this::actionPerformedDisplay);
        panel.add(buttonDisplay);

        JButton buttonSave = new JButton("Save Already Watched List");
        buttonSave.addActionListener(this::actionPreformedSaveAWL);
        panel.add(buttonSave);

        JButton buttonLoad = new JButton("Load Already Watched List");
        buttonLoad.addActionListener(this::actionPreformedLoadAWL);
        panel.add(buttonLoad);

        panel.setBorder(BorderFactory.createEmptyBorder(0, 0,
                gap - 5, gap - 5));
        return panel;
    }

    //EFFECTS: Creates and sets up the window and content pane, displays the window, and displays the fields for the
    // user to input stuff in
    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame frame = new JFrame("Already Watched List Editor");

        EditAwlListener editAwlListenerPane = new EditAwlListener();
        editAwlListenerPane.setOpaque(true);
        frame.setContentPane(editAwlListenerPane);

        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: Takes the text that has been typed into the fields to create a Movie and adds it to the
    // alreadywacthedlist
    public void actionPreformedAddMovie(ActionEvent e) {
        String name = nameField.getText();
        String genre = genreField.getText();
        String rating = ratingField.getText();
        int convertedRating = Integer.parseInt(rating);
        Movie movie = new Movie(name, genre, convertedRating);
        alreadyWatchedList.addMovie(movie);
    }

    //EFFECTS: Takes the text that has been typed into the fields to create a Movie and removes it from the
    // already watched list
    public void actionPreformedRemoveMovie(ActionEvent e) {
        String name = nameField.getText();
        String genre = genreField.getText();
        String rating = ratingField.getText();
        int convertedRating = Integer.parseInt(rating);
        Movie movie = new Movie(name, genre, convertedRating);
        alreadyWatchedList.removeMovie(movie);

    }

    //MODIFIES: this
    //EFFECTS: Reads json file and sets it to the this
    public void actionPreformedLoadAWL(ActionEvent e) {
        try {
            this.alreadyWatchedList = jsonReaderAWL.read();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    //MODIFIES: jsonWriterPWL
    //EFFECTS: Writes this to jsonWriterPWL
    public void actionPreformedSaveAWL(ActionEvent e) {
        try {
            jsonWriterAWL.openAWL();
            jsonWriterAWL.writeAWL(this.alreadyWatchedList);
            jsonWriterAWL.closeAWL();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    //EFFECTS: Creates and sets up the window and content pane, displays the window, and displays the already watched
    // list by creating a DisplayAwlListener.
    public void actionPerformedDisplay(ActionEvent e) {
        JFrame frame = new JFrame("Already Watched List");

        label = new JLabel();
        label.setText("Name:     Genre:    Rating:");
        frame.add(label);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);

        DisplayAwlListener newContentPane = new DisplayAwlListener(this.alreadyWatchedList);
        newContentPane.setOpaque(true);
        panel.add(newContentPane);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: Creates a JComponent with 3 text fields and associates each field with an input and then returns the
    // JComponent.
    protected JComponent createEntryFields() {
        JPanel panelEditAWL = new JPanel();

        String[] labelStrings = {"Name: ", "Genre: ", "Rating: "};

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        setUpFields();
        fields[fieldNum++] = nameField;
        fields[fieldNum++] = genreField;
        fields[fieldNum++] = ratingField;

        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i], JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panelEditAWL.add(labels[i]);
            panelEditAWL.add(fields[i]);

            JTextField tf = null;
            tf = (JTextField)fields[i];
            tf.addActionListener(this);
            tf.addFocusListener(this);
        }


        return panelEditAWL;
    }

    //EFFECTS: set up the fields
    public void setUpFields() {
        nameField = new JTextField();
        nameField.setColumns(20);
        nameField.setText("");

        genreField = new JTextField();
        genreField.setColumns(20);
        genreField.setText("");

        ratingField = new JTextField();
        ratingField.setText("0");
        ratingField.setColumns(20);
    }


    //Ignore
    @Override
    public void focusGained(FocusEvent e) {}

    //Ignore
    @Override
    public void focusLost(FocusEvent e) {}

}

