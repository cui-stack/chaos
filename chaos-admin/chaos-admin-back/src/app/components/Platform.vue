<template>
    <el-select
            v-model="innerValue"
            @change="change"
            placeholder="请选择平台">
        <el-option
                v-for="item in datas"
                :key="item.mu"
                :label="item.name"
                :value="item.mu">
        </el-option>
    </el-select>
</template>

<script>
    import Data from '@/chaos/functions/common/Data';

    export default {
        name: 'Platform',
        props: {
            value: {
                type: String,
                default: ''
            },
            isInit: {
                type: Boolean,
                default: true
            },
            init: {
                type: Function
            },
            change: {
                type: Function
            },
        },
        watch: {
            value(value) {
                this.innerValue = value
            },
        },
        data() {
            return {
                innerValue: '',
                datas: []
            }
        },
        async created() {
            this.datas = await Data.list('chaos_platform')
            if (this.isInit) {
                this.innerValue = this.datas[0].mu
            }
            if (this.init)
                this.init(this.innerValue)
        },

    }
</script>
