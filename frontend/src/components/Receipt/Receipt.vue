<template>
  <div class="text">
    <h1>
      Thank you for your order <br>
      Your confirmation id is: {{ requestId }}
    </h1>
    <div>
      <button @click="goHome">Home</button>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
import router from '../../router/index';

export default {
  name: 'receipt',
  props: {
    requestId: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      receiptId: ''
    };
  },
  mounted() {
    this.fetchReceiptId();
  },
  methods: {
    goHome() {
      router.push("/");
    },
    async fetchReceiptId() {
    try {
      // Make an API call to your backend endpoint to fetch the receipt details
      const response = await axios.get(`http://localhost:8080/api/requests/${this.$route.params.requestId}`);
      this.requestId = response.data.id;
    } catch (error) {
      console.error('Error fetching receipt details:', error);
    }
  }
  }
};
</script>
<style lang="scss" scoped>
.text {
  margin-top: 25%;
  display: flex;
  color: white;
  text-align: center;
  width: 100%;
  align-items: center;
  justify-content: center;
}
</style>