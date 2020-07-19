<template>
<div>
    <!-- 调试类 -->
    <button type="button" @click="come">ok</button>
</div>
</template>
 
<script>
import container from '@/util/container'
export default {

  name: 'test',
 
  data() {

    return {   
      cc: '12345678'
    };
  
  },
    
  methods: {
      
    come() {
      if (!container.hasDigit(this.cc) && !container.hasLetter(this.cc)) {
        
        console.log("hello world!")

      }
    }

  },
   
  };
</script>
 
<style scoped>
 
</style>