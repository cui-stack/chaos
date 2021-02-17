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
    import Data from 'chaos-data/common/Data';
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
                Data.list(this.table, {}, (res) => {
                    this.platforms = res.data
                    this.mu = this.platforms[0].mu
                    eventBus.$emit('inited', this.mu)
                })
            },
            handleChange(val) {
                this.$emit("handleChange", val)
            }
        }
    }
</script>
<style scoped>

</style>
