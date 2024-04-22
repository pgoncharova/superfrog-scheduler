<template>
  <div>
    <span class="date">
      <div class="title">Review Your Order</div>
      <table>
        <tr>
          <td>Date</td>
          <td>{{eventInfo.selectedDate}}</td>
        </tr>
        <tr>
          <td>Start Time</td>
          <td>{{eventInfo.startTime}}</td>
        </tr>
        <tr>
          <td>End Time</td>
          <td>{{eventInfo.endTime}}</td>
        </tr>
      </table>
    </span>
    <span class="contact-info-1">
      <div class="title">Personal Information</div>
      <table>
        <tr>
          <td>Name</td>
          <td>{{eventInfo.contactFirstName + " " + eventInfo.contactLastName}}</td>
        </tr>
        <tr>
          <td>Email</td>
          <td>{{eventInfo.email}}</td>
        </tr>
        <tr>
          <td>Phone Number</td>
          <td>{{eventInfo.phoneNumber}}</td>
        </tr>
      </table>
    </span>
    <span class="event-info-1">
      <div class="title">Event Information</div>
      <table>
        <tr>
          <td>Address of Appearance</td>
          <td>{{eventInfo.address}}</td>
        </tr>
        <tr>
          <td>Name of Organization</td>
          <td>{{eventInfo.organizationName}}</td>
        </tr>
        <tr>
          <td>Event Type</td>
          <td>{{eventInfo.eventType}}</td>
        </tr>
        <tr>
          <td>Event Description</td>
          <td>{{eventInfo.eventDescription}}</td>
        </tr>
        <tr>
          <td>Special Instructions</td>
          <td>{{eventInfo.specialInstruction}}</td>
        </tr>
        <tr>
          <td>Outside Organizations</td>
          <td>{{eventInfo.outsideOrg}}</td>
        </tr>
      </table>
    </span>
  </div>
</template>

<script>
export default {
  name: 'ReviewSubPage',
  props: {
    eventInfo: {
      type: Object
    }
  }
}
</script>

<style scoped>
.contact-info-1{
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  align-items: center;
}
table {
  margin-bottom: 1%;
}

td {
  border-color: lightgray;
  background-color: white;
  color: grey;
  font-size: 1rem;
  text-align: center;
  padding: 2.5px;
}
.title {
  font-size: 2rem;
  margin: 1%;
  text-align: center;
  color: white;
}
.event-info-1 table > tr > td:nth-child(2) {
  width:20vw;
}

.contact-info-1 table > tr > td:nth-child(2) {
  width:15vw;
}

.date table > tr > td:nth-child(2) {
  width:15vw;
}
.event-info-1 {
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  align-items: center;
}

.date {
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  align-items: center;
}

</style>
