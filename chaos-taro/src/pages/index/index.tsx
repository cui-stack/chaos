import React, { useState, useEffect } from 'react';
import { View } from '@tarojs/components';
import { list } from '@/chaos/functions/Data';
import { navigateTo, getParams } from '@/chaos/functions/Taro';
import { doLogin } from '@/utils/login';
import './index.scss';

const Index: React.FC = () => {
  const [listData, setListData] = useState<[]>();
  useEffect(() => { load(); }, []);
  const load = async () => {
    setListData(await list('info'));
    const params = getParams();
    doLogin(params, true);
  };

  return (
    <View className='index'>
    </View>
  );
};

export default Index;
