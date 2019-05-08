package System;

import Data.Employee;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Alexander Schacht
 * This class is a complete GUI with included "fake-it" data to encapsulate the proof of concept.
 */

public class Healthee {
    //MISC
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private double sideBarMenuWidth = dim.getWidth()/20;
    private String lastView = "";
    private ArrayList<Employee> employees;
    private boolean specificEmployeeHasBeenOpened = false;

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
    private JPanel leftPagePanel;

    //GUI ELEMENTS
    private JButton specificEmployeeButton;
    private JButton totalIllnessHustoryButton;
    private JButton illTodayButton;
    private JButton aggIllnessDaysButton;

    private JButton homeButton;
    private JButton returnButton;

    private JList<DefaultListModel> timeFrameList;
    private DefaultListModel timeFrameListModel;

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
        mainPanel = new JPanel(new BorderLayout(5,5));
        mainCenterPanel = new JPanel(new GridLayout(2,2,5,5));
        leftPagePanel = new JPanel(new BorderLayout()); //Default layout is borderlayout. This is used to contain the sidebarmenu, in the north part of the borderlayout, to force top aligned

        //SETTINGS
        settingsPanel = new JPanel(new BorderLayout());

        //SPECIFIC EMPLOYEE
        specificEmployeePanel = new JPanel(new BorderLayout());

        //TOTAL ILLNESS HISTORY
        totalIllnessHistoryPanel = new JPanel(new BorderLayout());

        //BUTTONS
        setupButtons();

        //TIME FRAME LIST SETUP
        setupTimeFrameList();

        //SIDEBAR
        setupSideBar();

