import store from '@/store';

describe('Censor Store', () => {
    it('should add a word to censor', () => {
      store.commit('addCensoredWord', 'test');
      expect(store.getters.censoredWords).toEqual(['test']);
    })
  })
describe('Save Store', () => {
    it('should add a saved word', () => {
      store.commit('addToSavedTitles', 'test');
      expect(store.getters.savedTitles).toEqual(['test']);
    })
  })
describe('User Store', () => {
    it('should remove user data', () => {
      store.commit('addToSavedTitles', 'test');
      store.commit('addCensoredWord', 'test');
      store.commit('logout');
      expect(store.getters.savedTitles).toEqual(['']);
      expect(store.getters.censoredWords).toEqual(['']);
    })
  })