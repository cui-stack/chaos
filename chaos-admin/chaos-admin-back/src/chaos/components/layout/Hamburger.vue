<template>
    <div @click="toggleClick">
        <i v-if="isActive" class="el-icon-s-fold"/>
        <i v-if="!isActive" class="el-icon-s-unfold"/>
    </div>
</template>

<script>
    export default {
        name: 'Hamburger',
        props: {
            isActive: {
                type: Boolean,
                default: false
            }
        },
        methods: {
            toggleClick() {
                this.$emit('toggleClick')
            }
        }
    }
</script>

<style lang="less" scoped>
    div {
        line-height: 50px;
        height: 100%;
        float: left;
        cursor: pointer;
        transition: background .3s;
        padding: 0 16px;

        &:hover {
            background: rgba(0, 0, 0, .025)
        }
    }

</style>
