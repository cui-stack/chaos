import Data from '@/chaos/functions/common/Data';
export const page = {
    data() {
        return {
            table:'',
            currentPage: 1,
            limit: 20,
            total: 0,
            tableData: [],
            //查询条件
            data:{}
        }
    },
    methods: {
        async search() {
            const res = await Data.page(this.table, this.currentPage, this.limit, this.data)
            this.tableData = res.list;
            this.total = res.total;
        },
        doDelete(mu) {
            Data.remove(this.table, mu, () => {
                this.search()
            })
        },
        handleCurrentChange(currentPage) {
            this.currentPage = currentPage;
            this.search();
        },
        handleSizeChange(limit) {
            this.limit = limit;
            this.currentPage = 1;
            this.search();
        }
    }
}















