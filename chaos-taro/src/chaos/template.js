 /**
  * pages模版快速生成脚本,执行命令 npm run tep `文件名`
  */

 const fs = require('fs');

 const dirName = process.argv[2];

 if (!dirName) {
   console.log('文件夹名称不能为空！');
   console.log('示例：npm run tep test');
   process.exit(0);
 }

 // 页面模版
const indexTep = `
import Taro from '@tarojs/taro'
import React, { Component } from 'react'
import { connect } from 'react-redux'
import { View } from '@tarojs/components'
import './index.scss'

@connect(({${dirName}}) => ({ ...${dirName}}))
export default class ${titleCase(dirName)} extends Component {
  onReady() {
    this.dispatch('list', {})
  }
  dispatch = (domain, data) => {
    this.props.dispatch({
      type: '${dirName}/' + domain,
      payload: data,
    })
  }

  render() {
    const { list } = this.state
    return (
      <View className='${dirName}'>
      </View>
    )
  }
}
`;

 // scss文件模版
const scssTep = `
@import "@/styles/app";

.${dirName} {
  margin:0;
}
`;

 // model文件模版
const modelTep = `
import { list, } from './../../utils/mservice';

export default {
  namespace: '${dirName}',
  state: {
    list: []
  },

  effects: {
    *list({ payload }, { call, put }) {
            const response = yield call(list, '${dirName}', payload);
            yield put({
                type: 'listed',
                payload: response,
            });
    }

  },

  reducers: {
    listed(state, { payload }) {
            state.list = payload
            return {
                ...state,
            };
        },
  },

};
`;


 // service页面模版
const serviceTep = `
import Data from './../../utils/data'

`;



 fs.mkdirSync(`./src/pages/${dirName}`); // mkdir $1
 process.chdir(`./src/pages/${dirName}`); // cd $1

 fs.writeFileSync('index.tsx', indexTep);
 fs.writeFileSync('index.module.scss', scssTep);
 fs.writeFileSync('user.ts', modelTep);
 fs.writeFileSync('service.ts', serviceTep);

 console.log(`模版${dirName}已创建,请手动增加models`);

 function titleCase(str) {
   const array = str.toLowerCase().split(' ');
   for (let i = 0; i < array.length; i++) {
     array[i] = array[i][0].toUpperCase() + array[i].substring(1, array[i].length);
   }
   const string = array.join(' ');
   return string;
 }

 process.exit(0);
