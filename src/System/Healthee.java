package System;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Healthee {
    //MISC
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private double sideBarMenuWidth = dim.getWidth()/20;
    private String lastView = "";

    //MODULES
    private Settings settings;
    private SpecificEmployee specificEmployee;
    private Timeframe timeframe;
    private TotalIllnessHistory totalIllnessHistory;

    //FRAME
    private JFrame window;

    //GUI PANELS
    private JPanel mainPanel;
    private JPanel mainCenterPanel;

    private JPanel settingsPanel;
    private JPanel specificEmployeePanel;
    private JPanel totalIllnessHistoryPanel;

    private JPanel sideBarMenu;

    //GUI LAYOUTS
    private LayoutManager mainLayout;
    private LayoutManager mainCenterLayout;

    private LayoutManager settingsLayout;
    private LayoutManager specificEmployeeLayout;
    private LayoutManager totalIllnessHistoryLayout;

    private LayoutManager sideBarMenuLayout;

    //GUI ELEMENTS
    private JButton speceficEmplyoeeButton;
    private JButton totalIllnessHustoryButton;
    private JButton illTodayButton;
    private JButton aggIllnessDaysButton;

    private JButton homeButton;
    private JButton returnButton;

    private JList<DefaultListModel> timeFrameList;
    private DefaultListModel timeFrameListModel;

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

        //MAIN MENU
        mainLayout = new BorderLayout();
        mainCenterLayout = new GridLayout(2,2,5,5); //2x2 grid for the 4 main view action options
        mainCenterPanel = new JPanel(mainCenterLayout);
        mainPanel = new JPanel(mainLayout);

        //SETTINGS
        settingsLayout = new BorderLayout();
        settingsPanel = new JPanel(settingsLayout);

        //SPECIFIC EMPLOYEE
        specificEmployeeLayout = new BorderLayout();
        specificEmployeePanel = new JPanel(specificEmployeeLayout);

        //TOTAL ILLNESS HISTORY
        totalIllnessHistoryLayout = new BorderLayout();
        totalIllnessHistoryPanel = new JPanel(totalIllnessHistoryLayout);

        //BUTTONS
        speceficEmplyoeeButton = new JButton("Specific Employee");
        speceficEmplyoeeButton.setBackground(Color.white);
        illTodayButton = new JButton("Ill Today");
        illTodayButton.setBackground(Color.white);
        totalIllnessHustoryButton = new JButton("Total Illness History");
        totalIllnessHustoryButton.setBackground(Color.white);
        aggIllnessDaysButton = new JButton("Agg. Illness Days");
        aggIllnessDaysButton.setBackground(Color.white);

        Dimension sideBarMenuButtonDim = new Dimension((int)sideBarMenuWidth,(int)sideBarMenuWidth);
        homeButton = new JButton("Home");
        homeButton.setBackground(Color.white);
        homeButton.setPreferredSize(sideBarMenuButtonDim);

        returnButton = new JButton("Return to \n"+lastView);
        returnButton.setBackground(Color.white);
        returnButton.setPreferredSize(sideBarMenuButtonDim);

        //TIME FRAME LIST SETUP
        timeFrameListModel = new DefaultListModel<>();
        timeFrameListModel.addElement("Past Week");
        timeFrameListModel.addElement("Past 2 Weeks");
        timeFrameListModel.addElement("Past Month");
        timeFrameListModel.addElement("Past 3 Months");
        timeFrameListModel.addElement("Past 6 Months");
        timeFrameListModel.addElement("Past Year");
        timeFrameListModel.addElement("All Time");

        timeFrameList = new JList<>(timeFrameListModel);
        //timeFrameList.setPreferredSize(new Dimension(100,125));
        timeFrameList.setFixedCellWidth((int)sideBarMenuWidth);
        timeFrameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        timeFrameList.setLayoutOrientation(JList.VERTICAL);

        //SIDEBAR
        GridBagConstraints sideBarMenuConstraints = new GridBagConstraints();
        sideBarMenuConstraints.gridx = 0;
        sideBarMenuConstraints.gridy = 0;
        sideBarMenuConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        sideBarMenuLayout = new GridBagLayout();
        sideBarMenu = new JPanel(sideBarMenuLayout);
        sideBarMenu.add(homeButton, sideBarMenuConstraints);
        sideBarMenuConstraints.gridy = 1;
        JTextField timeFrameText = new JTextField("Time Frame");
        timeFrameText.setPreferredSize(new Dimension((int)sideBarMenuWidth, 25));
        timeFrameText.setEditable(false);
        sideBarMenu.add(timeFrameText, sideBarMenuConstraints);
        sideBarMenuConstraints.gridy = 2;
        sideBarMenu.add(timeFrameList, sideBarMenuConstraints);


        //Sidebar menu setup
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainScreen();
            }
        });

        //Setting up default settings for different views
        //Main view
        mainPanel.add(sideBarMenu, BorderLayout.WEST);
        mainPanel.add(mainCenterPanel, BorderLayout.CENTER);
        mainCenterPanel.add(illTodayButton);
        mainCenterPanel.add(speceficEmplyoeeButton);
        mainCenterPanel.add(totalIllnessHustoryButton);
        mainCenterPanel.add(aggIllnessDaysButton);
        speceficEmplyoeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: This has to not be hardcoded, but instead use a proper object reference later
                lastView = "Specific Employee";
                //returnButton.setText("Return to \n"+lastView);
                returnButton.setText("<html><center>"+"Return to"+"<br>"+lastView+"</center></html>");
                openSpecificEmployee();
            }
        });
    }

    public static void main(String args[]) {
        Healthee program = new Healthee();
        program.show();
    }

    public void show() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Pressing the windows X will terminate the program
        window.setSize(dim.width/4*3,dim.height/4*3); //Size of frame is 3/4 of screen resolution for width and height
        openMainScreen();
        window.setResizable(false);
        window.setContentPane(mainPanel);
        window.setLocationRelativeTo(null); //Sets the window to appear in the center of the main monitor
        window.setVisible(true); //Makes the frame visible
    }

    public void openMainScreen() {
        specificEmployeePanel.setVisible(false);
        settingsPanel.setVisible(false);
        totalIllnessHistoryPanel.setVisible(false);
        sideBarMenu.remove(homeButton);
        sideBarMenu.add(returnButton);
        mainPanel.add(sideBarMenu,BorderLayout.WEST);
        window.setContentPane(mainPanel);
        mainPanel.setVisible(true);
    }

    public void openSpecificEmployee() {
        mainPanel.setVisible(false);
        sideBarMenu.remove(returnButton);
        sideBarMenu.add(homeButton);
        specificEmployeePanel.add(sideBarMenu,BorderLayout.WEST);
        window.setContentPane(specificEmployeePanel);
        specificEmployeePanel.setVisible(true);
    }
}
