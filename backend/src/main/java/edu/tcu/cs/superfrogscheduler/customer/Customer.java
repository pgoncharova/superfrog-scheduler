package edu.tcu.cs.superfrogscheduler.customer;

import edu.tcu.cs.superfrogscheduler.request.Request;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "Phone number is invalid")
    private String phoneNumber;

    @OneToMany(cascade ={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "owner")
    private List<Request> requests = new ArrayList<>();

    public Customer() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public void addRequest(Request request) {
        request.setOwner(this);
        this.requests.add(request);
    }

    public Integer getNumberOfRequests() {
        return this.requests.size();
    }

    public void removeAllRequests() {
        this.requests.stream().forEach(request -> request.setOwner(null));
        this.requests = new ArrayList<>();
    }

    public void removeRequest(Request requestToBeAssigned) {
        // Remove artifact owner.
        requestToBeAssigned.setOwner(null);
        this.requests.remove(requestToBeAssigned);
    }
}
