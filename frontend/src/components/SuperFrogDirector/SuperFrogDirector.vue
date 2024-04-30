<template>
    <div>
        <h1>This is superfrog director!</h1>
        <button @click="logout">Logout</button>
    </div>
</template>

<script>
export default {
    name: 'Dashboard',
    data() {
        return {
            username: localStorage.getItem('username')
        };
    },
    methods: {
        logout() {
            // Clear user data from localStorage
            localStorage.removeItem('token');
            localStorage.removeItem('username');

            // Redirect to the login page
            this.$router.push('/login');
        }
    }
}
</script>
<style lang="scss" scoped>

</style>