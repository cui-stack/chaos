import React, { useState } from 'react';
import { AtInput, AtIcon } from 'taro-ui'
import { View } from '@tarojs/components';
import './index.scss';

const SearchInput: React.FC = (props) => {
  const { doCancel, input, confirm, placeholder } = props
  const [value, setValue] = useState<string>('');
  const doChange = (v, e) => {
    console.log(v, e)
    setValue(v);
  }
  return (
    <View className='search'>
      <View className='search_left'>
        <AtIcon value='search' size='25px' color='#808080' />
        <AtInput name='value' value={value} placeholder={placeholder} placeholderClass='input-placeholder' confirmType='search' focus onClick={input} onConfirm={confirm} onChange={doChange} />
      </View>
      <View className='search_right' onClick={doCancel}>取消</View>
    </View>
  );
};

export default SearchInput;
