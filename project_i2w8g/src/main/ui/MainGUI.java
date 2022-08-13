package ui;


import model.Event;
import model.EventLog;
import ui.guicode.EditAwlListener;
import ui.guicode.EditPwlListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//CITATIONS:
// ButtonDemo.java was used as a basis for methods in this class, this can be found at the link below
//LINK: https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java
//The picture of the clock was taken from the link bellow
//LINK: https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.istockphoto.com%2Fillustrations%2Fcartoon-of-a-ticking-clock-animation&psig=AOvVaw1ULwBpc4ZkSfqA6xnIFArZ&ust=1636679257458000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCMDqpsCPj_QCFQAAAAAdAAAAABAO
//The picture of the eyes was taken from the link bellow
//LINK: https://www.google.com/url?sa=i&url=https%3A%2F%2Fpixabay.com%2Fillustrations%2Fcartoon-eyes-look-looking-anatomy-313457%2F&psig=AOvVaw2yDhz0hRlBaZisOG62Sgq5&ust=1636679530704000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCLiOxtSQj_QCFQAAAAAdAAAAABAD

//Contains methods that creates, runs, and displays the GUI, and it's parts(buttons, pictures, etc.)
public class MainGUI extends JPanel implements ActionListener {

    EditAwlListener buttonEventAWL;
    EditPwlListener buttonEventPWL;


    protected JButton buttonAWL;
    protected JButton buttonPWL;

    //EFFECTS: Creates the button icons, buttons, sets buttons to action listeners, and adds them to the container
    public MainGUI() {

        ImageIcon buttonIconAWL = createImageIcon("./data/eyes.gif");
        ImageIcon buttonIconPWL = createImageIcon("./data/clock.gif");

        buttonAWL = new JButton("See/edit you already watched list", buttonIconAWL);
        buttonAWL.setHorizontalTextPosition(AbstractButton.LEADING);
        buttonAWL.setMnemonic(KeyEvent.VK_D);
        buttonAWL.setEnabled(true);
        buttonAWL.setActionCommand("disable");

        buttonPWL = new JButton("See/edit you plan to watch list", buttonIconPWL);
        buttonPWL.setHorizontalTextPosition(AbstractButton.LEADING);
        buttonPWL.setMnemonic(KeyEvent.VK_D);
        buttonPWL.setEnabled(true);
        buttonPWL.setActionCommand("disable");

        buttonEventAWL = new EditAwlListener();

        buttonEventPWL = new EditPwlListener();

        buttonAWL.addActionListener(buttonEventAWL);
        buttonPWL.addActionListener(buttonEventPWL);

        add(buttonAWL);
        add(buttonPWL);

    }


    //EFFECTS: Returns a scaled version of the image given at the given path, if path isn't found
    // or is invalid returns null.
    protected static ImageIcon createImageIcon(String path) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image imgURL = t.getImage(path);
        Image scaledImgURL = imgURL.getScaledInstance(50, 50, 50);
        if (imgURL != null) {
            return new ImageIcon(scaledImgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    //Stackoverflow was used to get the close button to do something when the frame is closed.
    //LINK: https://stackoverflow.com/questions/9093448/how-to-capture-a-jframes-close-button-click-event
    //EFFECTS: Creates and sets up the window and content pane and displays the window.
    private static void createAndShowGUI() {

        JFrame frame = new JFrame("Movie Organizer");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event);
                }
                System.exit(0);
            }
        });

        MainGUI paneMainGUI = new MainGUI();
        paneMainGUI.setOpaque(true);
        frame.setContentPane(paneMainGUI);

        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: Creates and shows a new GUI. (Runs the application)
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    //Ignore
    @Override
    public void actionPerformed(ActionEvent e) {}
}
