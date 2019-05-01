package data;

public class Employee {
    private int age;
    private String name;
    private int sickDaysInTimeFrame;
    private String position;

    public Employee(int age, String name, int sickDaysInTimeFrame, String position ) {
        this.age = age;
        this.name = name;
        this.sickDaysInTimeFrame = sickDaysInTimeFrame;
        this.position = position;
    }
}
