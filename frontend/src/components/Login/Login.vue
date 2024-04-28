<template>
    <div>
        <div class="container">
            <label for="username">Username: </label>
            <input class="username" id="username" name="username" v-model="username">
            <label for="password">Password: </label>
            <input class="password" id="password" name="password" v-model="password">
            <button @click="login">Login</button>
            <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

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
                    const basicAuth = 'Basic ' + btoa(this.username + ":" + this.password);
                    const response = await axios.post('http://localhost:8080/api/users/login', {}, {
                        headers: {
                            Authorization: basicAuth
                        }
                    });
                    const token = response.data.data.token;
                    console.log(token);
                    const username = response.data.data.userInfo.username;
                    localStorage.setItem('token', token);
                    localStorage.setItem('username', username);
                    this.$router.push('/');
                } catch (error) {
                    console.error(error);
                    this.errorMessage = error.response.data.message;
                }
            },
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