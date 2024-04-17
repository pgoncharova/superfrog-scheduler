<template>
    <div class="submitbox">
        <h1>Modify a Request</h1>
        <div class="buttoncontainer">
            <label for="start-time">Please enter the confirmation code</label>
            <input type="text"> 
            <br><button> submit </button>
        </div>
    </div>
</template>

<script>
    export default {
        
    }
</script>

<style lang="scss" scoped>


h1 {
    margin-top: 10px;
    margin-left: 50px;
    color: white;
  }

.submitbox .buttoncontainer{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 10 10 10 10;
    color: white;
}

button {
    align-self: center;
}
</style>