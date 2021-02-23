<template>
    <el-select v-model="mu" @change="platformChange" placeholder="请选择平台">
        <el-option v-for="item in platforms" :key="item.mu" :label="item.name"
                   :value="item.mu"/>
    </el-select>
</template>
<script>
    import Data from '@/chaos/functions/common/Data';

    export default {
        name: 'Platform',
        props: {
            platformMu: {
                type: String,
                default: ''
            },
            inited: {
                type: String,
                default: 'true'
            },
        },
        watch: {
            platformMu(platformMu) {
                this.mu = platformMu
            },
        },
        data() {
            return {
                table: 'chaos_platform',
                platforms: [],
                mu: ''
            }
        },
        async created() {
            this.platforms = await Data.list(this.table)
            if (this.inited === "true") {
                this.mu = this.platforms[0].mu
            }
            this.$emit("platformInit", this.mu)
        },
        methods: {
            platformChange(platformMu) {
                this.mu = platformMu
                this.$emit("platformChange", this.mu)
            }
        }
    }
</script>
