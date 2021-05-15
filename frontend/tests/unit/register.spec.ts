import { shallowMount } from '@vue/test-utils';
import Register from '@/views/Register.vue'

describe('Register.vue', () => {
  it('should render Create User Button', () => {
    const wrapper = shallowMount(Register);
    const contentButton = wrapper.find('button');
    expect(contentButton.text()).toEqual('Create Account');
  })
})
test('sets the value', async () => {
    const wrapper = shallowMount(Register)
    const input = wrapper.find('input')
  
    await input.setValue('Test')
  
    expect(input.element.value).toBe('Test')
  })