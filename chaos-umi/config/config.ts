import { defineConfig } from 'umi';
import defaultSettings from './defaultSettings';
import proxy from './proxy';
import routes from './routes';

export default defineConfig({
  hash: true,
  antd: {},
  dva: {
    hmr: true,
  },
  history: {
    type: 'browser',
  },
  locale: false,
  dynamicImport: {
    loading: '@/chaos/components/PageLoading/index',
  },
  targets: {
    ie: 11,
  },
  routes,
  theme: {
    'primary-color': defaultSettings.primaryColor,
  },
  title: false,
  ignoreMomentLocale: true,
  proxy: proxy['dev'],
  manifest: {
    basePath: '/',
  },
  exportStatic: {},
  esbuild: {},
  base: '/umi/',
  publicPath: '/umi/',
  outputPath: 'dist/umi'
});

