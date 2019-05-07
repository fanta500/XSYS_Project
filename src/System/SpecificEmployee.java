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
    private JTextArea employeeDetailsText;

    public SpecificEmployee() {
        setupGUI();
    }

    private void setupGUI() {
        initPanels();
        employeeDetailsText = new JTextArea(); //Text area for employee details
        employeeDetailsText.setFont(new Font("P", Font.PLAIN, 24)); //Setting font size
        employeeDetailsText.setEditable(false); //Making details non-editable



        addComponents();
    }

    private void addComponents() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints(); //making GridBagLayoutConstraints to set position of elements within
        gridBagConstraints.insets = new Insets(0,5,0,0);
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        try {
            BufferedImage image = ImageIO.read(new File("D:/coding_stuff/XSYS_Project/src/resources/profile_picture.png"));
            JLabel label = new JLabel(new ImageIcon(image));
            employeeDetailsPanelNested.add(label, gridBagConstraints);
        } catch (IOException e) {
            System.out.println("Image could not be found");
        }
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        employeeDetailsPanel.add(employeeDetailsPanelNested, BorderLayout.WEST); //adding final panel to GUI
        specificEmployeePanel.add(employeeDetailsPanel, BorderLayout.NORTH); //adding employee details panel
        employeeDetailsPanelNested.add(employeeDetailsText, gridBagConstraints);
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

        employeeDetailsText.setText(name + "\n" + age + " years old \n" + position);
    }

    public JPanel getJPanel() {
        return specificEmployeePanel;
    }
}
