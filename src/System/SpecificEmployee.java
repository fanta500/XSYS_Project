package System;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class SpecificEmployee {
    private String name;
    private String position;
    private int age;
    private ArrayList<Integer> sickDays;

    //GUI
    private JPanel specificEmployeePanel; //primary panel to hold other panels
    private JPanel specificEmployeeCenterPanel; // Panel to go into center of outer most panel
    private JPanel specificEmployeeCenterNorthPanel; // Panel to go into north of 2nd outer most panel
    private JPanel specificEmployeeCenterNorthWestPanel; // Panel to go into west of 3rd outer most panel

    private JPanel employeeDetailsPanel; // Panel to go into north of outer most panel
    private JPanel employeeDetailsPanelNested; // Panel to go into west of 2nd outer most panel

    private JTextArea employeeNameText;
    private JTextArea employeeAgeText;
    private JTextArea employeePositionText;

    private JTextArea employeeHiringDateText;
    private JTextArea illnessInPastYearText;
    private JTextArea illnessInTimeFrameText;

    public SpecificEmployee() {
        setupGUI();
    }

    private void setupGUI() {
        initPanels();
        setupEmployeeText();
        addComponents();
    }

    private void setupEmployeeText() {
        employeeAgeText = new JTextArea(); //Text area for employee age
        employeeNameText = new JTextArea(); //Text area for employee name
        employeePositionText = new JTextArea(); //Text area for employee position
        employeeAgeText.setFont(new Font("P", Font.PLAIN, 24)); //Setting font size
        employeeNameText.setFont(new Font("P", Font.PLAIN, 54)); //Setting font size
        employeePositionText.setFont(new Font("P", Font.PLAIN, 24)); //Setting font size
        employeeAgeText.setEditable(false); //Making details non-editable
        employeeNameText.setEditable(false); //Making details non-editable
        employeePositionText.setEditable(false); //Making details non-editable
        employeeAgeText.setOpaque(false); //Remove the white backdrop
        employeeNameText.setOpaque(false); //Remove the white backdrop
        employeePositionText.setOpaque(false); //Remove the white backdrop

        employeeHiringDateText = new JTextArea(); //Text for employee hiring date
        illnessInPastYearText = new JTextArea(); //Text for employee illness in past year
        illnessInTimeFrameText = new JTextArea(); //Text for employee illness in time frame setting
        employeeHiringDateText.setFont(new Font("P", Font.PLAIN, 20)); //Setting font size
        illnessInPastYearText.setFont(new Font("P", Font.PLAIN, 20)); //Setting font size
        illnessInTimeFrameText.setFont(new Font("P", Font.PLAIN, 20)); //Setting font size
        employeeHiringDateText.setEditable(false); //Making details non-editable
        illnessInPastYearText.setEditable(false); //Making details non-editable
        illnessInTimeFrameText.setEditable(false); //Making details non-editable
        employeeHiringDateText.setOpaque(false); //Remove the white backdrop
        illnessInPastYearText.setOpaque(false); //Remove the white backdrop
        illnessInTimeFrameText.setOpaque(false); //Remove the white backdrop
    }

    private void addComponents() {
        setupEmployeeTextAreas();

        employeeDetailsPanel.add(employeeDetailsPanelNested, BorderLayout.WEST); //adding final panel to GUI
        specificEmployeePanel.add(employeeDetailsPanel, BorderLayout.NORTH); //adding employee details panel

        specificEmployeeCenterNorthPanel.add(specificEmployeeCenterNorthWestPanel, BorderLayout.WEST);
        specificEmployeeCenterPanel.add(specificEmployeeCenterNorthPanel, BorderLayout.NORTH);
        specificEmployeePanel.add(specificEmployeeCenterPanel, BorderLayout.CENTER);
    }

    private void setupEmployeeTextAreas() {
        //THIS IS FOR THE TOP OF THE SCREEN (IMAGE; NAME; AGE; POSITION)
        GridBagConstraints gridBagConstraints = new GridBagConstraints(); //making GridBagLayoutConstraints to set position of elements within
        gridBagConstraints.insets = new Insets(15,15,0,0);
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        try {
            BufferedImage image = ImageIO.read(new File("D:/coding_stuff/XSYS_Project/src/resources/profile_picture_small.png"));
            JLabel label = new JLabel(new ImageIcon(image));
            employeeDetailsPanelNested.add(label, gridBagConstraints);
        } catch (IOException e) {
            System.out.println("Image could not be found");
        }
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        employeeDetailsPanelNested.add(employeeNameText, gridBagConstraints);
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        employeeDetailsPanelNested.add(employeeAgeText, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        employeeDetailsPanelNested.add(employeePositionText, gridBagConstraints);

        //THIS IF FOR THE 3 ROW 2 COLUMN TEXT AREA RIGHT BELOW
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        specificEmployeeCenterNorthWestPanel.add(employeeHiringDateText, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        specificEmployeeCenterNorthWestPanel.add(illnessInPastYearText, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        specificEmployeeCenterNorthWestPanel.add(illnessInTimeFrameText, gridBagConstraints);
    }

    private void initPanels() {
        specificEmployeePanel = new JPanel(new BorderLayout()); //Main panel for specific employee
        specificEmployeeCenterPanel = new JPanel(new BorderLayout()); //Panel to contain other panels for graphs
        specificEmployeeCenterNorthPanel = new JPanel(new BorderLayout()); //Panel to sit in north section of center
        specificEmployeeCenterNorthWestPanel = new JPanel(new GridBagLayout()); //Panel to hold 3 rows and 2 columns, top left of center
        employeeDetailsPanel = new JPanel(new BorderLayout()); //Panel for employee details (Will contain another panel for layout)
        employeeDetailsPanelNested = new JPanel(new GridBagLayout()); //Final panel for employee details
    }

    public void setEmployeeDetails(String name, String position, int age, ArrayList<Integer> sickDays, int timeFrameIndex, String hiringDate) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.sickDays = sickDays;
        employeeNameText.setText(name);
        employeePositionText.setText(position);
        employeeAgeText.setText(age + " years old");

        employeeHiringDateText.setText("Hiring date: "+hiringDate);
        illnessInTimeFrameText.setText("Illness in time frame: "+sickDays.get(timeFrameIndex)+" days");
        illnessInPastYearText.setText("Illness in past year: "+sickDays.get(5)+" days");
    }

    public void setIllnessInTimeFrameText(int index) {
        illnessInTimeFrameText.setText("Illness in time frame: "+sickDays.get(index)+" days");
    }

    /*
    private MyRectangle testForGraph = new MyRectangle(0,0, 500,25,Color.cyan);
    private MyRectangle anotherGraph = new MyRectangle(500,0, 25, 300,Color.BLACK);
    private Rectangle testTest = new Rectangle(500,0,25,300);

    public void drawGraph() {
        specificEmployeeCenterPanel.add(testForGraph, BorderLayout.CENTER);
        specificEmployeeCenterPanel.add(testTest, BorderLayout.CENTER);

    }
    */


    public JPanel getJPanel() {
        return specificEmployeePanel;
    }


}
