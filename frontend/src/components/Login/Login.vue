<template>
  <div>
    <div class="container">
      <label for="username">Username: </label>
      <input
        class="username"
        id="username"
        name="username"
        v-model="username"
      />
      <label for="password">Password: </label>
      <input
        class="password"
        id="password"
        name="password"
        v-model="password"
      />
      <button @click="login">Login</button>
      <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
      <button @click="goHome">Home</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
      errorMessage: null,
    };
  },
  methods: {
    async login() {
      try {
        const basicAuth = "Basic " + btoa(this.username + ":" + this.password);
        const response = await axios.post(
          "https://superfrog-container-backend.nicerock-3516d100.eastus.azurecontainerapps.io/api/users/login",
          {},
          {
            headers: {
              Authorization: basicAuth,
            },
          }
        );
        const token = response.data.data.token;
        const role = response.data.data.userInfo.roles;
        console.log(token);
        const username = response.data.data.userInfo.username;
        const id = response.data.data.userInfo.id;
        localStorage.setItem("token", token);
        localStorage.setItem("username", username);
        if (role == "superfrog") {
          this.$router.push(`/super-frog/${id}}`);
        } else if (role == "superfrog spiritdirector") {
          this.$router.push(`/super-frog-director`);
        }
      } catch (error) {
        console.error(error);
        this.errorMessage = error.response.data.message;
      }
    },
    goHome() {
        this.$router.push('/');
    }
  },
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  justify-content: center;
  align-content: center;
}
</style>
