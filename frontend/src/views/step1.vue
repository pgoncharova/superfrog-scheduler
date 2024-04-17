<template>
    <div class="date-time-picker">
      <div class="form-group">
        <label for="select-date">Select date  </label>
        <input type="date" v-model="selectedDate" id="select-date">
      </div>
  
      <div class="form-group">
        <label for="start-time">Select starting time  </label>
        <input type="time" v-model="startTime" id="start-time" format="HH:mm:ss">
      </div>
  
      <div class="form-group">
        <label for="end-time">Select ending time  </label>
        <input type="time" v-model="endTime" id="end-time" format="HH:mm:ss">
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        selectedDate: new Date(), // default to today, but you can make it null or a specific date
        startTime: '08:00:00',
        endTime: '09:00:00',
      };
    },
  };
  </script>
  
  <style scoped>
  .date-time-picker {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    color: white;
  }
  
  .form-group {
    margin-bottom: 1rem;
  }

  </style>
  