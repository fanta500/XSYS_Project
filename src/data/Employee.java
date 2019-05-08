package Data;

import java.util.ArrayList;
import java.util.Random;

public class Employee {
    private int age;
    private String name;
    private ArrayList<Integer> sickDays = new ArrayList<>();
    private String position;
    private String hiringDate;

    public Employee(int age, String name, String position ) {
        this.age = age;
        this.name = name;
        this.position = position;
        generateSickDays();
        generateHiringDate();
    }

    private void generateSickDays() {
        Random random = new Random();
        int sickDayCeiling = 5;

        int sickDaysLastWeek = random.nextInt(sickDayCeiling);
        sickDays.add(sickDaysLastWeek);
        sickDayCeiling += 2;

        int sickDaysLast2Weeks = random.nextInt(sickDayCeiling)+sickDaysLastWeek;
        sickDays.add(sickDaysLast2Weeks);
        sickDayCeiling += 2;

        int sickDaysLastMonth = random.nextInt(sickDayCeiling)+sickDaysLast2Weeks;
        sickDays.add(sickDaysLastMonth);
        sickDayCeiling += 5;

        int sickDaysLast3Months = random.nextInt(sickDayCeiling)+sickDaysLastMonth;
        sickDays.add(sickDaysLast3Months);
        sickDayCeiling += 5;

        int sickDaysLast6Months = random.nextInt(sickDayCeiling)+sickDaysLast3Months;
        sickDays.add(sickDaysLast6Months);
        sickDayCeiling += 5;

        int sickDaysLastYear = random.nextInt(sickDayCeiling)+sickDaysLast6Months;
        sickDays.add(sickDaysLastYear);
        sickDayCeiling += 5;

        int sickDaysAllTime = random.nextInt(sickDayCeiling)+sickDaysLastYear;
        sickDays.add(sickDaysAllTime);
    }

    private void generateHiringDate() {
        Random random = new Random();
        String day = Integer.toString(random.nextInt(27)+1);
        if (day.length()==1) {
            day = "0"+day;
        }
        String month = Integer.toString(random.nextInt(11)+1);
        if (month.length()==1) {
            month = "0"+month;
        }
        String year = Integer.toString(random.nextInt(5)+2014);
        hiringDate = day+"/"+month+"/"+year;
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

    public String getHiringDate() { return hiringDate; }
}
