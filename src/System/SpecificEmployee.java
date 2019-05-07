package System;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SpecificEmployee {
    private String name;
    private String position;
    private int age;
    private ArrayList<Integer> sickDays;

    //GUI
    private JPanel specificEmployeePanel;
    private JPanel employeeDetailsPanel;
    private JPanel employeeDetailsPanelNested;
    private JTextArea employeeNameText;
    private JTextArea employeeAgeText;
    private JTextArea employeePositionText;

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
    }

    private void addComponents() {
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

        employeeDetailsPanel.add(employeeDetailsPanelNested, BorderLayout.WEST); //adding final panel to GUI
        specificEmployeePanel.add(employeeDetailsPanel, BorderLayout.NORTH); //adding employee details panel
    }

    private void initPanels() {
        specificEmployeePanel = new JPanel(new BorderLayout()); //Main panel for specific employee
        employeeDetailsPanel = new JPanel(new BorderLayout()); //Panel for employee details (Will contain another panel for layout)
        employeeDetailsPanelNested = new JPanel(new GridBagLayout()); //Final panel for employee details
    }

    public void setEmployeeDetails(String name, String position, int age, ArrayList<Integer> sickDays) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.sickDays = sickDays;
        employeeNameText.setText(name);
        employeePositionText.setText(position);
        employeeAgeText.setText(age + " years old");
    }

    public JPanel getJPanel() {
        return specificEmployeePanel;
    }
}
