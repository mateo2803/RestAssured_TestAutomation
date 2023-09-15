package entities;

import lombok.Getter;
import lombok.Setter;

public class Employee {

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String salary;
    @Getter @Setter
    private String age;
    @Getter @Setter
    private String profileImage;

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setProfileImage(String path) {
        this.profileImage = profileImage;
    }
    public String getProfileImage() {
        return profileImage;
    }
}
