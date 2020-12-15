<template>
    <div id="app">
        <router-view v-wechat-title="$route.meta.title"></router-view>
    </div>
</template>
<script>
    export default {
        name: 'App',
        computed: {},
        created() {
            this.$store.dispatch('app/setBaseUrl', {prod: 'https://center.firepongo.com/api'})
        },
    }
</script>
<style>
</style>
