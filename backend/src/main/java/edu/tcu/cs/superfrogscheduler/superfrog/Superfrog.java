package edu.tcu.cs.superfrogscheduler.superfrog;


import edu.tcu.cs.superfrogscheduler.request.Request;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Superfrog implements Serializable {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String physicalAddress;

    @Id
    private String email;

    private boolean enabled;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "superfrog")
    private List<Request> requests = new ArrayList<>();

    public Superfrog() {

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public void addRequest(Request request) {
        request.setSuperfrog(this);
        this.requests.add(request);
    }

    public void removeRequest(Request request) {
        request.setSuperfrog(null);
        this.requests.remove(request);
    }

    public void removeAllRequests() {
        this.requests.stream().forEach(request -> request.setSuperfrog(null));
        this.requests = null;
    }
}
