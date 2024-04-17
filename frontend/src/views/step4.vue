<template>
    <div class="step4-container">
      <h2>Review Your Information</h2>
      <div class="review-section">
        <div>
          <strong>Date:</strong> {{ formData.selectedDate }}
        </div>
        <div>
          <strong>Start Time:</strong> {{ formData.startTime }}
        </div>
        <div>
          <strong>End Time:</strong> {{ formData.endTime }}
        </div>
        <!-- Display other information from formData -->
        <div>
          <strong>Contact First Name:</strong> {{ formData.contactFirstName }}
        </div>
        <div>
          <strong>Contact Last Name:</strong> {{ formData.contactLastName }}
        </div>
        <div>
          <strong>Email:</strong> {{ formData.email }}
        </div>
        <div>
          <strong>Event Title:</strong> {{ formData.eventTitle }}
        </div>
        <div>
          <strong>Event Description:</strong> {{ formData.eventDescription }}
        </div>
        <!-- ...more fields as needed -->
      </div>
      <button @click="submitForm">Submit</button>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      formData: {
        type: Object,
        default: () => ({}),
      },
    },
    methods: {
      submitForm() {
        // Here you would handle the form submission,
        // like sending the data to a server or emitting an event.
        this.$emit('formSubmitted', this.formData);
        // For example, you could use axios to send the formData to a server:
        // axios.post('/api/submit-form', this.formData)
        //   .then(response => { /* handle success */ })
        //   .catch(error => { /* handle error */ });
      },
    },
  };
  </script>
  
  <style scoped>
  .step4-container {
    display: flex;
    flex-direction: column;
    align-items: start;
    padding: 20px;
    max-width: 600px;
    margin: auto;
  }
  
  .review-section {
    margin-bottom: 20px;
  }
  
  button {
    padding: 10px 20px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #45a049;
  }
  </style>
  