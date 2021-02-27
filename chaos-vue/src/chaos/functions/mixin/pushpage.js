export const pushpage = {
    data() {
        return {
            addPath: '',
            updatePath: ''
        }
    },
    methods: {
        showAdd() {
            this.$router.push('/' + this.addPath)
        },
        showUpdate(mu) {
            this.$router.push({
                name: this.updatePath,
                params: {mu}
            })
        },
    }
}


