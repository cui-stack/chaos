Component({
    properties: {
        text: {
            type: String,
            value: ''
        },
        sessionFrom: {
            type: String,
            value: ''
        }
    },
    data: {},
    methods: {
        handleContact(e) {
            console.log(e.detail.path)
            console.log(e.detail.query)
        },
    }
})
