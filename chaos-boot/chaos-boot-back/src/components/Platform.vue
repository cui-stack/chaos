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
    import Data from '@/common/Data'
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
        mounted() {
            Data.chain([this.list, () => {
                eventBus.$emit('inited', this.mu)
            }])
        },
        methods: {
            list(after) {
                Data.list(this.table, {}, (res) => {
                    this.platforms = res.data
                    this.mu = this.platforms[0].mu
                    if (after != undefined) after.shift()(after)
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
