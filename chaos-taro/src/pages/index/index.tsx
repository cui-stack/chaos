import logo from '@/asserts/logo.png';
import React, { useState, useEffect } from 'react';
import { View, Image } from '@tarojs/components';
import { AtIcon } from 'taro-ui';
import { list } from '@/chaos/functions/Data';
import { navigateTo, getParams } from '@/chaos/functions/Taro';
import { doLogin } from '@/utils/login';
import './index.scss';

const Index: React.FC = () => {
  const [listData, setListData] = useState<[]>();
  useEffect(() => { load(); }, []);
  const load = async () => {
    setListData(await list('iya_info'));
    const params = getParams();
    console.log(params)
    doLogin(params, true);
  };
  const goDoctors = () => { navigateTo('doctors') }
  const goWebview = (mu: string) => { navigateTo('webview', `mu=${mu}`) }
  const goCitys = () => { navigateTo('citys') }
  const goOrders = () => { navigateTo('orders') }

  return (
    <View className='index'>
      <View className='index_head'>
        <Image src={logo} />
      </View>
      <View className='index_body'>
        <View className='body_search' onClick={() => goDoctors()}>
          <View >医生姓名/症状/自锁/隐适美/RW</View>
          <AtIcon value='search' size='20' color='#A6A6A6' />
        </View>
        <View className='body_list'>
          {listData &&
            listData.map((item: any, index) => {
              return (
                <View className='list_item' key={index} onClick={() => goWebview(item.mu)}>
                  {item.title}
                </View>
              );
            })}
        </View>
      </View>
      <View className='index_foot'>
        <View className='foot_bt' onClick={() => goCitys()} >按城市看</View>
        <View>|</View>
        <View className='foot_bt' onClick={() => goOrders()} >我的预约</View>
      </View>
    </View>
  );
};

export default Index;
