package cz.fi.muni.pa165.entity;


import javax.persistence.*;

/**
 * @author Jozef Krcho
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String surname;

    /**
     * Initialize new Employee object. For serialization uses.
     */
    public Employee() {
    }

    /**
     * @param name    name of the Employee
     * @param surname surname of the Employee
     */
    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (!(o instanceof Employee))) return false;

        Employee employee = (Employee) o;

        if (name != null ? !name.equals(employee.getName()) : employee.getName() != null) return false;
        return surname != null ? surname.equals(employee.getSurname()) : employee.getSurname() == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
