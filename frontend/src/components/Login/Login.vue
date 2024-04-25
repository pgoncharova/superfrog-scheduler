<template>
    <div>
        <div class="container">
            <label for="username">Username: </label>
            <input class="username">
            <label for="password">Password: </label>
            <input class="password">
            <button>Login</button>
        </div>
    </div>
</template>

<script>
    export default {
        
    }
</script>

<style lang="scss" scoped>
    .container {
        display: flex;
        justify-content: center;
        align-content: center;
    }
</style>