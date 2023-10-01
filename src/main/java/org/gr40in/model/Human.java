package org.gr40in.model;

import java.time.LocalDate;
import java.util.Objects;

public class Human {
    private String lastName;
    private String firstName;
    private String patronymic;
    private LocalDate birthDate;
    private Long phoneNumber;
    private Sex sex;

    public Human(String lastName, String firstName, String patronymic, LocalDate birthDate, Long phoneNumber, Sex sex) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Human{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber=" + phoneNumber +
                ", sex=" + sex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(lastName, human.lastName)
                && Objects.equals(firstName, human.firstName)
                && Objects.equals(patronymic, human.patronymic)
                && Objects.equals(birthDate, human.birthDate)
                && Objects.equals(phoneNumber, human.phoneNumber)
                && sex == human.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, patronymic, birthDate, phoneNumber, sex);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
