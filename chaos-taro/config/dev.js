export const env = {
  NODE_ENV: '"development"'
};
export const defineConstants = {};
export const mini = {};
export const h5 = {
  esnextModules: ["taro-ui"],
  devServer: {
    host: "0.0.0.0",
    port: 10086,
    proxy: {
      "/": {
        target: "http://127.0.0.1:8089",
        changeOrigin: true,
        ws: false
      }
    }
  }
};
