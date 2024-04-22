<template>
  <div class="event-form-container">
    <div class="selected-date-time">
      <p><strong>Date:</strong> {{ eventInfo.selectedDate }}</p>
      <p><strong>Start Time:</strong> {{ eventInfo.startTime }}</p>
      <p><strong>End Time:</strong> {{ eventInfo.endTime }}</p>
    </div>


      <h1>Contact Information</h1>

      <div class="form-group">
        <label for="contact-first-name">Contact First Name</label>
        <input type="text" id="contact-first-name" v-model="eventInfo.contactFirstName" @input="updateParent">
      </div>

      <div class="form-group">
        <label for="contact-last-name">Contact Last Name</label>
        <input type="text" id="contact-last-name" v-model="eventInfo.contactLastName" @input="updateParent">
      </div>

      <div class="form-group">
        <label for="phone-number">Phone Number</label>
        <input type="text" id="phone-number" v-model="eventInfo.phoneNumber" @input="updateParent">
      </div>

      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" v-model="eventInfo.email" @input="updateParent">
      </div>
      <h1>Event Information</h1>
      <!-- Event Information -->
      <div class="form-group">
        <label for="event-title">Event Title</label>
        <input type="text" id="event-title" v-model="eventInfo.eventTitle" @input="updateParent">
      </div>

      <div class="dropdown-box">
      <label for="dropdown">Event Type</label>
        <select id="dropdown" v-model="eventInfo.eventType" @change="updateParent">
          <option value="TCU">TCU</option>
          <option value="PUBLIC">Public/Non-profit</option>
          <option value="PRIVATE">Private/Residential</option>
        </select>
      </div>

      <div class="form-group">
        <label for="oragnization-name">Name of Organization</label>
        <input type="text" id="event-description" v-model="eventInfo.organizationName" @input="updateParent">
      </div>

      <div class="form-group">
        <label for="address">Address of Appearance</label>
        <input type="text" id="address" v-model="eventInfo.address" @input="updateParent">
      </div>

      <div class="form-group">
        <label for="event-description">Event Description</label>
        <textarea id="event-description" v-model="eventInfo.eventDescription" @input="updateParent"></textarea>
      </div>

      <div class="form-group">
        <label for="special-instruction">Special Instructions</label>
        <textarea id="evenspecial-instruction" v-model="eventInfo.specialInstruction" @input="updateParent"></textarea>
      </div>

      <div class="form-group">
        <label for="outside-organization">List any other outside organizations involved in sponsoring the event</label>
        <textarea id="evenspecial-instruction" v-model="eventInfo.outsideOrg" @input="updateParent"></textarea>
      </div>

      <div class="form-group">
        <label for="expense-benefit">Describe any expense or benefits to the spirit team members</label>
        <textarea id="expense-benefit" v-model="eventInfo.expenseBenefit" @input="updateParent"></textarea>
      </div>
  </div>
</template>

<script>
export default {
  props: {
    eventInfo: {
      type: Object
    }
  },
  data() {
    return {
      contactFirstName: this.eventInfo.contactFirstName,
      contactLastName: this.eventInfo.contactLastName,
      email: this.eventInfo.email,
      eventTitle: this.eventInfo.eventTitle,
      eventType: this.eventInfo.eventType,
      eventDescription: this.eventInfo.eventDescription,
      selectedDate: this.eventInfo.selectedDate,
      startTime: this.eventInfo.startTime,
      endTime: this.eventInfo.endTime,
      organizationName: this.eventInfo.organizationName,
      phoneNumber: this.eventInfo.phoneNumber,
      address: this.eventInfo.address,
      specialInstruction: this.eventInfo.specialInstruction,
      outsideOrg: this.eventInfo.outsideOrg,
      expenseBenefit: this.eventInfo.expenseBenefit,
    };
  },

  methods: {
    updateParent() {
      this.$emit('update', {
        eventInfo: this.eventInfo,

      });
    }
  },
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