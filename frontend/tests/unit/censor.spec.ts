import { shallowMount } from '@vue/test-utils';
import Censorship from '@/views/Censorship.vue'
import store from '@/store'

describe('Censorship.vue', () => {
  it('should render Access Button', () => {
    store.commit('login', 'test');
    const wrapper = shallowMount(Censorship);
    const contentButton = wrapper.find('button');
    expect(contentButton.text()).toEqual('Access');
  })
})