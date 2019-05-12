package System;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SpecificEmployee {
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

    private JLabel lowerGraph;
    private JLabel upperGraph;

    private JTable illnessNotesTable;
    private JScrollPane illnessNotesScrollPane;

    public SpecificEmployee() {
        setupGUI();
    }

    private void setupGUI() {
        initPanels();
        setupEmployeeText();
        setupIllnessNotes();
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

        //ADDING PANEL TO CENTER PANEL WEST FOR LOWER GRAPH
        JPanel specificEmployeeCenterWestPanel = new JPanel(new GridBagLayout());
        specificEmployeeCenterPanel.add(specificEmployeeCenterWestPanel, BorderLayout.WEST);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0,15,0,0);
        specificEmployeeCenterWestPanel.add(lowerGraph, gridBagConstraints);
        specificEmployeePanel.add(illnessNotesScrollPane, BorderLayout.EAST);

        //ADDING upperGraph TO specificEmployeeCenterNorthWestPanel
        gridBagConstraints.gridx = 2;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        specificEmployeeCenterNorthWestPanel.add(upperGraph, gridBagConstraints);
    }

    private void setupEmployeeTextAreas() {
        //THIS IS FOR THE TOP OF THE SCREEN (IMAGE; NAME; AGE; POSITION)
        GridBagConstraints gridBagConstraints = new GridBagConstraints(); //making GridBagLayoutConstraints to set position of elements within
        gridBagConstraints.insets = new Insets(15,15,0,0);
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        try {
            BufferedImage image = ImageIO.read(new File("src/resources/profile_picture_small.png"));
            JLabel label = new JLabel(new ImageIcon(image));
            employeeDetailsPanelNested.add(label, gridBagConstraints);
        } catch (IOException e) {
            System.out.println("Image could not be found");
        }
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        employeeDetailsPanelNested.add(employeeNameText, gridBagConstraints);
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        employeeDetailsPanelNested.add(employeeAgeText, gridBagConstraints);
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        employeeDetailsPanelNested.add(employeePositionText, gridBagConstraints);

        //THIS IF FOR THE 3 ROW 2 COLUMN TEXT AREA RIGHT BELOW
        gridBagConstraints.insets = new Insets(15,15,0,15);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        specificEmployeeCenterNorthWestPanel.add(employeeHiringDateText, gridBagConstraints);
        //gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(75,15,10,15);
        specificEmployeeCenterNorthWestPanel.add(illnessInPastYearText, gridBagConstraints);
        //gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(135,15,10,15);
        specificEmployeeCenterNorthWestPanel.add(illnessInTimeFrameText, gridBagConstraints);

    }

    private void initPanels() {
        specificEmployeePanel = new JPanel(new BorderLayout()); //Main panel for specific employee
        specificEmployeeCenterPanel = new JPanel(new BorderLayout()); //Panel to contain other panels for graphs
        specificEmployeeCenterNorthPanel = new JPanel(new BorderLayout()); //Panel to sit in north section of center
        specificEmployeeCenterNorthWestPanel = new JPanel(new GridBagLayout()); //Panel to hold 3 rows and 2 columns, top left of center
        employeeDetailsPanel = new JPanel(new BorderLayout()); //Panel for employee details (Will contain another panel for layout)
        employeeDetailsPanelNested = new JPanel(new GridBagLayout()); //Final panel for employee details
        lowerGraph = new JLabel();
        upperGraph = new JLabel();
        illnessNotesTable = new JTable();
        illnessNotesScrollPane = new JScrollPane(illnessNotesTable);
    }

    public void setEmployeeDetails(String name, String position, int age, ArrayList<Integer> sickDays, int timeFrameIndex, String hiringDate) {
        this.sickDays = sickDays;
        employeeNameText.setText(name);
        employeePositionText.setText(position);
        employeeAgeText.setText(age + " years old");

        employeeHiringDateText.setText("Hiring date:          "+hiringDate);
        illnessInTimeFrameText.setText("Illness in time frame: "+sickDays.get(timeFrameIndex)+" days");
        illnessInPastYearText.setText("Illness in past year:   "+sickDays.get(5)+" days");
    }

    public void setIllnessInTimeFrameText(int index) {
        illnessInTimeFrameText.setText("Illness in time frame: "+sickDays.get(index)+" days");
    }

    public void drawGraph(int index) {
        try {
            BufferedImage imageLower = null;
            BufferedImage imageUpper = null;
            if (index==0) {
                imageLower = ImageIO.read(new File("src/resources/specific_employee_week_1.png"));
                imageUpper = ImageIO.read(new File("src/resources/specific_employee_pattern_week.png"));
            } else if (index==1) {
                imageLower = ImageIO.read(new File("src/resources/specific_employee_2weeks_1.png"));
                imageUpper = ImageIO.read(new File("src/resources/specific_employee_pattern_2weeks.png"));
            } else if (index==2) {
                imageLower = ImageIO.read(new File("src/resources/specific_employee_month.png"));
                imageUpper = ImageIO.read(new File("src/resources/specific_employee_pattern_month.png"));
            } else if (index==3) {
                imageLower = ImageIO.read(new File("src/resources/specific_employee_3months.png"));
                imageUpper = ImageIO.read(new File("src/resources/specific_employee_pattern_3months.png"));
            } else if (index==4) {
                imageLower = ImageIO.read(new File("src/resources/specific_employee_6months.png"));
                imageUpper = ImageIO.read(new File("src/resources/specific_employee_pattern_6months.png"));
            } else if (index==5) {
                imageLower = ImageIO.read(new File("src/resources/specific_employee_year.png"));
                imageUpper = ImageIO.read(new File("src/resources/specific_employee_pattern_year.png"));
            } else if (index==6) {
                imageLower = ImageIO.read(new File("src/resources/specific_employee_allTime.png"));
                imageUpper = ImageIO.read(new File("src/resources/specific_employee_pattern_allTime.png"));
            }
            lowerGraph.setIcon(new ImageIcon(imageLower));
            upperGraph.setIcon(new ImageIcon(imageUpper));

        } catch (IOException e) {
            System.out.println("Image could not be found");
        }
    }

    private void setupIllnessNotes() {
        String[][] illnessData = {
                {"16/02/2019", "<html>" + "Almost feeling well enough. Coming in tomorrow."},
                {"01/03/2019", "<html>" + "Feeling ill."},
                {"11/03/2019", "<html>" + "Caught a cold, but I'm coming in tomorrow."},
                {"18/03/2019", "<html>" + "Feeling ill."},
                {"22/03/2019", "<html>" + "I have an appointment with my doctor."},
                {"25/04/2019", "<html>" + "Not feeling well."},
                {"04/05/2019", "<html>" + "Visiting my mother who is ill. My father is on a business trip, so I have to take care of her today"},
        };

        String[] columnNames = {"Date", "Note"};
        illnessNotesTable = new JTable(illnessData, columnNames);
        illnessNotesTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        illnessNotesTable.setRowSelectionAllowed(false);
        illnessNotesTable.setColumnSelectionAllowed(false);

        //TO SET TEXT TO TOP OF CELL
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setVerticalAlignment( JLabel.TOP );
        illnessNotesTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        //SET FONT
        illnessNotesTable.setFont(new Font("P", Font.PLAIN, 20));
        //SET ROW HEIGHT
        illnessNotesTable.setRowHeight(110);

        illnessNotesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        illnessNotesScrollPane = new JScrollPane(illnessNotesTable);
    }

    public JPanel getJPanel() {
        return specificEmployeePanel;
    }
}
