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
};

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
                const res = await Data.add(this.domain, this.form);
                if (res) {
                    await this.$router.push(this.indexPath)
                }
            })
        }
    }
};

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
};

export const update = {
    data() {
        return {
            domain: '',
            updateForm: {},
            rules: {},
            indexPath: '',
            showUpdateMethod: ''
        }
    },
    methods: {
        async showUpdate() {
            if (!this.$route.params.mu) {
                await this.$router.push(this.indexPath);
                return
            }
            if (this.showUpdateMethod) {
                this.updateForm = await Data.query(this.domain + '/' + this.showUpdateMethod, {mu: this.$route.params.mu})
            } else {
                this.updateForm = await Data.one(this.domain, this.$route.params.mu)
            }
        },
        doUpdate() {
            Data.validate(this, 'updateForm', async () => {
                if (this.updateMethod) {
                    const res = await Data.submit(this.domain + '/' + this.updateMethod, {
                        mu: this.updateForm.mu,
                        data: this.updateForm
                    }, () => {
                        if (res) {
                            this.$router.push(this.indexPath)
                        }
                    })
                } else {
                    const res = await Data.update(this.domain, this.updateForm.mu, this.updateForm);
                    if (res) {
                        await this.$router.push(this.indexPath)
                    }
                }
            })
        },
    }
};

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
};




