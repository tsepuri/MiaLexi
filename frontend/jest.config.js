module.exports = {
    preset: '@vue/cli-plugin-unit-jest/presets/typescript-and-babel',
    "transform": {
        "^.+\\.ts$": "ts-jest",
        ".*\\.(vue)$": "vue-jest",
        "^.+\\.jsx?$": "babel-jest"
      },
      moduleNameMapper: {
        '\\.(css|less)$': '<rootDir>/tests/mock/styleMock.js',
      },
    moduleFileExtensions: [
        "js","ts", "json", "vue"
    ]
  };
