<template>
  <router-view v-if="$route.path != '/'"></router-view>

  <div v-if="$route.path == '/'" id="app">
      <title>Landing Page</title>
      
      <div class="landing-page">
          <div class="profile-image"></div>
          <h1 class="heading">SuperFrog Scheduler</h1>

          <p class="description">
              Add TCU Spirit to your event <br>
              Request SuperFrog, TCU Cheerleader, & Showgirls to your <br>
              wedding, grad party and more!
          </p>

          <div class="button-container">
              <button class="button" v-on:click="goToPricing"> Pricing</button>
              <button class="button" v-on:click="goToRange"> Range</button>
              <button class="button" v-on:click="goToRequest"> Request</button>
              <button class="button" v-on:click="goToModifyRequest"> Modify a request</button>
          </div>
      </div>

  </div>
</template>

<script>


export default {
  name: 'App',
  methods: {
      goToPricing() {
          this.$router.push('/pricing')
      },
      goToRange() {
          this.$router.push('/range')
      },
      goToRequest() {
          this.$router.push('/step1')
      },
      goToModifyRequest() {
          this.$router.push('/modify')
      },
      goToLogin() {
          this.$router.push('/login')
      }
  }
}

</script>

<style>
.landing-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;

}



body {
  background-color: #4d2279;
}

.button-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin-top: 20px;
  width: 80%;
}

.button {
  margin: 10px;
  padding: 20px 40px;
  font-size: 20px;
  border: none;
  border-radius: 5px;
  background-color: white;
  color: black;
  cursor: pointer;
  width: 25%;
  border-radius: 40px;
}

.login-text {
  position: absolute;
  left: 32.5%;
  bottom: 80px;
}

.login-button {
  margin: 10px;
  padding: 20px 40px;
  font-size: 20px;
  border: none;
  border-radius: 5px;
  background-color: white;
  color: black;
  cursor: pointer;
  width: 10%;
  border-radius: 40px;
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
}

.button i {
  font-size: 50px;
  margin-bottom: 10px;
}

.profile-image {
  position: absolute;
  top: 20px;
  right: 20px;
  border-radius: 50%;
  width: 250px;
  height: 250px;
  background-image: url('images/super_frog.jpeg');
  background-size: cover;
  background-position: center;
}

.heading {
  font-family: "Times New Roman", Times, serif;
  color: white;
  position: absolute;
  top: 20px;
  left: 20px;
  font-size: 50px;
  font-weight: 2000px;
}

.description {
  font-family: "Times New Roman", Times, serif;
  color: white;
  position: absolute;
  top: 80px;
  left: 20px;
  margin-top: 50px;
  font-size: 24px;
  line-height: 1.2;
}

.logincontainer {
  display: flex;
  align-self: bot;
}
</style>
    
