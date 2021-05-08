import React, { useState, useEffect } from 'react';
import { View } from '@tarojs/components';
import { list } from '@/chaos/functions/Data';
import { getParams } from '@/chaos/functions/Taro';
import { doLogin } from '@/utils/login';
import styles from './index.module.scss';

const Index: React.FC = () => {
  const [listData, setListData] = useState<[]>();
  useEffect(() => { load(); }, []);
  const load = async () => {
    setListData(await list('index'));
    const params = getParams();
    doLogin(params, true);
  };

  return (
    <View className={styles.page}>
    </View>
  );
};

export default Index;
