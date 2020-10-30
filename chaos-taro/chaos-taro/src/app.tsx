import React, { Component } from 'react'
import Taro from '@tarojs/taro';
import { Provider } from 'react-redux'
import dva from './utils/dva';
import models from './models';
import 'taro-ui/dist/style/index.scss'
import './styles/app.scss';
import data from './utils/data'

const dvaApp = dva.createApp({

  initialState: {},
  models,

});

const store = dvaApp.getStore();

class App extends Component {

  componentWillMount() {
    this.wxLogin()
  }
  wxLogin = () => {
    Taro.login()
      .then(response => {
        if (response.errMsg === 'login:ok')
          this.login(response.code)
      })
      .catch(err => {
        Taro.showToast({
          title: '发生错误，请重启!',
          icon: 'none'
        })
      })
  }

  login = (code) => {
    data.submit('/wxmini/login', {
      code: code,
      referrer: Taro.getStorageSync('referrer'),
      source: Taro.getStorageSync('source')
    }).then((res) => {
      Taro.setStorageSync('token', res.token)
      Taro.setStorageSync('mu', res.mu)
      this.location()
    })

  }

  location = () => {
    Taro.getLocation({
      type: 'gcj02',
      success(res) {
        Taro.setStorageSync('location', res.longitude + ';' + res.latitude)
        data.update('chaos_user', Taro.getStorageSync('mu'), {
          lat: res.latitude,
          lng: res.longitude
        })
      }
    })
  }

  render() {
    return (
      <Provider store={store} >
        {this.props.children}
        </Provider>
    )
  }

}

export default App
