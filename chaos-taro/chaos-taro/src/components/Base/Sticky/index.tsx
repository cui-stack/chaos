import React, { Component } from 'react'
import { View } from '@tarojs/components'
import './index.scss';

export default class Sticky extends Component {

    render() {
        return (
            <View className='sticky'>
                {this.props.children}
            </View >
        )
    }
}
