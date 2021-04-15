import React, { useState } from 'react';
import { View, WebView } from '@tarojs/components';
import './index.scss';

const Webview: React.FC = () => {
    const [infoSrc] = useState<string>('');
    return (
        <View className='webview'>
            <WebView src={infoSrc} />
        </View>
    );
};

export default Webview;
