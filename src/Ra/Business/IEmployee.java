package Ra.Business;

import Ra.BusinessImp.Employee;

import java.util.Scanner;

public interface IEmployee {
    float BASIC_SALARY = 1300000;

    public void inputData(Scanner scanner);

    void displayData();
    int compareTo(Employee emp);
}
