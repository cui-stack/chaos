<template>
  <div class="paging">
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :page-sizes="[10, 20, 50, 100,500]"
      layout="total, sizes, prev, pager, next, jumper"
      :current-page.sync="currentPage"
      :page-size="limit"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
    export default {
        name: "Paging",
        data() {
            return {
                currentPage: 1
            }
        },
        props: {
            'total': {
                required: false,
                default: 0
            },
            'limit': {
                required: false,
                default: 20
            },
            'small': {
                required: false,
                type: Boolean,
                default: false
            }
        },
        watch: {
            currentPage(val) {
                // 改变这个值并不会触发 handleCurrentChange
                if (typeof val === "number") {
                    this.currentPage = val;
                }
            },
        },
        methods: {
            handleCurrentChange(val) {
                window.scrollTo(0,0);
                this.$emit("handleCurrentChange", val);
            },
            handleSizeChange(val) {
                this.currentPage = 1;
                this.$emit('handleSizeChange', val);
            }
        }
    }
</script>

<style scoped>
  .paging {
    margin: auto 0px;
    text-align: right;
  }
</style>
