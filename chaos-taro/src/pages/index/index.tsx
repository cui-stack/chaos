import React from 'react'
import { connect } from 'react-redux'
import { View, Image } from '@tarojs/components'

import logo from '../../asserts/logo.ico'
import './index';

@connect(({ index }) => ({ ...index }))
class Index extends React.Component {

  onReady() {
    this.props.dispatch({
      type: 'index/list',
      payload: {
      },
    })
  }

  render() {
    const { list } = this.props

    return (
      <view>
        <View className='index'>
          <View className='index_head'>
            <Image src={logo}></Image>
          </View>
        </View >
      </view>
    )
  }
}

export default Index
