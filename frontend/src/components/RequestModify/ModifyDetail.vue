<template>
    <div class="submitbox">
      <h1>Modify a Request</h1>
      <div class="buttoncontainer">
        <label for="firstName">First Name</label>
        <input type="text" id="firstName" v-model="requestData.firstName" />
  
        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" v-model="requestData.lastName" />
  
        <label for="phoneNumber">Phone Number</label>
        <input type="text" id="phoneNumber" v-model="requestData.phoneNumber" />
  
        <label for="email">Email</label>
        <input type="email" id="email" v-model="requestData.email" />
  
        <label for="eventType">Event Type</label>
        <input type="text" id="eventType" v-model="requestData.eventType" />
  
        <label for="eventTitle">Event Title</label>
        <input type="text" id="eventTitle" v-model="requestData.eventTitle" />
  
        <label for="organizationName">Organization Name</label>
        <input type="text" id="organizationName" v-model="requestData.organizationName" />
  
        <label for="eventAddress">Event Address</label>
        <input type="text" id="eventAddress" v-model="requestData.eventAddress" />
  
        <label for="isOnCampus">Is on Campus?</label>
        <input type="checkbox" id="isOnCampus" v-model="requestData.isOnCampus" />
  
        <label for="specialInstructions">Special Instructions</label>
        <textarea id="specialInstructions" v-model="requestData.specialInstructions"></textarea>
  
        <label for="benefitsDescription">Benefits Description</label>
        <textarea id="benefitsDescription" v-model="requestData.benefitsDescription"></textarea>
  
        <label for="sponsorDescription">Sponsor Description</label>
        <textarea id="sponsorDescription" v-model="requestData.sponsorDescription"></textarea>
  
        <label for="detailedDescription">Detailed Description</label>
        <textarea id="detailedDescription" v-model="requestData.detailedDescription"></textarea>
  
        <br />
        <button @click="updateRequest">Update</button>
        <button @click="cancelRequest">Cancel</button>
        <button @click="goHome">Home</button>
      </div>
      <p v-if="errorMessage" style="color: red">{{ errorMessage }}</p>
    </div>
  </template>
  

  <script>
  import axios from 'axios';
  import router from "../../router/index";
  
  export default {
    data() {
      return {
        requestId: "",
        requestData: {}, // Object to store the fetched request data
        errorMessage: "",
      };
    },
    methods: {
      goHome() {
      router.push("/");
      },
      async fetchRequestData() {
        try {
          const response = await axios.get(`https://superfrog-container-backend.nicerock-3516d100.eastus.azurecontainerapps.io/api/requests/${this.requestId}`);
          this.requestData = response.data.data; // Assuming the backend returns data in this structure
        } catch (error) {
          console.error("Error fetching request data:", error);
          this.errorMessage = "Failed to load request data. Please try again later.";
        }
      },
      async updateRequest() {
        try {
          const response = await axios.put(`https://superfrog-container-backend.nicerock-3516d100.eastus.azurecontainerapps.io/api/requests/${this.requestId}`, this.requestData);
          alert("Request updated successfully!");
        } catch (error) {
          console.error("Error updating request:", error);
          this.errorMessage = "Failed to update request. Please try again later.";
        }
      },
      async cancelRequest(){
        try {
          await axios.delete(`https://superfrog-container-backend.nicerock-3516d100.eastus.azurecontainerapps.io/api/requests/${this.requestId}`);
          alert("Request cancel successfully!");
        } catch (error) {
          console.error("Error canceling request:", error);
          this.errorMessage = "Failed to cancel request. Please try again later.";
        }
      },
    },
    mounted() {
      this.requestId = this.$route.params.requestId;
      this.fetchRequestData();
    }
  };
  </script>
  

<style scoped>
.event-form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
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
  margin-bottom: 0.5rem;
}

input[type="text"],
input[type="email"],
textarea {
  align-self: center;
  width: 50%;
  padding: 0.5rem;
  border-radius: 4px;
  border: 1px solid #ddd;
}

button {
  padding: 0.5rem 1rem;
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
