Component({
    properties: {
        slottext: {
            type: String,
            value: ''
        },
        textClass: {
            type: String,
            value: ''
        },
        slotClass: {
            type: String,
            value: ''
        },
        text: {
            type: String,
            value: ''
        },
        sessionFrom: {
            type: String,
            value: ''
        },
        textStyle: {
            type: String,
            value: ''
        },
        slotStyle: {
            type: String,
            value: ''
        },
        btStyle: {
            type: String,
            value: ''
        },
    },
    data: {},
    methods: {
        handleContact(e) {
            // console.log(e.detail.path)
            //console.log(e.detail.query)
        },
    }
})