        //Sidebar menu setup
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainScreen();
            }
        });

        //Setup Main view
        leftPagePanel.add(sideBarMenu, BorderLayout.NORTH);
        mainPanel.add(leftPagePanel, BorderLayout.WEST);
        mainPanel.add(mainCenterPanel, BorderLayout.CENTER);

        createEmployees();
        setupIllToday();


        mainCenterPanel.add(illTodayButton);
        mainCenterPanel.add(employeeListScrollPane);
        mainCenterPanel.add(totalIllnessHustoryButton);
        mainCenterPanel.add(aggIllnessDaysButton);

        totalIllnessHustoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTotalIllnessHistory();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lastView.equals("Employee")) {
                    mainPanel.setVisible(false);
                    specificEmployeePanel.add(leftPagePanel,BorderLayout.WEST);
                    setToHomeButton();
                    window.setContentPane(specificEmployeePanel);
                    specificEmployeePanel.setVisible(true);
                } else if (lastView.equals("Illness History")) {
                    mainPanel.setVisible(false);
                    openTotalIllnessHistory();
                }
            }
        });
    }

    private void setupIllToday() {

    }

    private void setupSideBar() {
        GridBagConstraints sideBarMenuConstraints = new GridBagConstraints();
        sideBarMenuConstraints.gridx = 0;
        sideBarMenuConstraints.gridy = 0;
        sideBarMenuConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        sideBarMenu = new JPanel(new GridBagLayout());
        sideBarMenu.add(homeButton, sideBarMenuConstraints);
        sideBarMenuConstraints.gridy = 1;
        JTextField timeFrameText = new JTextField("Time Frame");
        timeFrameText.setPreferredSize(new Dimension((int)sideBarMenuWidth, 25));
        timeFrameText.setEditable(false);
        sideBarMenu.add(timeFrameText, sideBarMenuConstraints);
        sideBarMenuConstraints.gridy = 2;
        sideBarMenu.add(timeFrameList, sideBarMenuConstraints);
    }

    private void setupButtons() {
        specificEmployeeButton = new JButton("Specific Employee");
        specificEmployeeButton.setBackground(Color.white);
        illTodayButton = new JButton("Ill Today");
        illTodayButton.setBackground(Color.white);
        totalIllnessHustoryButton = new JButton("Total Illness History");
        totalIllnessHustoryButton.setBackground(Color.white);
        aggIllnessDaysButton = new JButton("Agg. Illness Days");
        aggIllnessDaysButton.setBackground(Color.white);

        Dimension sideBarMenuButtonDim = new Dimension((int)sideBarMenuWidth,(int)sideBarMenuWidth);
        homeButton = new JButton();
        try {
            BufferedImage image = ImageIO.read(new File("src/resources/home_button_small.png"));
            homeButton.setIcon(new ImageIcon(image));
            homeButton.setOpaque(false);
            homeButton.setContentAreaFilled(false);
            homeButton.setBorderPainted(false);
        } catch (IOException e) {
            System.out.println("Image could not be found");
        }
        homeButton.setBackground(Color.white);
        homeButton.setPreferredSize(sideBarMenuButtonDim);

        returnButton = new JButton("Return to \n"+lastView);
        returnButton.setBackground(Color.white);
        returnButton.setPreferredSize(sideBarMenuButtonDim);
    }

    private void setupTimeFrameList() {
        timeFrameListModel = new DefaultListModel<>();
        timeFrameListModel.addElement("Past Week");
        timeFrameListModel.addElement("Past 2 Weeks");
        timeFrameListModel.addElement("Past Month");
        timeFrameListModel.addElement("Past 3 Months");
        timeFrameListModel.addElement("Past 6 Months");
        timeFrameListModel.addElement("Past Year");
        timeFrameListModel.addElement("All Time");

        timeFrameList = new JList<>(timeFrameListModel);
        timeFrameList.setSelectedIndex(0); //Sets the selected option to the first one in the time frame list
        timeFrameList.setFixedCellWidth((int)sideBarMenuWidth);
        timeFrameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        timeFrameList.setLayoutOrientation(JList.VERTICAL);

        timeFrameList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList<DefaultListModel> target = (JList<DefaultListModel>) e.getSource();
                int index = target.getSelectedIndex();
                if (specificEmployeeHasBeenOpened) {
                    updateTimeFrameDependentObjects(index);
                }
            }
        });
    }

    public void updateTimeFrameDependentObjects(int index) {
        specificEmployee.setIllnessInTimeFrameText(index);
        specificEmployee.drawGraph(index);
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

    public void openSpecificEmployee(String name, String position, int age, ArrayList<Integer> sickDays, int timeFrameIndex, String hiringDate) {
        mainPanel.setVisible(false);
        specificEmployee.setEmployeeDetails(name, position, age, sickDays, timeFrameIndex, hiringDate); //modifies the specificEmployee object to display the correct information
        specificEmployee.drawGraph(employeeList.getSelectedRow());
        specificEmployeePanel.add(leftPagePanel,BorderLayout.WEST);
        specificEmployeePanel.add(specificEmployee.getJPanel(),BorderLayout.CENTER); //adds the panel that the specificEmployee class returns to the center of the panel
        lastView = "Employee";
        returnButton.setText("<html><center>"+"Return to"+"<br>"+lastView+"</center></html>");
        setToHomeButton();
        window.setContentPane(specificEmployeePanel);
        specificEmployeeHasBeenOpened = true;
        specificEmployeePanel.setVisible(true);
    }

    private void openTotalIllnessHistory() {
        mainPanel.setVisible(false);
        totalIllnessHistoryPanel.add(leftPagePanel,BorderLayout.WEST);
        lastView = "Illness History";
        returnButton.setText("<html><center>"+"Return to"+"<br>"+lastView+"</center></html>");
        setToHomeButton();
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

        employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);
        employees.add(emp6);
        employees.add(emp7);

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
        employeeList.setFont(new Font("P", Font.PLAIN, 20));
        employeeList.setRowHeight(25);
        employeeList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                int row = target.getSelectedRow();
                openSpecificEmployee(employees.get(row).getName(),
                                     employees.get(row).getPosition(),
                                     employees.get(row).getAge(),
                                     employees.get(row).getSickDays(),
                                     timeFrameList.getSelectedIndex(),
                                     employees.get(row).getHiringDate());
            }
        });
        employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeeListScrollPane = new JScrollPane(employeeList);
    }

    private void setToHomeButton() {
        sideBarMenu.remove(returnButton);
        sideBarMenu.add(homeButton);
    }
}
