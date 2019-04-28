package System;

import javax.swing.*;
import java.awt.*;

public class Healthee {
    //MISC
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    //MODULES
    private Settings settings;
    private SpecificEmployee specificEmployee;
    private Timeframe timeframe;
    private TotalIllnessHistory totalIllnessHistory;

    //GUI ELEMENTS
    private JFrame window;

    //GUI WINDOWS
    private Container mainContainer;
    private Container settingsContainer;
    private Container specificEmployeeContainer;
    private Container totalIllnessHistoryContainer;

    //GUI LAYOUTS
    private LayoutManager mainLayout;
    private LayoutManager settingsLayout;
    private LayoutManager specificEmployeeLayout;
    private LayoutManager totalIllnessHistoryLayout;

    private Healthee() {
        //INIT SYSTEMS
        settings = new Settings();
        specificEmployee = new SpecificEmployee();
        timeframe = new Timeframe();
        totalIllnessHistory = new TotalIllnessHistory();

        //INIT GUI ELEMENTS
        window = new JFrame("Healthee");
        mainContainer = new Container();
        mainLayout = new GridLayout(3,3);

        settingsContainer = new Container();
        settingsLayout = new GridLayout(3,3);

        specificEmployeeContainer = new Container();
        specificEmployeeLayout = new GridLayout(3,3);

        totalIllnessHistoryContainer = new Container();
        totalIllnessHistoryLayout = new GridLayout(3,3);

    }

    public static void main(String args[]) {
        Healthee program = new Healthee();
        program.show();
    }

    public void show() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Pressing the windows X will terminate the program
        window.setSize(dim.width/4*3,dim.height/4*3); //Size of frame is 3/4 of screen resolution for width and height
        setupMainScreen();
    }

    public void setupMainScreen() {
        //window.setLayout(mainLayout);
        window.setContentPane(mainContainer);
        mainContainer.setLayout(mainLayout); // Layout is 3x3 grid layout. Some grid spaces will be merged

        window.setLocationRelativeTo(null); //Sets the window to appear in the center of the main monitor
        window.setVisible(true); //Makes the frame visible
    }
}
