package System;

import javax.imageio.ImageIO;
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

    //GUI GRAPHICS
    private ImageIcon homeButtonIcon;


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
        homeButton = new JButton();

        try {
            Image img = ImageIO.read(getClass().getResource("resources/home_button.png"));
            homeButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        //Sidebar menu setup
        homeButtonPressed = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Home Button pressed");
                openMainScreen();
            }
        };
        homeButton.addActionListener(homeButtonPressed);

        //Setting up default settings for different views
        //Main view
        mainPanel.setLayout(mainLayout); // Layout is a borderLayout
        mainPanel.add(sideBarMenu, BorderLayout.WEST);
        mainPanel.add(speceficEmplyoeeButton,BorderLayout.CENTER);
        speceficEmplyoeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Specific Employee Button pressed");
                openSpecificEmployee();
            }
        });

        //Specific employee view
        specificEmployeePanel.add(sideBarMenu,BorderLayout.WEST);
        sideBarMenu.add(homeButton);
        window.setContentPane(specificEmployeePanel);
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
        specificEmployeePanel.setVisible(false);
        settingsPanel.setVisible(false);
        totalIllnessHistoryPanel.setVisible(false);
        window.setContentPane(mainPanel);
        mainPanel.setVisible(true);
    }

    public void openSpecificEmployee() {
        mainPanel.setVisible(false);
        window.setContentPane(specificEmployeePanel);
        specificEmployeePanel.setVisible(true);
    }
}
