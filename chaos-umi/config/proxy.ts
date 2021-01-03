export default {
  dev: {
    '/manage/': {
      target: 'http://localhost:8090',
      changeOrigin: true,
    },
  },
  test: {
    '/manage/': {
      target: 'https://dev.firepongo.com/api',
      changeOrigin: true,
    },
  },

};
