package Data;

import java.util.ArrayList;
import java.util.Random;

public class Employee {
    private int age;
    private String name;
    private ArrayList<Integer> sickDays = new ArrayList<>();
    private String position;

    public Employee(int age, String name, String position ) {
        this.age = age;
        this.name = name;
        this.position = position;
        generateSickDays();
    }

    private void generateSickDays() {
        Random random = new Random();
        int sickDayCeiling = 5;

        int sickDaysLastWeek = random.nextInt(sickDayCeiling);
        sickDays.add(sickDaysLastWeek);
        sickDayCeiling += 5;

        int sickDaysLast2Weeks = random.nextInt(sickDayCeiling);
        sickDays.add(sickDaysLast2Weeks);
        sickDayCeiling += 5;

        int sickDaysLastMonth = random.nextInt(sickDayCeiling);
        sickDays.add(sickDaysLastMonth);
        sickDayCeiling += 5;

        int sickDaysLast3Months = random.nextInt(sickDayCeiling);
        sickDays.add(sickDaysLast3Months);
        sickDayCeiling += 5;

        int sickDaysLast6Months = random.nextInt(sickDayCeiling);
        sickDays.add(sickDaysLast6Months);
        sickDayCeiling += 5;

        int sickDaysLastYear = random.nextInt(sickDayCeiling);
        sickDays.add(sickDaysLastYear);
        sickDayCeiling += 5;

        int sickDaysAllTime = random.nextInt(sickDayCeiling);
        sickDays.add(sickDaysAllTime);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public ArrayList<Integer> getSickDays() {
        return sickDays;
    }
}
