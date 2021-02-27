import Data from '@/chaos/functions/common/Data';

export const create = {
    data() {
        return {
            table: '',
            form: {},
            rules: {},
            indexPath: ''
        }
    },
    methods: {
        doAdd() {
            Data.validate(this, 'form', async () => {
                await Data.add(this.domain, this.form)
                await this.$router.push(this.indexPath)
            })
        }
    }
}

export const update = {
    data() {
        return {
            domain: '',
            updateForm: {},
            rules: {},
            indexPath: ''
        }
    },
    methods: {
        async initUpdate(){
            if (!this.$route.params.mu) {
                await this.$router.push(this.indexPath)
                return
            }
            this.updateForm = await Data.one(this.domain, this.$route.params.mu)
        },
        doUpdate() {
            Data.validate(this, 'updateForm', async () => {
                await Data.update(this.domain, this.updateForm.mu, this.updateForm)
                await this.$router.push(this.indexPath)
            })
        },
    }
}

export const goBack = {
    data() {
        return {
            indexPath: ''
        }
    },
    methods: {
        goBack() {
            this.$router.push(this.indexPath)
        }
    }
}




