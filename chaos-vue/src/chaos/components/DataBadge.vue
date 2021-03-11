<template>
    <el-badge :value="value" :hidden="!value"/>
</template>
<script>
    import Data from '@/chaos/functions/common/Data';
    import {SideMenuData} from '@/app/config'

    export default {
        name: 'DataBadge',
        data() {
            return {
                value: ''
            };
        },
        props: {
            link: {
                required: true,
                default: ''
            },
        },
        watch: {
            $route() {
                this.queryValue()
            }
        },
        created() {
            this.queryValue()
        },
        methods: {
            async queryValue() {
                const method = SideMenuData().get(this.link);
                if (!method)
                    return;
                this.value = await Data.query(method)
            }
        }

    };
</script>
