import java.util.Objects;

public final class Employee {
    private String namn;
    private int salary;
    private Employee manager;

    public Employee(String namn, int salary, Employee manager) {
        this.namn = Objects.requireNonNull(namn);
        this.salary = salary;
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && Objects.equals(namn, employee.namn) && Objects.equals(manager, employee.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namn, salary, manager);
    }

    public String getNamn() {
        return namn;
    }

    public int getSalary() {
        return salary;
    }

    public Employee getManager() {
        return manager;
    }
}
