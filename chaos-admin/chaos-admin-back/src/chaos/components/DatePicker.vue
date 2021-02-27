<template>
    <el-date-picker
            v-model.trim="value"
            type="date"
            align="right"
            :picker-options="pickerOptions"
            :placeholder="placeholder"
            @change="change"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd">
    </el-date-picker>
</template>
<script>

    export default {
        name: 'DatePicker',
        data() {
            return {
                value: '',
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() > Date.now();
                    },
                    shortcuts: [{
                        text: '今天',
                        onClick(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '昨天',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周前',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', date);
                        }
                    }]
                }
            };
        },
        props: {
            placeholder: {
                required: false,
                default: ''
            },
            change: {
                type: Function
            }
        },
    };
</script>
