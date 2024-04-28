<template>
    <div class="submitbox">
      <h1>Modify a Request</h1>
      <div class="buttoncontainer">
        <label for="confirmation-code">Please enter the confirmation code</label>
        <input type="text" id="confirmation-code" v-model="requestId" />
        <br />
        <button @click="fetchRequestId">Submit</button>
      </div>
      <p v-if="requestId">Request ID: {{ requestId }}</p>
      <p v-if="errorMessage" style="color:red">{{ errorMessage }}</p>
    </div>
  </template>
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        confirmationCode: '',
        requestId: '',
        errorMessage: '',
      };
    },
    methods: {
      async fetchRequestId() {
        try {
          const response = await axios.get(`http://localhost:8080/api/requests/${this.requestId}`);
          this.requestId = response.data.id;
          this.errorMessage = '';
        } catch (error) {
          console.error('Error fetching request ID:', error);
          this.requestId = '';
          this.errorMessage = 'Failed to fetch request ID. Please check the confirmation code.';
        }
      },
    },
  };
  </script>
  <style lang="scss" scoped>
  .submitbox {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
  }
  
  h1 {
    margin-top: 0;
    margin-bottom: 20px;
    color: white;
  }
  
  .buttoncontainer {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 20px;
    background-color: white;
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  label {
    margin-bottom: 10px;
    color: black;
  }
  
  input {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
  }
  
  button {
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  
  p {
    margin-top: 20px;
    color: #333;
  }
  </style>