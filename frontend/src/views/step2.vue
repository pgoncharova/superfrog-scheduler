<template>
    <div class="event-form-container">
      <div class="selected-date-time">
        <p><strong>Date:</strong> {{ formatDate(selectedDate) }}</p>
        <p><strong>Start Time:</strong> {{ startTime }}</p>
        <p><strong>End Time:</strong> {{ endTime }}</p>
      </div>
  
      <form @submit.prevent="submitForm">
        <!-- Personal Contact Information -->
        <div class="form-group">
          <label for="contact-first-name">Contact First Name</label>
          <input type="text" id="contact-first-name" v-model="contactFirstName">
        </div>
  
        <div class="form-group">
          <label for="contact-last-name">Contact Last Name</label>
          <input type="text" id="contact-last-name" v-model="contactLastName">
        </div>
  
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" id="email" v-model="email">
        </div>
  
        <!-- Event Information -->
        <div class="form-group">
          <label for="event-title">Event Title</label>
          <input type="text" id="event-title" v-model="eventTitle">
        </div>
  
        <div class="form-group">
          <label for="event-description">Event Description</label>
          <textarea id="event-description" v-model="eventDescription"></textarea>
        </div>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    props: ['selectedDate', 'startTime', 'endTime'],
    data() {
      return {
        contactFirstName: '',
        contactLastName: '',
        email: '',
        eventTitle: '',
        eventDescription: '',
        // Add more fields as needed
      };
    },
    methods: {
      submitForm() {
        // Handle the form submission
        const formData = {
          date: this.formatDate(this.selectedDate),
          startTime: this.startTime,
          endTime: this.endTime,
          contactFirstName: this.contactFirstName,
          contactLastName: this.contactLastName,
          email: this.email,
          eventTitle: this.eventTitle,
          eventDescription: this.eventDescription,
          // Include other data fields here
        };
        console.log('Form submitted with data:', formData);
        // Here you would typically send formData to your backend or store it in your state management
      },
      formatDate(date) {
        return date ? date.toISOString().split('T')[0] : '';
      },
    }
  };
  </script>
  
  <style scoped>
  .event-form-container {
    display: flex;
    flex-direction: column;
    max-width: 500px;
    margin: auto;
    padding: 1rem;
    background-color: #444;
    border-radius: 8px;
    color: white;
  }
  
  .selected-date-time {
    margin-bottom: 1rem;
  }
  
  .form-group {
    margin-bottom: 1rem;
  }
  
  label {
    display: block;
    margin-bottom: .5rem;
  }
  
  input[type=text],
  input[type=email],
  textarea {
    width: 100%;
    padding: .5rem;
    border-radius: 4px;
    border: 1px solid #ddd;
  }
  
  button {
    padding: .5rem 1rem;
    border: none;
    border-radius: 4px;
    background-color: #5cb85c;
    color: white;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #4cae4c;
  }
  </style>
  