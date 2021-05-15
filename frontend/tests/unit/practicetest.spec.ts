import { shallowMount } from '@vue/test-utils';
import Practice from '@/views/Practice.vue'
import store from '@/store'

describe('Home.vue', () => {
  it('should render Select file Button', () => {
    store.commit('login', 'test');
    const wrapper = shallowMount(Practice);
    expect(wrapper.vm.state.level).toEqual(1);
  })
})