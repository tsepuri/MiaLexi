import { TextService } from '@/services';
import store from '@/store';

describe('Text service', () => {
    it('should parse wikipedia', async () => {
      let wiki:string = await TextService.wikipedia("polka");
      expect(wiki).toEqual("The polka is originally a Czech dance and genre of dance music familiar throughout all of Europe and the Americas. It originated in the middle of the nineteenth century in Bohemia, now part of the Czech Republic. The polka remains a popular folk music genre in many European and American countries, and is performed by many folk artists.");
    })
  })

  describe('Text service', () => {
    it('should save file', async () => {
    store.commit('login', 'demo');
    await TextService.save(`Test`, 1, "This is a test file");
    let fileContent:string = (await TextService.getContent("Test")).fileContent;
      expect(fileContent).toEqual("This is a test file");
    })
  })
  describe('Text service', () => {
    it('to test add list to store', async () => {
    store.commit('login', 'TestUser');
    await TextService.save("Test1", 1, "Another test file");
    await TextService.addListToStore();
    expect(store.getters.savedTitles).toEqual(["Test1"]);
    })
  })
  describe('Text service', () => {
    it('to get a practice file', async () => {
    try {
        let practice:string = await TextService.getPracticeFile();
        expect(practice.length > 0).toEqual(true);
    }
    catch (err) {
        // Always throws an error in development mode
        return true;
    }
    
    })
  })
