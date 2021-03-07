import Data from '@/chaos/functions/common/Data';

export const pushcreate = {
    data() {
        return {
            addPath: '',
        }
    },
    methods: {
        showAdd() {
            this.$router.push('/' + this.addPath)
        },
    }
}

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

export const pushupdate = {
    data() {
        return {
            updatePath: ''
        }
    },
    methods: {
        showUpdate(mu) {
            this.$router.push({
                name: this.updatePath,
                params: {mu}
            })
        },
    }
}

export const update = {
    data() {
        return {
            domain: '',
            updateForm: {},
            rules: {},
            indexPath: '',
            initUpdateMethod: ''
        }
    },
    methods: {
        async initUpdate() {
            if (!this.$route.params.mu) {
                await this.$router.push(this.indexPath)
                return
            }
            if (this.initUpdateMethod) {
                this.updateForm = await Data.query(this.domain + '/' + this.initUpdateMethod, {mu: this.$route.params.mu})
            } else {
                this.updateForm = await Data.one(this.domain, this.$route.params.mu)
            }
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




