<template>
    <div>
        <el-select
                v-model="mu"
                @change="handleChange"
                placeholder="请选择平台">
            <el-option
                    v-for="item in platforms"
                    :key="item.mu"
                    :label="item.name"
                    :value="item.mu">
            </el-option>
        </el-select>
    </div>
</template>
<script>
    import Data from '@/chaos/functions/common/Data';
    import {eventBus} from '@/main'

    export default {
        name: 'Platform',
        data() {
            return {
                table: 'chaos_platform',
                platforms: [],
                mu: ''
            }
        },
        props: {},
        created() {
            this.list()
        },
        methods: {
            async list() {
                const res = await Data.list(this.table)
                this.platforms = res
                this.mu = this.platforms[0].mu
                eventBus.$emit('platformInited', this.mu)
            },
            handleChange(val) {
                this.$emit("platformChange", val)
            }
        }
    }
</script>
<style scoped>

</style>
