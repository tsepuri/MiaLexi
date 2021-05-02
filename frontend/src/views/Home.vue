<template>
  <div class="home">
    <form v-if="!state.textInput">
    <textarea v-model="state.text"></textarea>
    <br>
    <button type="submit" class="button button-two" @click="onTextInput">Submit</button>
    </form>
    <div v-if="state.textInput">
      <h1 class="oneWord">{{ state.word}}</h1>
      <button v-if="state.paused" @click = "goLeft" class="button button-two">Left</button>
      <button type="submit" class="button button-two" @click="togglePause">{{state.paused ? 'Resume' : 'Pause'}}</button>
      <button v-if="state.paused" @click = "goRight" class="button button-two">Right</button>
    </div>    
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue';
export default defineComponent({
  name: 'Home',
  setup() {
    const state = reactive({
      text: '',
      textInput: false,
      word: '',
      count: -1,
      textArray: [""],
      paused: false
    });
    const showWords = () => {
      const int = setInterval(() => { // time interval
      console.log("here");
     // next value 
        if ((state.count !== state.textArray.length - 1) && !state.paused) { // done = true when the end of array reached
          state.word = state.textArray[++state.count]; // concatenate word to the string
          console.log(state.word);
        } else {
          clearInterval(int); // when done - clear interval
        }
      }, 1000)
    }
    const onTextInput = () => {
      state.textInput = true;
      state.textArray = state.text.split(" ");
      showWords();
      console.log("What?");
    }
    const togglePause = () => {
      state.paused = !state.paused;
      if (!state.paused) {
        showWords();
      }
    }
    const goLeft = () => {
      if (state.count > 0) {
        state.word = state.textArray[--state.count];
      }
    }
    const goRight = () => {
      if (state.count < state.textArray.length - 1) {
        state.word = state.textArray[++state.count];
      }
    }
    return { state, onTextInput, togglePause, goLeft, goRight };
  },
  components: {
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
</style>
