export const page = {
    data() {
    },
    methods: {
        handleCurrentChange(val) {
            this.currentPage = val;
            this.search();
        },
        handleSizeChange(val) {
            this.limit = val;
            this.currentPage = 1;
            this.search();
        }
    }
}















