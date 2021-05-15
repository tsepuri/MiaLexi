import { CensorshipService } from '@/services';
import store from '@/store';

describe('Censorship service', () => {
    it('should add to censorship list', async () => {
      await CensorshipService.add("Demo3", "testing");
      let censoredList:string[] = await CensorshipService.getList("Demo3");
      expect(censoredList).toEqual(["testing"]);
    })
  })

  describe('Censorship service', () => {
    it('should remove from censorship list', async () => {
    await CensorshipService.add("Demo1", "test");
    await CensorshipService.add("Demo1", "test1");
    await CensorshipService.remove("Demo1", "test");
    let censoredList:string[] = await CensorshipService.getList("Demo1");
    expect(censoredList).toEqual(["test1"]);
    })
  })
  describe('Censorship service', () => {
    it('to test add list to store', async () => {
    store.commit('login', 'TestUser');
    await CensorshipService.add("TestUser", "test");
    await CensorshipService.addListToStore();
    expect(store.getters.censoredWords).toEqual(["test"]);
    })
  })
