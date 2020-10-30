import React from 'react'
import { View } from '@tarojs/components'
import './index.scss';

export default class Tabs extends React.Component {

    render() {
        const { list, currentTabIndex, onTabsItemTap } = this.props
        return (
            <View className='tabs'>
                {
                    list.map((item, index) => {
                        let cn = 'tabs-item' + currentTabIndex == index && 'selected'
                        return (
                            <View key={index} className={cn} onClick={onTabsItemTap}>
                                { item}
                            </View>
                        )
                    })
                }
            </View >
        )
    }
}
