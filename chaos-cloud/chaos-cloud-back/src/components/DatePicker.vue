<template>
  <el-date-picker v-model="time"
                  type="date"
                  align="right"
                  :picker-options="pickerOptions"
                  :placeholder="placeholder"
                  @change="handleTimeChange"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd">
  </el-date-picker>
</template>
<script>
    import PickerData from '@/common/PickerData'

    export default {
        data() {
            return {
                time: '',
                pickerOptions: PickerData.data()
            };
        },
        props: {
            'placeholder': {
                required: false,
                default: ''
            },
        },
        methods: {
            handleTimeChange(val) {
                this.$emit("handleTimeChange", val)
            }
        }
    };
</script>
