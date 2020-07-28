<template>
  <div id="app">
    <keep-alive>
      <router-view v-if="$route.meta.keepAlive"
                   v-wechat-title="$route.meta.title"></router-view>
    </keep-alive>
    <router-view v-if="!$route.meta.keepAlive"
                 v-wechat-title="$route.meta.title"></router-view>
  </div>
</template>

<script>

  export default {
    name: 'App',
    computed: {
    },
    data() {
      return {
      };
    },
    methods: {

    }
  }
</script>

<style>
  * {
    margin: 0;
    padding: 0;
    border: 0;
  }
</style>
