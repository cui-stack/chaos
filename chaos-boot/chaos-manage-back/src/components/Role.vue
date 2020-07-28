<template>
  <div>
    <el-select v-model="mu"
               @change="handleChange"
               placeholder="请选择角色">
      <el-option v-for="item in roles"
                 :key="item.mu"
                 :label="item.info"
                 :value="item.mu">
      </el-option>
    </el-select>
  </div>
</template>
<script>
    import fetch from '@/axios/fetch'
    import Data from '@/common/Data'
    import {eventBus} from '@/main'

    export default {
        name: 'Role',
        data() {
            return {
                table: 'chaos_role',
                roles: [],
                mu: ''
            }
        },
        props: {},
        mounted() {
            this.list()
        },
        methods: {
            list() {
                Data.list(this.table, {}, (res) => {
                    this.roles = res.data
                    this.mu = this.roles[0].mu
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
