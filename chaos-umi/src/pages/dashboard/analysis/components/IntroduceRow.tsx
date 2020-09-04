import {InfoCircleOutlined} from '@ant-design/icons';
import {Col, Row, Tooltip} from 'antd';
import React from 'react';
import numeral from 'numeral';
import {ChartCard, MiniArea, MiniBar, MiniProgress, Field} from './Charts';
import {
  OrderDataType,
  PhoneDataType,
  UserDataType,
  XyDataType
} from '../data.d';
import Trend from './Trend';
import styles from '../style.less';

const topColResponsiveProps = {
  xs: 24,
  sm: 12,
  md: 12,
  lg: 12,
  xl: 6,
  style: {
    marginBottom: 24,
  },
};

const IntroduceRow = ({loading, userData, phoneData, registerDatas, orderData}:
                        {
                          loading: boolean; userData: UserDataType;
                          phoneData: PhoneDataType; registerDatas: XyDataType[];
                          orderData: OrderDataType
                        }) => (
  <Row gutter={24}>
    <Col {...topColResponsiveProps}>
      <ChartCard
        bordered={false}
        title="总注册人数"
        action={
          <Tooltip title="累计注册人数">
            <InfoCircleOutlined/>
          </Tooltip>
        }
        loading={loading}
        total={numeral(userData.total).format('0,0')}
        footer={<Field label="总有效人数" value={phoneData.total}/>}
        contentHeight={46}
      >
        <Trend
          flag="up"
          style={{
            marginRight: 16
          }}
        >
          周同比
          <span className={styles.trendText}>{userData.wyoy}%</span>
        </Trend>
        <Trend flag="down">
          日同比
          <span className={styles.trendText}>{userData.dyoy}%</span>
        </Trend>
      </ChartCard>
    </Col>

    <Col {...topColResponsiveProps}>
      <ChartCard
        bordered={false}
        loading={loading}
        title="今日注册"
        action={
          <Tooltip title="今日注册用户">
            <InfoCircleOutlined/>
          </Tooltip>
        }
        total={numeral(userData.todayTotal).format('0,0')}
        footer={<Field label="日访问量" value={numeral(1234).format('0,0')}/>}
        contentHeight={46}
      >
        <MiniArea color="#975FE4" data={registerDatas}/>
      </ChartCard>
    </Col>
    <Col {...topColResponsiveProps}>
      <ChartCard
        bordered={false}
        loading={loading}
        title="总预约人数"
        action={
          <Tooltip title="预约下单人数">
            <InfoCircleOutlined/>
          </Tooltip>
        }
        total={numeral(orderData.total).format('0,0')}
        footer={<Field label="今日预约人数" value={orderData.todayTotal}/>}
        contentHeight={46}
      >
        <MiniBar data={[]}/>
      </ChartCard>
    </Col>
    <Col {...topColResponsiveProps}>
      <ChartCard
        loading={loading}
        bordered={false}
        title="成交数据"
        action={
          <Tooltip title="指标说明">
            <InfoCircleOutlined/>
          </Tooltip>
        }
        total="78%"
        footer={
          <div
            style={{
              whiteSpace: 'nowrap',
              overflow: 'hidden',
            }}
          >
            <Trend
              flag="up"
              style={{
                marginRight: 16,
              }}
            >
              周同比
              <span className={styles.trendText}>12%</span>
            </Trend>
            <Trend flag="down">
              日同比
              <span className={styles.trendText}>11%</span>
            </Trend>
          </div>
        }
        contentHeight={46}
      >
        <MiniProgress percent={78} strokeWidth={8} target={80} color="#13C2C2"/>
      </ChartCard>
    </Col>
  </Row>
);

export default IntroduceRow;
