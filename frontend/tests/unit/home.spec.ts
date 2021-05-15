import { shallowMount, mount } from '@vue/test-utils';
import Home from '@/views/Home.vue'
import store from '@/store'

describe('Home.vue', () => {
  it('should render Select file Button', () => {
    const wrapper = shallowMount(Home);
    const contentButton = wrapper.find('button');
    expect(contentButton.text()).toEqual('Select File');
  })
})
describe('Home.vue', () => {
    it('should render saved list when one exists', () => {
      store.commit('login', 'test');
      store.commit('addToSavedTitles', 'Bird');
      const wrapper = shallowMount(Home);
      expect(wrapper.vm.state.savedList[0]).toEqual('Bird');
    })
  })
describe('Home.vue', () => {
    it('should input a wikipedia page', async () => {
      const wrapper = shallowMount(Home);
      wrapper.vm.state.enteringWiki = true;
      wrapper.vm.state.wikiText = 'polka';
      await wrapper.vm.onTextInput();
      expect(wrapper.vm.state.text).toEqual('The polka is originally a Czech dance and genre of dance music familiar throughout all of Europe and the Americas. It originated in the middle of the nineteenth century in Bohemia, now part of the Czech Republic. The polka remains a popular folk music genre in many European and American countries, and is performed by many folk artists.');
    })
  })
  describe('Home.vue', () => {
    it('should pause the page', () => {
      const wrapper = shallowMount(Home);
      wrapper.vm.togglePause();
      expect(wrapper.vm.state.paused).toEqual(true);
    })
  })
  describe('Home.vue', () => {
    it('should save in place', async () => {
      store.commit('login', "Test");
      const wrapper = shallowMount(Home);
      wrapper.vm.state.savedList = [];
      wrapper.vm.state.text = "Test file";
      wrapper.vm.state.title = "Title";
      wrapper.vm.state.count = 2;
      await wrapper.vm.saveInPlace();
      expect(wrapper.vm.state.savedList).toEqual(['Bird', 'Title']);
    })
  })

