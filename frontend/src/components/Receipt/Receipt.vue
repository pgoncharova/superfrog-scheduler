<template>
    <div class="text">
        <h1> Thank you for your order <br>
           Your confirmation id is: {{ eventInfo.receiptId }}</h1>
    </div>
</template>

<script>
  export default {
    name: 'receipt',
    props:{
      eventInfo: {
        type: Object
      }
    }
  }
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