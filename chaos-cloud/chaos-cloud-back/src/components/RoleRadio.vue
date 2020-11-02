<template>
  <div>
    <el-radio-group v-model="mu" @change="handleChange">
      <el-radio v-for="item in roles"
                :key="item.mu"
                :label="item.mu">
        {{item.info}}
      </el-radio>
    </el-radio-group>
  </div>
</template>
<script>
    import Data from '@/common/Data'
    import {eventBus} from '@/main'

    export default {
        name: 'RoleRadio',
        props: {
            roleMu: {
                default: ''
            },
            isShow: {
                default: true
            }
        },
        data() {
            return {
                table: 'chaos_role',
                roles: [],
                mu: ''
            }
        },
        watch: {
            roleMu(val) {
                this.mu = val;
            }
        },
        mounted() {
            Data.chain([this.list, () => {
                eventBus.$emit('inited')
            }])
        },
        methods: {
            list(after) {
                Data.list(this.table, {platformMu: '1253286995502305281'}, (res) => {
                    this.roles = res.data
                    if (this.isShow) {
                        this.mu = this.roles[0].mu
                        this.$emit('changeRole', this.mu)
                    }
                    if (after != undefined) after.shift()(after)
                })
            },
            handleChange(val) {
                this.$emit('changeRole', val)
            }
        }
    }
</script>
<style scoped>

</style>
