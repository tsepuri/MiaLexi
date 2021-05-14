 <template>
  <div class="home">
    <form v-if="!state.textInput">
    <textarea v-model="state.title" placeholder="Enter title here"></textarea>
    <br>
    <textarea v-model="state.text" class="biggerText" placeholder="Enter text here"></textarea>
    <br>
    <div v-if="state.selectingFile">
      <input type="file" class="button" @change="previewFile" accept="application/pdf,text/plain"><button type="submit" class="button button-two" @click.prevent="state.selectingFile = false">Cancel</button>
      </div>
      <div v-if="state.enteringWiki">
      <input type="text" v-model="state.wikiText" placeholder="Input Wikipedia URL or Page name"><button type="submit" class="button button-two" @click="state.enteringWiki = false">Cancel</button>
      </div>
    <div v-if="!state.selectingFile && !state.enteringWiki">
    <button type="submit" class="button button-two" @click.prevent="state.selectingFile = true">Select File</button>
    <button type="submit" class="button button-two" @click.prevent="state.enteringWiki = true">Enter Wiki</button>

    </div>
      <br>
    <button type="submit" class="button button-two" @click.prevent="onTextInput">Start</button>
    <div class="red" v-if="state.noInputtedText">No inputted text.</div>
     <div class="red" v-if="state.noInputtedTitle">No inputted title.</div>
    </form>
    <div v-if="state.textInput" class="pageView">
      <div>
      <h1>{{ state.title }}</h1>
      <h1 :style="`font-size: ${state.font}px`" class="oneWord">{{ state.word}}</h1>
      <button v-if="state.paused && state.text.length" @click = "goLeft" class="button button-two button-small">Left</button>
      <button type="submit" v-if="state.text.length && !state.completed" class="button button-two" :class="[state.paused ? 'pausedButton' : '']" @click="togglePause">{{state.paused ? 'Resume' : 'Pause'}}</button>
      <button v-if="state.paused && state.text.length && !state.completed" @click = "goRight" class="button button-two button-small">Right</button>
      <br>
      <button v-if="state.paused && state.text.length" class="button button-two button-save" @click="saveInPlace">Save in place</button>
      <br>
      <p class="red" v-if="state.fileTooBig">File too big to save</p>
      <p class="red" v-if="state.saveLoggedOut">Login to save in place</p>
      <br>
      <div class="sliders" v-if="state.text.length">
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
      <vue-slider class="slider" v-model="state.font"  :min="80"
      :max="125" :tooltip="'none'"
      :interval="1"/>
      </div>
      </div>
      </div>
      <button type="submit" class="button button-two" @click="newText">Input New Text</button> <button v-if="state.text.length" type="submit" class="button button-two pausedButton" @click="startOver">Start Over</button>
    <div v-if="state.completed && state.text.length" class="completedDiv">Text has been completed
      <br>
      </div> 
      </div>
      <div v-if="state.loggedIn">
        <ul class="page page-two">
        <li class="list-head">Saved files</li>
        <li class="list" @click="titlePressed(index)" :class="{ clicked: state.activeIndex === index }" v-for="(title, index) in state.savedList" :key="index">{{title }}</li>
        </ul>
        <button class="button" @click="getSavedFile" :disabled="state.activeIndex === -1">Choose</button>
        </div>
    </div>  
     
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref, computed, onMounted } from 'vue';
import VueSlider from 'vue-slider-component'
import store from '@/store';
import { TextService } from '@/services';
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
      font: 80,
      title: '',
      completed: false,
      noInputtedText: false,
      noInputtedTitle: false,
      selectingFile: false,
      enteringWiki: false,
      wikiText: '',
      startedOver: false,
      file: null,
      activeIndex: -1,
      loggedIn: store.getters.isLoggedIn,
      savedList: computed(() => store.getters.savedTitles),
      saveLoggedOut: false,
      fileTooBig: false
    });
    const titlePressed = (index:number) => {
      console.log(index);
      if (state.activeIndex === index) {
        state.activeIndex = -1;
      }
      else {
        state.activeIndex = index;
      }
    }
    const getSavedFile = async() => {
      if (state.activeIndex !== -1) {
        state.title = state.savedList[state.activeIndex];
        state.completed = false;
        state.startedOver = true;
        state.paused = true;
        state.text = '';
        const result = await TextService.getContent(state.title);
        console.log(result);
        state.text = result.fileContent;
        console.log(state.text);
        state.count = result.index;
        state.count -= 1;
        state.activeIndex = -1;
        onTextInput();
      }
       
    }
    onMounted(async () => {
          if (state.savedList[0] == "") {
            await TextService.addListToStore();
          }
          if (state.loggedIn && state.savedList.length > 0) {
              state.textInput = true;
          }
      });
    const value1 = ref(50);
    const showWords = () => {
      const interval = 60000/(state.speed);
      const int = setInterval(() => { // time interval
     // next value 
        if ((state.count !== state.textArray.length - 1)) { 
           if(!state.paused && !state.startedOver && state.count > -1) {
             // done = true when the end of array reached
          state.word = state.textArray[++state.count]; // concatenate word to the string
          console.log(state.word);
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
    }
    const setDefaultVals = () => {
      state.saveLoggedOut = false;
      state.fileTooBig = false;
      state.enteringWiki = false;
    }
    const complete = () => {
      state.completed = true;
      state.word = '';
      state.paused = true;
    }
    const newText = () => {
      state.completed = false;
      setDefaultVals();
      state.textInput = false;
      state.enteringWiki = false;
      state.count = -1;
      state.text = '';
      state.title = '';
    }
    const startOver = () => {
      state.count = -1;
      state.completed = false;
      setDefaultVals();
      state.startedOver = true;
      state.paused = true;
      onTextInput();
    }
    const onTextInput = async () => {
      let textReceived = false;
      state.noInputtedText = false;
      state.noInputtedTitle = false;
      if (state.enteringWiki) {
        if (state.wikiText.length !== 0) {
          state.text = await TextService.wikipedia(state.wikiText);
          textReceived = true;
          state.title = state.wikiText;
          state.enteringWiki = false;
        }
      }
      else if (state.selectingFile) {
        if (state.file !== null) {
          console.log("here")
          console.log(state.file);
          state.text = await TextService.fileInput(state.file || '');
          textReceived = true;
          if (state.file !== null) {
            const test = state.file || {name: ""};
            state.title = test.name;
          }
          else {
            state.title = 'Test';
          }
          state.selectingFile = false;
        }
      }
      else if (state.text.length === 0) {
        state.noInputtedText = true;
      }
      else if (state.title.length === 0) {
        state.noInputtedTitle = true;
      }
      else {
        textReceived = true;
      }
      if (textReceived) {
        await TextService.addListToStore();
        state.textInput = true;
        state.textArray = state.text.split(" ");
        if (store.getters.censorWords) {
          let censoredWords = store.getters.censoredWords;
          console.log(state.textArray);
          console.log(censoredWords);
          let newArr = [];
          for (let word of state.textArray) {
            for (let censor of censoredWords) {
              if (word.toLowerCase().includes(censor.toLowerCase()) && censor.length > 0) {
                word = "***";
              }
            }
            newArr.push(word);
          }
        console.log(newArr);
        state.textArray = newArr;
        }
        state.word = state.textArray[++state.count];
        showWords();
      }
      
    }
    const togglePause = () => {
      setDefaultVals();
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
    const previewFile = (event:any) => {
      if (event.target.files.length > 0) {
          state.file = event.target.files[0];     
      }
      else {
        state.file = null;
      }
    }
    const saveInPlace = async() => {
      if (!state.loggedIn) {
        state.saveLoggedOut = true;
      }
      else if (!state.savedList.includes(state.title)) {
        console.log(state.text);
        try {
        await TextService.save(state.title, state.count, state.text);
        store.commit('addToSavedTitles', state.title);
        }
        catch (err) {
          state.fileTooBig = true;
        }
      }
      else {
        await TextService.updateIndex(state.title, state.count);
      }
    }
    return { state, onTextInput, titlePressed, togglePause, previewFile, goLeft, goRight, value1, newText, startOver, saveInPlace, getSavedFile };
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
  padding: 0.4rem;
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
.oneWord {
  color: #000;
  background-color: #F4EEEE;
  padding: 3rem 1.5rem;
  width: 80%;
  height: 15vh;
  margin: 1rem auto;
}
.pageView {
  display: grid;
  grid-template-columns: 2fr 1fr;
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
.page-two {
  font-size: 1.5rem;
  list-style: none;
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
.page {
  width: 80%;
  margin: 1rem;
  color: #000;
}
.button-save:hover {
  background-color: rgb(65, 145, 172);
}
.completedDiv {
  color: #000;
}
</style>