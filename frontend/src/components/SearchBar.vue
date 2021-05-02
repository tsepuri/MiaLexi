<template>
    <div class="searchbar-container">
        <input class = "searchbar" v-model="state.term" type="text" placeholder="Type anything..."/>
        <button type="submit" class="button" @click="message">Search</button>
    </div>
</template>
<script>
import { defineComponent, reactive } from 'vue'
export default defineComponent({
    name:'SearchBar',
    emits: 'searched',
    setup(_, { emit }) { 
    const state = reactive({
        term : ''
    })
    const message = () => {emit('searched', state.term)};
    return { 
      message, state
    } 
    }
})
</script>
<style scoped>
    .searchbar {
       color: #000; 
       padding: 0.5rem;
       margin: 0.25rem;
       border-radius: 2rem;
       border: 1px solid #444;
    }
</style>