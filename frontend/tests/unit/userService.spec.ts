import { UserService } from '@/services';

describe('User service', () => {
    it('should register a user', async () => {
      let registerSucceeded:boolean = await UserService.register(`Test${Math.random().toFixed(4)}`, "123456")
      expect(registerSucceeded).toEqual(true);
    })
  })
  describe('User service', () => {
    it('should log in a user', async () => {
      let username = `Test${Math.random().toFixed(4)}`;
      await UserService.register(username, "123456");
      let loginSucceeded:boolean = await UserService.login(username, '123456');
      expect(loginSucceeded).toEqual(true);
    })
  })
  describe('User service', () => {
    it('should return default level', async () => {
      let username = `Test${Math.random().toFixed(4)}`;
      await UserService.register(username, "123456");
      let level:number = await UserService.level(username);
      expect(level).toEqual(1);
    })
  })
  describe('User service', () => {
    it('should update level', async () => {
      let username = `Test${Math.random().toFixed(4)}`;
      await UserService.register(username, "123456");
      await UserService.updateLevel(username, "5");
      let level:number = await UserService.level(username);
      expect(level).toEqual(5);
    })
  })
  