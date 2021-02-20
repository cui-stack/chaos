import Data from '@/chaos/functions/common/Data';

export const crud = {
    data() {
        return {
            table: '',
            showAddForm: false,
            form: {},
            showUpdateForm: false,
            updateForm: {},
            rules: {}
        }
    },
    methods: {
        showAdd() {
            this.showAddForm = true
        },
        doAdd() {
            Data.validate(this, 'form', async () => {
                await Data.add(this.table, this.form)
                this.showAddForm = false
                this.search()
            })
        },
        async showUpdate(mu) {
            this.updateForm = await Data.one(this.table, mu)
            this.showUpdateForm = true
        },
        doUpdate() {
            Data.validate(this, 'form', async () => {
                await Data.update(this.table, this.updateForm.mu, this.updateForm)
                this.showUpdateForm = false
                this.search()
            })

        },
    }
}















