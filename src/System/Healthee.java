package System;

import Data.Employee;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
    private SpecificEmployee specificEmployee;

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
    private JLabel totalIllnessHistoryGraph = new JLabel();
    private JLabel aggIllnessDaysGraph = new JLabel();

    private JButton homeButton;
    private JButton returnButton;

    private JList<DefaultListModel> timeFrameList;
    private DefaultListModel timeFrameListModel;

    //EMPLOYEE DATA
    private JTable employeeList;
    private JScrollPane employeeListScrollPane;

    //ILL TODAY
    private JTable illTodayList;
    private JScrollPane illTodayListScrollPane;

    private Employee emp1;
    private Employee emp2;
    private Employee emp3;
    private Employee emp4;
    private Employee emp5;
    private Employee emp6;
    private Employee emp7;

    private Healthee() {
        //INIT SYSTEMS
        specificEmployee = new SpecificEmployee();

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
                System.out.println("Home button pressed");
            }
        });

        //Setup Main view
        leftPagePanel.add(sideBarMenu, BorderLayout.NORTH);
        mainPanel.add(leftPagePanel, BorderLayout.WEST);
        mainPanel.add(mainCenterPanel, BorderLayout.CENTER);

        createEmployees(); //Generates employees and also generates random ill people today

        //Sets up the text for ill today
        JPanel mainCenterPanelTopLeft = new JPanel(new BorderLayout());
        JTextArea illTodayTextArea = new JTextArea("Ill Today");
        illTodayTextArea.setFont(new Font("P", Font.PLAIN, 24)); //Setting font size
        illTodayTextArea.setEditable(false); //Making details non-editable
        illTodayTextArea.setOpaque(false); //Remove the white backdrop

        mainCenterPanelTopLeft.add(illTodayTextArea, BorderLayout.NORTH);
        mainCenterPanelTopLeft.add(illTodayListScrollPane, BorderLayout.CENTER);

        //Sets up the text for employees
        JPanel mainCenterTopRight = new JPanel(new BorderLayout());
        JTextArea employeesTextArea = new JTextArea("Employees");
        employeesTextArea.setFont(new Font("P", Font.PLAIN, 24)); //Setting font size
        employeesTextArea.setEditable(false); //Making details non-editable
        employeesTextArea.setOpaque(false); //Remove the white backdrop

        mainCenterTopRight.add(employeesTextArea, BorderLayout.NORTH);
        mainCenterTopRight.add(employeeListScrollPane, BorderLayout.CENTER);

        //Sets up the text for total illness history
        JPanel mainCenterBottomLeft = new JPanel(new BorderLayout());
        JTextArea illnessHistoryTextArea = new JTextArea("Total Illness History");
        illnessHistoryTextArea.setFont(new Font("P", Font.PLAIN, 24)); //Setting font size
        illnessHistoryTextArea.setEditable(false); //Making details non-editable
        illnessHistoryTextArea.setOpaque(false); //Remove the white backdrop

        mainCenterBottomLeft.add(illnessHistoryTextArea, BorderLayout.NORTH);
        mainCenterBottomLeft.add(totalIllnessHistoryGraph, BorderLayout.CENTER);

        //Sets up the text for sickness on weekday
        JPanel mainCenterBottomRight = new JPanel(new BorderLayout());
        JTextArea sicknessOnWeekdayTextArea = new JTextArea("Sickness on Weekday");
        sicknessOnWeekdayTextArea.setFont(new Font("P", Font.PLAIN, 24)); //Setting font size
        sicknessOnWeekdayTextArea.setEditable(false); //Making details non-editable
        sicknessOnWeekdayTextArea.setOpaque(false); //Remove the white backdrop

        mainCenterBottomRight.add(sicknessOnWeekdayTextArea, BorderLayout.NORTH);
        mainCenterBottomRight.add(aggIllnessDaysGraph, BorderLayout.CENTER);

        mainCenterPanel.add(mainCenterPanelTopLeft);
        mainCenterPanel.add(mainCenterTopRight);
        mainCenterPanel.add(mainCenterBottomLeft);
        drawMainScreenGraphs();
        mainCenterPanel.add(mainCenterBottomRight);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lastView.equals("Employee")) {
                    mainPanel.setVisible(false);
                    specificEmployeePanel.add(leftPagePanel,BorderLayout.WEST);
                    setToHomeButton();
                    window.setContentPane(specificEmployeePanel);
                    specificEmployeePanel.setVisible(true);
                    System.out.println("Return to an employee button pressed");
                } else if (lastView.equals("Illness History")) {
                    mainPanel.setVisible(false);
                    openTotalIllnessHistory();
                    System.out.println("Return to total illness history button pressed");
                }
            }
        });
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
                drawMainScreenGraphs();
                System.out.println("Clicked time frame list at index: "+index);
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
        specificEmployee.drawGraph(timeFrameList.getSelectedIndex());
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

    public void drawMainScreenGraphs() {
        int index = timeFrameList.getSelectedIndex();
        try {
            BufferedImage totalIllnessGraph = null;
            BufferedImage aggIllnessGraph = null;
            if (index==0) {
                totalIllnessGraph = ImageIO.read(new File("src/resources/total_illness_week.png"));
                aggIllnessGraph = ImageIO.read(new File("src/resources/agg_illness_week.png"));
            } else if (index==1) {
                totalIllnessGraph = ImageIO.read(new File("src/resources/total_illness_2weeks.png"));
                aggIllnessGraph = ImageIO.read(new File("src/resources/agg_illness_2weeks.png"));
            } else if (index==2) {
                totalIllnessGraph = ImageIO.read(new File("src/resources/total_illness_month.png"));
                aggIllnessGraph = ImageIO.read(new File("src/resources/agg_illness_month.png"));
            } else if (index==3) {
                totalIllnessGraph = ImageIO.read(new File("src/resources/total_illness_3months.png"));
                aggIllnessGraph = ImageIO.read(new File("src/resources/agg_illness_3months.png"));
            } else if (index==4) {
                totalIllnessGraph = ImageIO.read(new File("src/resources/total_illness_6months.png"));
                aggIllnessGraph = ImageIO.read(new File("src/resources/agg_illness_6months.png"));
            } else if (index==5) {
                totalIllnessGraph = ImageIO.read(new File("src/resources/total_illness_year.png"));
                aggIllnessGraph = ImageIO.read(new File("src/resources/agg_illness_year.png"));
            } else if (index==6) {
                totalIllnessGraph = ImageIO.read(new File("src/resources/total_illness_allTime.png"));
                aggIllnessGraph = ImageIO.read(new File("src/resources/agg_illness_allTime.png"));
            }
            totalIllnessHistoryGraph.setIcon(new ImageIcon(totalIllnessGraph));
            aggIllnessDaysGraph.setIcon(new ImageIcon(aggIllnessGraph));
        } catch (IOException e) {
            System.out.println("Image could not be found");
        }
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

        generateIllToday();

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
                System.out.println("Pressed employee list at index: "+row);
            }
        });
        employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeeListScrollPane = new JScrollPane(employeeList);
    }

    private void generateIllToday() {
        Random illPersonIndex = new Random();
        int illPerson1Index = illPersonIndex.nextInt(6);
        int illPerson2Index = illPersonIndex.nextInt(6);
        while (illPerson1Index == illPerson2Index) {
            illPerson2Index = illPersonIndex.nextInt(6);
        }
        Employee illPerson1 = employees.get(illPerson1Index);
        Employee illPerson2 = employees.get(illPerson2Index);

        String[][] employeeData = {
                {illPerson1.getName(), "<html>" + "I have an appointment with my doctor."},
                {illPerson2.getName(), "<html>" + "Caught a cold, but I'm coming in tomorrow."},
        };

        String[] columnNames = {"Name", "Note"};
        illTodayList = new JTable(employeeData, columnNames);

        //TO SET TEXT TO TOP OF CELL
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setVerticalAlignment( JLabel.TOP );
        illTodayList.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );

        illTodayList.setFont(new Font("P", Font.PLAIN, 20));
        illTodayList.setRowHeight(110);
        int finalIllPerson2Index = illPerson2Index; //Had to convert for some reason
        illTodayList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                int row = target.getSelectedRow();
                if (row == 0) {
                    openSpecificEmployee(employees.get(illPerson1Index).getName(),
                            employees.get(illPerson1Index).getPosition(),
                            employees.get(illPerson1Index).getAge(),
                            employees.get(illPerson1Index).getSickDays(),
                            timeFrameList.getSelectedIndex(),
                            employees.get(illPerson1Index).getHiringDate());
                } else {
                    openSpecificEmployee(employees.get(finalIllPerson2Index).getName(),
                            employees.get(finalIllPerson2Index).getPosition(),
                            employees.get(finalIllPerson2Index).getAge(),
                            employees.get(finalIllPerson2Index).getSickDays(),
                            timeFrameList.getSelectedIndex(),
                            employees.get(finalIllPerson2Index).getHiringDate());
                }
                System.out.println("Pressed ill today at index: "+row);
            }
        });
        illTodayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        illTodayListScrollPane = new JScrollPane(illTodayList);
    }

    private void setToHomeButton() {
        sideBarMenu.remove(returnButton);
        sideBarMenu.add(homeButton);
    }
}
