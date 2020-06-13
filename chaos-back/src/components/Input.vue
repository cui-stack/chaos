<template>
  <el-input v-model.trim="input"
            :placeholder="placeholder"
            clearable
            prefix-icon="el-icon-search"
            @change="search"
            v-on:keyup.enter.native="search">
  </el-input>
</template>
<script>
    export default {
        data() {
            return {
                input: ''
            };
        },
        props: {
            'placeholder': {
                required: false,
                default: ''
            },
        },
        methods: {
            search(val) {
                this.$emit("search", val)
            }
        }
    };
</script>
