import React, { useState } from 'react';
import { View, WebView } from '@tarojs/components';
import styles from './index.module.scss';

const Webview: React.FC = () => {
    const [infoSrc] = useState<string>('');
    return (
        <View className={styles.page}>
            <WebView src={infoSrc} />
        </View>
    );
};

export default Webview;
