Component({
    properties: {
        tabs: {
            type: Array,
            value: []
        },
        tabIndex: {
            type: Number,
            value: 0,
            observer: function (newVal, oldVal) {
                this.show(newVal)
            }
        },
    },
    data: {
        currentTabIndex: 0,
    },
    methods: {
        onTabsItemTap: function (event) {
            let index = event.currentTarget.dataset.index;
            this.setData({
                currentTabIndex: index
            })
            this.triggerEvent('currentTabIndex', index)
        },
        show: function (index) {
            this.setData({
                currentTabIndex: index
            })
        }
    }
})
