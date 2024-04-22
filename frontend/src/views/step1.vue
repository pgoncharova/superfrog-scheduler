<template>
  <div class="date-time-picker">
    <div class="date-picker">
      <label>Select date</label>
      <VueDatePicker
        :enable-time-picker="false"
        v-model="eventInfo.selectedDate"
        :format="formatDate"
        @closed="updateDate"
        class="custom-date-picker"
      />
    </div>
    <div class="time-picker">
      <div class="start-time">
        <label>Select starting time</label>
        <VueDatePicker
          v-model="startTime"
          time-picker
          :format="formatS"
          @closed="updateStart"
          :is-24="false"
          class="custom-time-picker"
        />
      </div>
      <div class="end-time">
        <label>Select ending time</label>
        <VueDatePicker
          v-model="endTime"
          time-picker
          :format="formatE"
          @closed="updateEnd"
          :is-24="false"
          class="custom-time-picker"
        />
      </div>
    </div>
  </div>
</template>
<script>
import VueDatePicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";
import { format } from "date-fns";
import { ref } from "vue";
export default {
  name: "DateTimePage",
  data() {
    return {
      startTime: ref(new Date()),
      endTime: ref(new Date()),
      scheduleDate: ref(new Date()),
    };
  },
  computed: {},
  props: { eventInfo: { type: Object } },
  components: { VueDatePicker },
  methods: {
    updateParent() {
      this.$emit("update", { eventInfo: this.eventInfo });
    },
    updateStart() {
      this.eventInfo.startTime = this.startTime;
      this.updateParent();
    },
    updateEnd() {
      this.eventInfo.endTime = this.endTime;
      this.updateParent();
    },
    updateDate() {
      this.eventInfo.scheduleDate = this.scheduleDate;
      this.updateParent();
    },
    formatS(date) {
      this.eventInfo.startTime = format(date, "hh:mm:ss");
      return format(date, "hh:mm:ss");
    },
    formatE(date) {
      this.eventInfo.endTime = format(date, "hh:mm:ss");
      return format(date, "hh:mm:ss ");
    },
    formatDate(date) {
      this.eventInfo.scheduleDate = format(date, "yyyy-MM-dd");
      return format(date, "yyyy-MM-dd");
    },
  },
};
</script>
<style scoped>
.date-time-picker {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  color: white;
  text-align: center;
  border-radius: 8px;
}
.date-picker,
.time-picker {
  margin-bottom: 20px;
}
.date-picker label,
.start-time label,
.end-time label {
  font-weight: bold;
  margin-bottom: 5px;
}
.custom-date-picker,
.custom-time-picker {
  width: 100%;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 8px;
  font-size: 14px;
}
.time-picker {
  display: flex;
  justify-content: space-between;
}
.start-time,
.end-time {
  flex: 1;
  margin-right: 10px;
}
.start-time:last-child,
.end-time:last-child {
  margin-right: 0;
}
</style>
