package com.excel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
    public User(String firstName, String lastName, String emailAddress, Long mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "first name required")
    private String firstName;

    @NotBlank(message = "lastName name required")
    private String lastName;

    @NotBlank(message = "emailAddress name required")
    private String emailAddress;

    @NotBlank(message = "mobileNumber name required")
    private Long mobileNumber;

    private String reason;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}
