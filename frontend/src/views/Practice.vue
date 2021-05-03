<template>
  <div class="home">
    <h5>PRACTICE :(((</h5>  
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue';
export default defineComponent({
  name: 'Practice',
  setup() {
    const state = reactive({
      
    });
    return { state };
  },
});
</script>
<style scoped type="text/css">
.text {
  height: 10vh;
}
.home {
  padding: 2rem;
  background-color: #e5e5e5;
}
textarea {
  width: 30%;
}
.button-two {
  background-color: #FA9E9E;
}
.oneWord {
  color: #000;
  font-size: 3rem;
  background-color: #F4EEEE;
  padding: 4rem 1rem;
  width: 50%;
  margin: 1rem auto;
}
.p-slider-horizontal, .p-inputtext {
    width: 14rem;
    margin: auto;
    color: #000;
}
.p-slider-vertical {
     height: 14rem;
     margin: auto;
     color: #000;
}
</style>
