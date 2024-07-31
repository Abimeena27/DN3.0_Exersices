import java.util.Arrays;

public class EMS {
    private Employee[] employees;
    private int count;

    public EmployeeManagementSystem(int size) {
        employees = new Employee[size];
        count = 0;
    }

    
    public void addEmployee(Employee employee) {
        if (count < employees.length) {
            employees[count] = employee;
            count++;
        } else {
            System.out.println("Array is full. Cannot add more employees.");
        }
    }

    
    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null; 
    }

    
    public void traverseEmployees() {
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }

   
    public void deleteEmployee(String employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                // Shift elements to the left to fill the gap
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[count - 1] = null; // nullify the last element
                count--;
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(5);

        
        ems.addEmployee(new Employee("001", "Alice", "Manager", 70000));
        ems.addEmployee(new Employee("002", "Bob", "Developer", 50000));
        ems.addEmployee(new Employee("003", "Charlie", "Designer", 40000));

    
        System.out.println("Employees:");
        ems.traverseEmployees();

        System.out.println("\nSearching for employee with ID 002:");
        Employee emp = ems.searchEmployee("002");
        if (emp != null) {
            System.out.println(emp);
        } else {
            System.out.println("Employee not found.");
        }

        // Deleting an employee
        System.out.println("\nDeleting employee with ID 003:");
        ems.deleteEmployee("003");
        ems.traverseEmployees();
    }
}
