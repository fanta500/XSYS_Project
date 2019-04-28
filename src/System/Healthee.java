package System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Healthee {
    //MISC
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    //MODULES
    private Settings settings;
    private SpecificEmployee specificEmployee;
    private Timeframe timeframe;
    private TotalIllnessHistory totalIllnessHistory;

    //FRAME
    private JFrame window;

    //GUI PANELS
    private JPanel mainPanel;
    private JPanel settingsPanel;
    private JPanel specificEmployeePanel;
    private JPanel totalIllnessHistoryPanel;

    private JPanel sideBarMenu;

    //GUI LAYOUTS
    private LayoutManager mainLayout;
    private LayoutManager settingsLayout;
    private LayoutManager specificEmployeeLayout;
    private LayoutManager totalIllnessHistoryLayout;

    private LayoutManager sideBarMenuLayout;

    //GUI ELEMENTS
    private JButton speceficEmplyoeeButton;
    private JButton homeButton;
    private ActionListener homeButtonPressed;


    private Healthee() {
        //INIT SYSTEMS
        settings = new Settings();
        specificEmployee = new SpecificEmployee();
        timeframe = new Timeframe();
        totalIllnessHistory = new TotalIllnessHistory();

        //INIT GUI ELEMENTS
        window = new JFrame("Healthee");
        mainLayout = new BorderLayout();
        mainPanel = new JPanel(mainLayout);

        settingsLayout = new BorderLayout();
        settingsPanel = new JPanel(settingsLayout);

        specificEmployeeLayout = new BorderLayout();
        specificEmployeePanel = new JPanel(specificEmployeeLayout);

        totalIllnessHistoryLayout = new BorderLayout();
        totalIllnessHistoryPanel = new JPanel(totalIllnessHistoryLayout);

        sideBarMenuLayout = new GridLayout(10,1);
        sideBarMenu = new JPanel(sideBarMenuLayout);

        speceficEmplyoeeButton = new JButton("Lars Larsen");
        homeButton = new JButton("Main Screen");

        homeButtonPressed = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainScreen();
            }
        };
    }

    public static void main(String args[]) {
        Healthee program = new Healthee();
        program.show();
    }

    public void show() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Pressing the windows X will terminate the program
        window.setSize(dim.width/4*3,dim.height/4*3); //Size of frame is 3/4 of screen resolution for width and height
        openMainScreen();
        window.setContentPane(mainPanel);
        window.setLocationRelativeTo(null); //Sets the window to appear in the center of the main monitor
        window.setVisible(true); //Makes the frame visible
    }

    public void openMainScreen() {
        mainPanel.setLayout(mainLayout); // Layout is a borderLayout
        mainPanel.add(sideBarMenu, BorderLayout.WEST);
        sideBarMenu.add(speceficEmplyoeeButton);
        speceficEmplyoeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSpecificEmployee();
            }
        });
        mainPanel.setVisible(true);
    }

    public void openSpecificEmployee() {
        mainPanel.setVisible(false);
        window.setContentPane(specificEmployeePanel);

    }
}
