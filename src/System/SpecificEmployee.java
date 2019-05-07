package System;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpecificEmployee {
    private String name;
    private String position;
    private int age;
    private ArrayList<Integer> sickDays;

    //GUI
    private JPanel specificEmployeePanel;
    private JTextArea employeeDetailsText;


    public SpecificEmployee() {
        setupGUI();
    }

    private void setupGUI() {
        specificEmployeePanel = new JPanel();
        employeeDetailsText = new JTextArea();
        employeeDetailsText.setFont(new Font("P", Font.PLAIN, 22));
        employeeDetailsText.setEditable(false);
        specificEmployeePanel.add(employeeDetailsText, BorderLayout.NORTH);
    }

    public void setEmployeeDetails(String name, String position, int age, ArrayList<Integer> sickDays) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.sickDays = sickDays;

        employeeDetailsText.setText(name+"\n"+
                                         age + " years old\n" + position);
    }

    public JPanel getJPanel() {
        return specificEmployeePanel;
    }
}
