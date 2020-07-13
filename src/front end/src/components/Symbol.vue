<template>
  <div class="symbol">
    <p>RaySun WHUT</p>
  </div>
</template>

<script>
export default {
  name: 'v-symbol',

}
</script>

<style scoped>

.symbol {

    background: black;
    /* z-index: -1; */
    width: 178px;
    height: 53px;
    position: relative;
    top: 5px;
    left: 10px;

}

.symbol p {
    
    font-size: 20px;
    font-weight: bold;
    color: white;
    font-family: Microsoft Jhenghei UI;
    padding: 9px;
    z-index: 5;
    position: absolute;
    top: -16px;
    left: 10px;

}

</style>

 