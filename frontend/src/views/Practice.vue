<template>
  <div class="home">
    <div>
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
import { defineComponent, reactive, ref, onMounted } from 'vue';
import VueSlider from 'vue-slider-component'
import router from '@/router';
import 'vue-slider-component/theme/antd.css'
 
export default defineComponent({
  name: 'Home',
  setup() {
    const state = reactive({
      text: "A Hare was making fun of the Tortoise one day for being so slow. 'Do you ever get anywhere?' he asked with a mocking laugh. 'Yes,' replied the Tortoise, 'and I get there sooner than you think. I'll run you a race and prove it.' The Hare was much amused at the idea of running a race with the Tortoise, but for the fun of the thing he agreed. So the Fox, who had consented to act as judge, marked the distance and started the runners off. The Hare was soon far out of sight, and to make the Tortoise feel very deeply how ridiculous it was for him to try a race with a Hare, he lay down beside the course to take a nap until the Tortoise should catch up. The Tortoise meanwhile kept going slowly but steadily, and, after a time, passed the place where the Hare was sleeping. But the Hare slept on very peacefully; and when at last he did wake up, the Tortoise was near the goal. The Hare now ran his swiftest, but he could not overtake the Tortoise in time. Slow and steady wins the race.",
      text1: "ONCE upon a time there was a prince who wanted to marry a princess; but she would have to be a real princess. He travelled all over the world to find one, but nowhere could he get what he wanted. There were princesses enough, but it was difficult to find out whether they were real ones. There was always something about them that was not as it should be. So he came home again and was sad, for he would have liked very much to have a real princess. One evening a terrible storm came on; there was thunder and lightning, and the rain poured down in torrents. Suddenly a knocking was heard at the city gate, and the old king went to open it.It was a princess standing out there in front of the gate. But, good gracious! what a sight the rain and the wind had made her look. The water ran down from her hair and clothes; it ran down into the toes of her shoes and out again at the heels. And yet she said that she was a real princess. Well, we'll soon find that out, thought the old queen. But she said nothing, went into the bed-room, took all the bedding off the bedstead, and laid a pea on the bottom; then she took twenty mattresses and laid them on the pea, and then twenty eider-down beds on top of the mattresses. On this the princess had to lie all night. In the morning she was asked how she had slept. Oh, very badly! said she. I have scarcely closed my eyes all night. Heaven only knows what was in the bed, but I was lying on something hard, so that I am black and blue all over my body. Its horrible! Now they knew that she was a real princess because she had felt the pea right through the twenty mattresses and the twenty eider-down beds. Nobody but a real princess could be as sensitive as that. So the prince took her for his wife, for now he knew that he had a real princess; and the pea was put in the museum, where it may still be seen, if no one has stolen it. There, that is a true story.",
      text2: "Once upon a time there lived a poor widow and her son Jack. One day, Jack’s mother told him to sell their only cow. Jack went to the market and on the way he met a man who wanted to buy his cow. Jack asked, 'What will you give me in return for my cow?' The man answered, 'I will give you five magic beans!' Jack took the magic beans and gave the man the cow. But when he reached home, Jack’s mother was very angry. She said, 'You fool! He took away your cow and gave you some beans!' She threw the beans out of the window. Jack was very sad and went to sleep without dinner. The next day, when Jack woke up in the morning and looked out of the window, he saw that a huge beanstalk had grown from his magic beans! He climbed up the beanstalk and reached a kingdom in the sky. There lived a giant and his wife. Jack went inside the house and found the giant’s wife in the kitchen. Jack said, 'Could you please give me something to eat? I am so hungry!' The kind wife gave him bread and some milk. While he was eating, the giant came home. The giant was very big and looked very fearsome. Jack was terrified and went and hid inside. The giant cried, 'Fee-fi-fo-fum, I smell the blood of an Englishman. Be he alive, or be he dead, I’ll grind his bones to make my bread!' The wife said, 'There is no boy in here!' So, the giant ate his food and then went to his room. He took out his sacks of gold coins, counted them and kept them aside. Then he went to sleep. In the night, Jack crept out of his hiding place, took one sack of gold coins and climbed down the beanstalk. At home, he gave the coins to his mother. His mother was very happy and they lived well for sometime. Jack and the Beanstalk Fee Fi Fo Fum!Climbed the beanstalk and went to the giant’s house again. Once again, Jack asked the giant’s wife for food, but while he was eating the giant returned. Jack leapt up in fright and went and hid under the bed. The giant cried, 'Fee-fifo-fum, I smell the blood of an Englishman. Be he alive, or be he dead, I’ll grind his bones to make my bread!' The wife said, 'There is no boy in here!' The giant ate his food and went to his room. There, he took out a hen. He shouted, 'Lay!' and the hen laid a golden egg. When the giant fell asleep, Jack took the hen and climbed down the beanstalk. Jack’s mother was very happy with him. After some days, Jack once again climbed the beanstalk and went to the giant’s castle. For the third time, Jack met the giant’s wife and asked for some food. Once again, the giant’s wife gave him bread and milk. But while Jack was eating, the giant came home. 'Fee-fi-fo-fum, I smell the blood of an Englishman. Be he alive, or be he dead, I’ll grind his bones to make my bread!' cried the giant. 'Don’t be silly! There is no boy in here!' said his wife. The giant had a magical harp that could play beautiful songs. While the giant slept, Jack took the harp and was about to leave. Suddenly, the magic harp cried, 'Help master! A boy is stealing me!' The giant woke up and saw Jack with the harp. Furious, he ran after Jack. But Jack was too fast for him. He ran down the beanstalk and reached home. The giant followed him down. Jack quickly ran inside his house and fetched an axe. He began to chop the beanstalk. The giant fell and died. Jack and his mother were now very rich and they lived happily ever after.",
      text3: "There once was a girl named Goldilocks. She is called Goldilocks because of her sproingy locks of golden hair. Goldilocks is an expert at skipping. Usually Goldilocks likes to skip around the pond, but today she decided to skip through the forest. Along the path she noticed the cutest, coziest cottage she had ever seen. 'I wonder who lives in such a cute, cozy cottage,' Goldilocks thought. Goldilocks skipped to the cottage for a closer look. She knocked on the door and was disappointed when no one answered. Then it hit her—the most wonderful smell she had ever smelled! 'Porridge!' Goldilocks said dreamily as her stomach rumbled. 'That smell is making me hungry! I don’t think anyone would mind if I went in for a quick taste.' She opened the door to the cottage. After all that skipping, Goldilocks was starving. Goldilocks went to the table, where she found three bowls of porridge. She tasted the first bowl. 'Too sweet!' she said. Then she tasted the middle bowl. 'Too cinnamony!' she said. Finally she tasted the last bowl. It was just right! 'Wow! This is delicious porridge!” she said. After all that skipping and eating, Goldilocks wanted to sit down. Goldilocks looked around the cozy cottage and noticed three chairs. She sat in the first chair. 'Too hard!' she said. Then she sat in the middle chair. 'Too cushiony!' she said. Finally she sat in the last chair. It was just right! 'This is the most comfortable chair ever!' she said. After all that skipping and eating and sitting, Goldilocks was feeling sleepy. She went into the next room and saw three beds. She flopped onto the first bed. “Too firm!” she said. Then she flopped onto the middle bed. 'Too pillowy!' she said. Finally she flopped onto the last bed. It was just right! 'Zzzzzzzzzzzzzzzz!' she said. Meanwhile, three bears came in the door. 'That was a fine skip through the forest,' said the papa bear. 'It’s good to be home,' said the mama bear. 'I’m hungry!' said the little bear. The bears went over to the table, where they noticed something was different about their porridge. 'Somebody’s been eating my porridge,' said the papa bear. “And somebody’s been eating my porridge,” said the mama bear. “Hey, my porridge is almost gone!” said the little bear. The bears ate what was left of their porridge and then went to sit down. They noticed something was different about their chairs. “Somebody’s been sitting in my chair,” said the papa bear. “And somebody’s been sitting in my chair,” said the mama bear. “Hey! My chair looks like a person!” said the little bear. After all that skipping and wondering about porridge and chairs, the bears were sleepy, and they headed to bed. They noticed something was different about their beds. “Somebody’s been flopping in my bed,” said the papa bear. “And somebody’s been flopping in my bed,” said the mama bear. “Hey! There’s a girl in my bed!” said the little bear. Goldilocks opened one eye, then the other. She saw three bears staring at her. “Hello,” said the little bear. “We’re the bears that live here.” “You are?” said Goldilocks, wide eyed. “Did you know that you have the best porridge, chair, and bed in the whole world?” “I think so too!” said the little bear. “You don’t mind that I tried them?” asked Goldilocks. “No, I don’t mind!” said the little bear. “Let’s play!” The little bear invited Goldilocks to stay, and they laughed and had pillow fights all morning long.",
      textInput: false,
      word: '',
      count: -1,
      textArray: [""],
      paused: false,
      speed: 60,
      font: 30,
      title: 'The Hare and the Tortoise',
      title1: 'Jack and the Beanstalk',
      title2: '',
      title3: 'Goldilocks and the Three Bears',
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
            else {
              clearInterval(int);
            }
        } else {
          clearInterval(int); // when done - clear interval
          complete();
        }
      }, interval)
    };
    onMounted(() => {
          onTextInput();
      });
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
        state.textInput = true;
        state.textArray = state.text.split(" ");
        state.word = state.textArray[++state.count];
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
