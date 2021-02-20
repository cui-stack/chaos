<template>
    <el-select v-model="mu" placeholder="请选择角色" @change="roleChange">
        <el-option v-for="item in roles" :key="item.mu" :label="item.info"
                   :value="item.mu"/>
    </el-select>
</template>
<script>
    import Data from '@/chaos/functions/common/Data';

    export default {
        name: 'Role',
        props: {
            role: {
                type: Object,
            },
        },
        watch: {
            async role(role) {
                const {platformMu, roleMu} = role
                if (platformMu) {
                    await this.list()
                    this.mu = roleMu || this.roles[0].mu
                }
            },
        },
        data() {
            return {
                table: 'chaos_role',
                roles: [],
                mu: ''
            }
        },
        methods: {
            async list() {
                this.roles = await Data.list(this.table, {platformMu: this.role.platformMu})
            },
            roleChange(roleMu) {
                this.mu = roleMu
                this.$emit("roleChange", roleMu)
            }
        }
    }
</script>
