<template>
 <div>
     <div class="theme">
      <div class="no">
        404
      </div>
      <p>Page not Found</p>
     </div>
     <v-symbol></v-symbol>
 </div>
</template>
 
<script>

import vSymbol from '@/components/Symbol.vue'

export default {

    name: 'NotFound',

    components: {

        vSymbol

    },

};

</script>
 
<style lang="less" scoped>

.theme p {
    font-size: 40px;
    font-weight: bold;
    top: 320px;
    left: 610px;
    position: absolute;
}


.no {

    font-size: 180px;
    font-weight: bold;
    top: 180px;
    left: 610px;
    position: absolute;
}

</style>