import Data from '@/chaos/functions/common/Data';

export const remove = {
    data() {
        return {
            domain:'',
            pickRowMu: '',
        }
    },
    methods: {
        doDelete(mu) {
            Data.remove(this.domain, mu, () => {
                this.search()
            })
        }
    }
}

export const page = {
    data() {
        return {
            domain: '',
            currentPage: 1,
            limit: 20,
            total: 0,
            tableData: [],
            //查询条件
            data: {},
            searchMethod: ''
        }
    },
    methods: {
        async search() {
            let res
            if (this.searchMethod) {
                res = await Data.search(this.domain + '/' + this.searchMethod, this.currentPage, this.limit, this.data);
            } else {
                res = await Data.page(this.domain, this.currentPage, this.limit, this.data)
            }
            this.tableData = res.list;
            this.total = res.total;
        },
        handleCurrentChange(currentPage) {
            this.currentPage = currentPage;
            this.search();
        },
        handleSizeChange(limit) {
            this.limit = limit;
            this.currentPage = 1;
            this.search();
        },
        handleChange(value, key) {
            this.currentPage = 1;
            this.data[key] = value;
            this.search();
        },
    }
}















