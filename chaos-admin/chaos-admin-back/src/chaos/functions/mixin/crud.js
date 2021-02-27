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
                await Data.add(this.domain, this.form)
                this.showAddForm = false
                this.search()
            })
        }
    }
}

export const update = {
    data() {
        return {
            domain: '',
            showUpdateForm: false,
            updateForm: {},
            rules: {}
        }
    },
    methods: {
        async showUpdate(mu) {
            this.updateForm = await Data.one(this.domain, mu)
            this.showUpdateForm = true
        },
        doUpdate() {
            Data.validate(this, 'updateForm', async () => {
                await Data.update(this.domain, this.updateForm.mu, this.updateForm)
                this.showUpdateForm = false
                this.search()
            })
        },
    }
}










