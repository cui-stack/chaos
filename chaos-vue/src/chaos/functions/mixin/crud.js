import Data from '@/chaos/functions/common/Data';

export const create = {
    data() {
        return {
            domain: '',
            showAddForm: false,
            form: {},
            rules: {}
        }
    },
    methods: {
        showAdd() {
            this.showAddForm = true
        },
        doAdd() {
            Data.validate(this, 'form', async () => {
                const res = await Data.add(this.domain, this.form);
                if (res) {
                    this.showAddForm = false;
                    this.search()
                }
            })
        }
    }
};

export const update = {
    data() {
        return {
            domain: '',
            showUpdateForm: false,
            updateForm: {},
            rules: {},
            showUpdateMethod: '',
            updateMethod: ''
        }
    },
    methods: {
        async showUpdate(mu) {
            this.showUpdateForm = true;
            if (this.showUpdateMethod) {
                this.updateForm = await Data.query(this.domain + '/' + this.showUpdateMethod, {mu})
            } else {
                this.updateForm = await Data.one(this.domain, mu)
            }
        },
        doUpdate() {
            Data.validate(this, 'updateForm', async () => {
                let res;
                if (this.updateMethod) {
                    res = await Data.submit(this.domain + '/' + this.updateMethod, {
                        mu: this.updateForm.mu,
                        data: this.updateForm
                    })
                } else {
                    res = await Data.update(this.domain, this.updateForm.mu, this.updateForm)
                }
                if (res) {
                    this.showUpdateForm = false;
                    this.search()
                }
            })
        },
    }
};

export const remove = {
    data() {
        return {
            domain: '',
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
};

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
            searchMethod: '',
            loading: false
        }
    },
    methods: {
        async search() {
            this.loading = true;
            let res;
            if (this.searchMethod) {
                res = await Data.search(this.domain + '/' + this.searchMethod, this.currentPage, this.limit, this.data);
            } else {
                res = await Data.page(this.domain, this.currentPage, this.limit, this.data)
            }
            this.tableData = res.list;
            this.total = res.total;
            this.loading = false
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
};










