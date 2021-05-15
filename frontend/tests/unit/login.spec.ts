import { shallowMount } from '@vue/test-utils';
import Login from '@/views/Login.vue'

describe('Login.vue', () => {
  it('should render Log in Button', () => {
    const wrapper = shallowMount(Login);
    const contentButton = wrapper.find('button');
    expect(contentButton.text()).toEqual('Log in');
  })
})
describe('Login.vue', () => {
    it('should render hyperlink', () => {
      const wrapper = shallowMount(Login);
      const contentButton = wrapper.find('a');
      expect(contentButton.text()).toEqual("Don't have an account? Create one.");
    })
  })
test('sets the value', async () => {
    const wrapper = shallowMount(Login);
    const input = wrapper.find('input')
  
    await input.setValue('Test')
  
    expect(input.element.value).toBe('Test')
  })