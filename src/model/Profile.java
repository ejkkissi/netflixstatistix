package model;

public class Profile {
    private String name;
    private String accountEmail;
    private int age;

    public Profile() {

    }

    public Profile(String name, String accountEmail, int age) {
        this.name = name;
        this.accountEmail = accountEmail;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
