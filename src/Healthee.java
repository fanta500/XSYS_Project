import javax.swing.*;
import java.awt.*;

public class Healthee {

    //MODULES
    private Settings settings;
    private SpecificEmployee specificEmployee;
    private Timeframe timeframe;
    private TotalIllnessHistory totalIllnessHistory;

    //GUI ELEMENTS
    private JFrame window;
    private LayoutManager layout;


    private Healthee() {
        settings = new Settings();
        specificEmployee = new SpecificEmployee();
        timeframe = new Timeframe();
        totalIllnessHistory = new TotalIllnessHistory();

        window = new JFrame("Healthee");
        layout = new GridLayout();
    }

    public static void main(String args[]) {
        Healthee program = new Healthee();
        program.show();
    }

    public void show() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1000,800);
        window.setLayout(layout);
        window.setVisible(true);
    }
}
