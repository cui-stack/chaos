<template>
    <SearchSelect placeholder="请选择平台"
                  :datas="datas"
                  :value="value"
                  :change="change"/>
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
        },
        data() {
            return {
                domain: 'chaos_platform',
                datas: [],
            }
        },
        async created() {
            this.datas = await Data.list(this.domain)
            this.value = this.datas[0].mu
            //this.$emit("platformInit", this.mu)
        },
        methods: {
            // platformChange(platformMu) {
            //     this.mu = platformMu
            //     this.$emit("platformChange", this.mu)
            // }
        }
    }
</script>
