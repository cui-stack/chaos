let page = require('./../../utils/page')
Component({
    properties: {},
    data: {},
    methods: {
        goCity: function () {
            page.to('citylist')
        },
        goOrder: function () {
            page.to('booklist')
        }
    }
})
