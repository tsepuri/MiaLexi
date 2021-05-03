<template>
  <div class="home">
    <form v-if="!state.textInput">
    <textarea v-model="state.title" placeholder="Enter title here"></textarea>
    <br>
    <textarea v-model="state.text" class="biggerText" placeholder="Enter text here"></textarea>
    <br>
    <button type="submit" class="button button-two" @click="selectFile">Select File</button>
    <button type="submit" class="button button-two" @click="enterWiki">Enter Wiki</button>
      <br>
    <button type="submit" class="button button-two" @click="onTextInput">Start</button>
    <div class="red" v-if="state.noInputtedText">No inputted text.</div>
     <div class="red" v-if="state.noInputtedTitle">No inputted title.</div>
    </form>
    <div v-if="state.textInput">
      <h1>{{ state.title }}</h1>
      <h1 class="oneWord">{{ state.word}}</h1>
      <button v-if="state.paused" @click = "goLeft" class="button button-two button-small">Left</button>
      <button type="submit" class="button button-two" :class="[state.paused ? 'pausedButton' : '']" @click="togglePause">{{state.paused ? 'Resume' : 'Pause'}}</button>
      <button v-if="state.paused" @click = "goRight" class="button button-two button-small">Right</button>
      <br>
      <button v-if="state.paused" class="button button-two button-save" @click="saveInPlace">Save in place</button>
      <br>
      <div class="sliders">
        <div>
      <h5>{{state.speed}} WPM</h5>
      <div v-if="!state.paused">Pause to change speed</div>
      <div v-else class="sliderDiv">
      <vue-slider class="slider" v-model="state.speed"  :min="50"
      :max="1500" :tooltip="'none'"
      :interval="10"/>
      </div>
      </div>
      <div>
      <h5>Font size {{state.font}}</h5>
      <div v-if="!state.paused">Pause to change font</div>
      <div v-else class="sliderDiv">
      <vue-slider class="slider" v-model="state.font"  :min="10"
      :max="50" :tooltip="'none'"
      :interval="1"/>
      </div>
      </div>
      </div>
      <button type="submit" class="button button-two" @click="newText">Input New Text</button> <button type="submit" class="button button-two pausedButton" @click="startOver">Start Over</button>
    <div v-if="state.completed" class="completedDiv">Text has been completed
      <br>
      </div> 
    </div>  
     
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref } from 'vue';
import VueSlider from 'vue-slider-component'
import router from '@/router';
import 'vue-slider-component/theme/antd.css'
 
export default defineComponent({
  name: 'Home',
  setup() {
    const state = reactive({
      text: '',
      textInput: false,
      word: '',
      count: -1,
      textArray: [""],
      paused: false,
      speed: 60,
      font: 30,
      title: '',
      completed: false,
      noInputtedText: false,
      noInputtedTitle: false
    });
    const value1 = ref(50);
    const showWords = () => {
      const interval = 60000/(state.speed);
      const int = setInterval(() => { // time interval
     // next value 
        if ((state.count !== state.textArray.length - 1)) { 
           if(!state.paused) {
             // done = true when the end of array reached
          state.word = state.textArray[++state.count]; // concatenate word to the string
          console.log(state.word);
        }
        } else {
          clearInterval(int); // when done - clear interval
          complete();
        }
      }, interval)
    }
    const complete = () => {
      state.completed = true;
      state.word = '';
    }
    const newText = () => {
      state.completed = false;
      state.textInput = false;
      state.count = -1;
      state.text = '';
      state.title = '';
    }
    const startOver = () => {
      state.count = -1;
      state.completed = false;
      onTextInput();
    }
    const onTextInput = () => {
      state.noInputtedText = false;
      state.noInputtedTitle = false;
      if (state.text.length === 0) {
        state.noInputtedText = true;
      }
      else if (state.title.length === 0) {
        state.noInputtedTitle = true;
      }
      else {
        state.textInput = true;
        state.textArray = state.text.split(" ");
        state.word = state.textArray[++state.count];
        showWords();
        console.log("What?");
      }
      
    }
    const togglePause = () => {
      state.paused = !state.paused;
      if (!state.paused) {
        showWords();
      }
    }
    const goLeft = () => {
      state.completed = false;
      if (state.count > 0) {
        state.word = state.textArray[--state.count];
      }
    }
    const goRight = () => {
      if (state.count < state.textArray.length - 1) {
        state.word = state.textArray[++state.count];
      }
    }
    const selectFile = () => {
      return;
    }
    const enterWiki = () => {
      return;
    }
    return { state, onTextInput, togglePause, goLeft, goRight, value1, selectFile, enterWiki, newText, startOver };
  },
  components: {
    VueSlider
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
.sliders {
  color: #000;
  padding: 1rem;
  margin: 0.5rem;
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
h1 {
  color: #000;
}
.sliders {
  display: grid;
  grid-template-columns: 1fr 1fr;
  width: 50%;
  margin: auto;
}
.biggerText {
  height: 10rem;
}
vue-slider {
  width: 50%;
}
.slider {
  width: 50%;
}
.sliderDiv {
  width: 80%;
  margin: auto;
}
.pausedButton {
  background-color: rgb(120, 189, 120);
  color: #fff;
}
.pausedButton:hover {
  background-color: rgb(100, 190, 100);
}
.button-small {
  font-size: 0.8rem;
  padding: 0.6rem;
  background-color: #F4EEEE;
}
.button-small:hover {
  background-color: #f3e3e3;
}
.unpaused {
  background-color: red;
}
.button-save {
  background-color: lightblue;
  color: #fff;
}
.red {
  color: red;
}
.button-save:hover {
  background-color: rgb(65, 145, 172);
}
.completedDiv {
  color: #000;
}
</style>
