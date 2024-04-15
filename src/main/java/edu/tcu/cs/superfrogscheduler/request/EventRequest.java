package edu.tcu.cs.superfrogscheduler.request;

import jakarta.persistence.*;
import edu.tcu.cs.superfrogscheduler.customer.Customer;
import jakarta.validation.constraints.NotBlank;

@Entity
public class EventRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String specialInstructions;
    private String eventDetails;

    @NotBlank(message = "Event type is required")
    private String eventType;

    @NotBlank(message = "Event title is required")
    private String eventTitle;

    // Relationship to Customer
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    // Status field to indicate the state of the request
    private String status = "Pending";

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    // Getter and setter for eventDetails
    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }
}
