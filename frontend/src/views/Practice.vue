<template>
<div class="main">
  <div class="home">
    <div v-if="state.loggedIn">
      <div v-if="false">
      <li v-for="(level) in [...Array(state.level).keys()]" :key="level">
        {{ level+1 }}
      </li>
      </div>
      <h1>{{ state.title }}</h1>
      <h1 :style="`font-size: ${state.font}px`" class="oneWord">{{ state.word}}</h1>
      <button v-if="state.paused && !state.levelCompleted" @click = "goLeft" class="button button-two button-small">Left</button>
      <button type="submit" class="button button-two" :class="[state.paused ? 'pausedButton' : '']" @click="togglePause">{{state.paused ? (state.levelCompleted ? 'Next Level' : 'Resume') : 'Pause'}}</button>
      <div class="sliders">
      <div>
      <p>Level {{state.level}}</p>
          <div v-if="state.levelCompleted" class="completedDiv">Congratultions! You passed level {{ state.level - 1 }}
          </div>
      <h5> Speed: {{state.speed}}</h5>
      <h5>Font size {{state.font}}</h5>
      <div v-if="!state.paused">Pause to change font</div>
      <div v-else class="sliderDiv">
      <vue-slider class="slider" v-model="state.font"  :min="80"
      :max="125" :tooltip="'none'"
      :interval="1"/>
      </div>
      </div>
      </div>
       <button v-if="!state.levelCompleted" type="submit" class="button button-two pausedButton" @click="startOver">Start Over</button>
      <br>
    </div>  
    <div v-else>
      <h1>Log in or create an account to access practice mode.</h1>
      </div>
    </div>
    <div class="bottom" v-if="state.badges[0] != ''">
      <h1 class="bottom-header"><u>Current Badges</u></h1>
      <ul class="badges">
      <li v-for="(badge, index) in state.badges" :key="index">
        {{badge}}
      </li>
      </ul>
      </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref, onMounted, computed } from 'vue';
import VueSlider from 'vue-slider-component'
import { UserService, TextService } from '@/services'
import router from '@/router';
import store from '@/store';
import 'vue-slider-component/theme/antd.css'
 
export default defineComponent({
  name: 'Home',
  setup() {
    const state:any = reactive({
      text: '',
      textInput: false,
      word: '',
      count: -1,
      textArray: [""],
      paused: true,
      font: 80,
      level: 1,
      completed: false,
      noInputtedText: false,
      noInputtedTitle: false,
      startedOver: false,
      loggedIn: store.getters.isLoggedIn,
      levelCompleted: false,
      speed: 60,
      badges: ['']
      });
    const speed = () => {
      return (60*Math.pow(1.2, state.level-1)).toFixed(0);
    }
    const value1 = ref(50);
    const showWords = () => {
      const interval = 60000/(state.speed);
      const int = setInterval(() => { // time interval
     // next value 
        if ((state.count !== state.textArray.length - 1)) { 
           if(!state.paused && !state.startedOver) {
             // done = true when the end of array reached
            state.word = state.textArray[++state.count]; // concatenate word to the string
            }
            else {
              state.startedOver = false;
              clearInterval(int);
            }
        } else {
          clearInterval(int); // when done - clear interval
          complete();
        }
      }, interval)
    };
    const setTextTitle = async() => {
      state.title = `Practice text ${state.level}`;
      try {
        state.text = await TextService.getPracticeFile();
      }
      catch (err) {
        state.text = 'This is a demo text';
      }
      if (state.text.length === 0) {
        state.text = 'This is a demo text';
      }
      return;
    }
    onMounted(async () => {
          await setTextTitle();
          onTextInput();
      });
    const complete = async () => {
      state.completed = true;
      state.word = '';
      state.speed *= 1.2;
      state.speed = state.speed.toFixed(0);
      state.level++;
      setBadges(state.level);
      let updated:boolean = await UserService.updateLevel(store.getters.username, state.level);
      state.paused = true;
      state.levelCompleted = true;
    }
    const setBadges = (level:number) => {
      if (level === 2) {
        state.badges = ["-1-"];
      }
      else if ((level-1)%3 === 0) {
        let stars = (level-1)/3
        state.badges.push(`${("*").repeat(stars)}${level-1}${("*").repeat(stars)}`);
      }
    }
    const newText = () => {
      state.completed = false;
      state.textInput = false;
      state.count = -1;
      state.text = '';
      state.title = '';
    }
    const startOver = () => {
      state.startedOver = true;
      state.count = -1;
      state.completed = false;
      onTextInput();
    }
    const onTextInput = () => {
        state.textInput = true;
        state.textArray = state.text.replace( /\n/g, " " ).split( " " );
        state.word = state.textArray[++state.count];
        showWords();
    }
    const togglePause = async() => {
      if (state.levelCompleted) {
        state.levelCompleted = false;
        state.count = -1;
        await setTextTitle();
        onTextInput();
      }
      else if (state.paused) {
       state.paused = !state.paused;
       showWords();
      }
      else {
        state.paused = !state.paused;
      }
    }
    const goLeft = () => {
      state.completed = false;
      if (state.count > 0) {
        state.word = state.textArray[--state.count];
      }
    }
    onMounted(async() => {
          if (state.loggedIn) {
            state.level = await UserService.level(store.getters.username);
          }
          if (state.level > 1) {
            state.speed = speed();
            for (let i = 1; i <= state.level; i++) {
              setBadges(i);
            }
          }
    });
    return { state, onTextInput, togglePause, goLeft, value1, newText, startOver };
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
  padding: 0.5rem;
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
  background-color: #F4EEEE;
  padding: 3rem 1.5rem;
  width: 80%;
  height: 15vh;
  margin: 1rem auto;
}
h1 {
  color: #000;
}
.sliders {
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
.bottom {
  color: #fff;
}
.bottom-header {
  color: #fff;
}
.badges {
  display: inline;
}
.badges li {
  display: inline;
  padding: 1rem;
}
.home li {
  display: inline;
  padding: 1rem;
  color: #000;
}
</style>