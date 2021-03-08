<template>
    <el-select v-model="innerValue" placeholder="请选择角色" @change="change">
        <el-option v-for="item in datas" :key="item.mu" :label="item.info"
                   :value="item.mu"/>
    </el-select>
</template>
<script>
    import Data from '@/chaos/functions/common/Data';

    export default {
        name: 'Role',
        props: {
            platformMu: {
                type: String,
                default: ''
            },
            value: {
                type: String,
            },
            change: {
                type: Function
            },
        },
        data() {
            return {
                domain: 'chaos_role',
                datas: [],
                innerValue: '',
                first: true
            }
        },
        watch: {
            async platformMu() {
                await this.list()
                if (this.first) {
                    this.first = false
                    this.innerValue = this.value || this.datas[0].mu
                } else {
                    this.innerValue = this.datas[0].mu
                }
                this.$emit('change',this.innerValue)
            },
        },
        methods: {
            async list() {
                this.datas = await Data.list(this.domain, {platformMu: this.platformMu})
            },
        }
    }
</script>
