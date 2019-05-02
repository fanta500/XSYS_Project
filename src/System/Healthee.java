package System;

import Data.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Alexander Schacht
 * This class is a complete GUI with included "fake-it" data to encapsulate the proof of concept.
 */

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
    private JPanel mainCenterTopRightPanel;

    private JPanel settingsPanel;
    private JPanel specificEmployeePanel;
    private JPanel totalIllnessHistoryPanel;

    private JPanel sideBarMenu;
    private JPanel leftPagePanel;

    //GUI LAYOUTS
    private LayoutManager mainLayout;
    private LayoutManager mainCenterLayout;
    private LayoutManager mainCenterTopRightLayout;

    private LayoutManager settingsLayout;
    private LayoutManager specificEmployeeLayout;
    private LayoutManager totalIllnessHistoryLayout;

    private LayoutManager sideBarMenuLayout;

    //GUI ELEMENTS
    private JButton specificEmployeeButton;
    private JButton totalIllnessHustoryButton;
    private JButton illTodayButton;
    private JButton aggIllnessDaysButton;

    private JButton homeButton;
    private JButton returnButton;

    private JList<DefaultListModel> timeFrameList;
    private DefaultListModel timeFrameListModel;

    //GUI GRAPHICS
    private ImageIcon homeButtonIcon;

    //EMPLOYEE DATA
    private JTable employeeList;
    private JScrollPane employeeListScrollPane;

    private Employee emp1;
    private Employee emp2;
    private Employee emp3;
    private Employee emp4;
    private Employee emp5;
    private Employee emp6;
    private Employee emp7;


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
        mainCenterTopRightLayout = new GridBagLayout();
        mainPanel = new JPanel(mainLayout);
        mainCenterPanel = new JPanel(mainCenterLayout);
        mainCenterTopRightPanel = new JPanel();
        leftPagePanel = new JPanel(); //Default layout is borderlayout. This is used to contain the sidebarmenu, in the north part of the borderlayout, to force top aligned

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
        specificEmployeeButton = new JButton("Specific Employee");
        specificEmployeeButton.setBackground(Color.white);
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
        leftPagePanel.add(sideBarMenu, BorderLayout.NORTH);
        mainPanel.add(leftPagePanel, BorderLayout.WEST);
        mainPanel.add(mainCenterPanel, BorderLayout.CENTER);
        mainCenterPanel.add(illTodayButton);
        mainCenterPanel.add(mainCenterTopRightPanel);
        mainCenterPanel.add(totalIllnessHustoryButton);
        mainCenterPanel.add(aggIllnessDaysButton);
        specificEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                sideBarMenu.remove(returnButton);
                sideBarMenu.add(homeButton);
                lastView = "Employee";
                returnButton.setText("<html><center>"+"Return to"+"<br>"+lastView+"</center></html>");
                openSpecificEmployee();
            }
        });
        totalIllnessHustoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                sideBarMenu.remove(returnButton);
                sideBarMenu.add(homeButton);
                lastView = "Illness History";
                returnButton.setText("<html><center>"+"Return to"+"<br>"+lastView+"</center></html>");
                openTotalIllnessHistory();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lastView.equals("Employee")) {
                    mainPanel.setVisible(false);
                    sideBarMenu.remove(returnButton);
                    sideBarMenu.add(homeButton);
                    openSpecificEmployee();
                } else if (lastView.equals("Illness History")) {
                    mainPanel.setVisible(false);
                    sideBarMenu.remove(returnButton);
                    sideBarMenu.add(homeButton);
                    openTotalIllnessHistory();
                }
            }
        });
        createEmployees();
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
        mainPanel.add(leftPagePanel, BorderLayout.WEST);
        window.setContentPane(mainPanel);
        mainPanel.setVisible(true);
    }

    public void openSpecificEmployee() {
        specificEmployeePanel.add(leftPagePanel,BorderLayout.WEST);
        window.setContentPane(specificEmployeePanel);
        specificEmployeePanel.setVisible(true);
    }

    private void openTotalIllnessHistory() {
        totalIllnessHistoryPanel.add(leftPagePanel,BorderLayout.WEST);
        window.setContentPane(totalIllnessHistoryPanel);
        totalIllnessHistoryPanel.setVisible(true);
    }

    private void createEmployees() {
        emp1 = new Employee(29, "Stine Larsen", "CEO");
        emp2 = new Employee(33, "Peter Hansen", "Engineer");
        emp3 = new Employee(26, "Maya Shultz", "Designer");
        emp4 = new Employee(24, "Pernille Jensen", "Marketing");
        emp5 = new Employee(30, "Laurel Markson", "Sales");
        emp6 = new Employee(30, "Willem de Lange", "Designer");
        emp7 = new Employee(32, "Emma May", "Finance");

        String[][] employeeData = {
                {emp1.getName(), emp1.getPosition()},
                {emp2.getName(), emp2.getPosition()},
                {emp3.getName(), emp3.getPosition()},
                {emp4.getName(), emp4.getPosition()},
                {emp5.getName(), emp5.getPosition()},
                {emp6.getName(), emp6.getPosition()},
                {emp7.getName(), emp7.getPosition()},
        };
        String[] columnNames = {"Name", "Position"};
        employeeList = new JTable(employeeData, columnNames);
        
        employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeeListScrollPane = new JScrollPane(employeeList);

        mainCenterTopRightPanel.add(employeeListScrollPane);
        /*
        System.out.println("Total Illness for emp1: "+emp1.getSickDays().get(6));
        System.out.println("Total Illness for emp2: "+emp2.getSickDays().get(6));
        System.out.println("Total Illness for emp3: "+emp3.getSickDays().get(6));
        System.out.println("Total Illness for emp4: "+emp4.getSickDays().get(6));
        System.out.println("Total Illness for emp5: "+emp5.getSickDays().get(6));
        System.out.println("Total Illness for emp6: "+emp6.getSickDays().get(6));
        System.out.println("Total Illness for emp7: "+emp7.getSickDays().get(6));
        */
    }
}
