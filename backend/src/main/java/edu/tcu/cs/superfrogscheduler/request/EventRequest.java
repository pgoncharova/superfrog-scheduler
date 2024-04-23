package edu.tcu.cs.superfrogscheduler.request;

import jakarta.persistence.*;
import edu.tcu.cs.superfrogscheduler.customer.Customer;

import java.io.Serializable;

@Entity
public class EventRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String eventType;

    private String eventTitle;

    private String organizationName;

    private String eventAddress;

    private boolean isOnCampus;

    private EventRequestStatus status;

    private String specialInstructions;

    private String rejectionReason;

    private String benefitsDescription;

    private String sponsorDescription;

    private String detailedDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public boolean isOnCampus() {
        return isOnCampus;
    }

    public void setOnCampus(boolean onCampus) {
        isOnCampus = onCampus;
    }

    public String getBenefitsDescription() {
        return benefitsDescription;
    }

    public void setBenefitsDescription(String benefitsDescription) {
        this.benefitsDescription = benefitsDescription;
    }

    public String getSponsorDescription() {
        return sponsorDescription;
    }

    public void setSponsorDescription(String sponsorDescription) {
        this.sponsorDescription = sponsorDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    // Constructors
    public EventRequest() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public enum EventRequestStatus {
        PENDING,
        APPROVED,
        REJECTED
    }

}


