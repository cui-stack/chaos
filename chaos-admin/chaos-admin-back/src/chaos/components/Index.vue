<template>
    <el-input v-model.trim="input"
              prefix-icon="el-icon-search"
              clearable
              :placeholder="placeholder"
              @change="search">
    </el-input>
</template>
<script>
    export default {
        name: 'Input',
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
