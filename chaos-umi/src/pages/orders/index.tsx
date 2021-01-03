import React, { useState, useEffect, useRef } from 'react';

import { PageContainer, } from '@ant-design/pro-layout';
import ProTable, { ProColumns, ActionType } from '@ant-design/pro-table';
import { Card, Col, Row, Button } from 'antd';
import type { FormInstance } from 'antd/lib/form';
import moment from "moment";
import { page, list, submit } from '@/services/data'
import styles from './index.less';

export interface OrderItem {
  platformTitle: string;
  orderCode: string;
  itemTitle: string;
  name: string;
  phone: string;
  payTime: string;
  cost: number;
}

export interface Count {
  todayBuyer: number;
  todayCost: number;
  todayOrder: number;
  totalBuyer: number;
  totalCost: number;
  totalOrder: number;
}

const Orders: React.FC = () => {
  const [valueEnum, setValueEnum] = useState<any>()
  const [count, setCount] = useState<Count>()
  const [title, setTitle] = useState<string>('全部')
  const [payTimeParam, setPayTimeParam] = useState<any>({ startTime: '', endTime: '' })
  const [platformMuParam, setPlatformMuParam] = useState<string>()
  const [timeTitle, setTimeTitle] = useState<string>('')

  useEffect(() => {
    list('AdminPlatform', {}, (res: any) => {
      const platList: object = {};
      res.forEach((item: any) => {
        platList[item.platformMu] = {
          text: item.platformTitle,
          value: item.platformMu,
        }
      });
      setValueEnum(platList);
    })
  }, []);

  useEffect(() => {
    submit('order/count', {
      startTime: payTimeParam.startTime,
      endTime: payTimeParam.endTime,
      platformMu: platformMuParam
    }, (res: Count) => {
      const { todayCost, totalCost, ...rest } = res
      setCount({ todayCost: todayCost / 100, totalCost: totalCost / 100, ...rest })
    })
  }, [payTimeParam, platformMuParam]);


  const formRef = useRef<FormInstance>();
  const actionRef = useRef<ActionType>();

  const handleClick = (day: number) => {
    const now = moment(new Date());
    const beforeDay = moment(new Date()).add(-day, 'days');
    const { current } = formRef
    if (!current) return
    current.setFieldsValue({
      payTime: [beforeDay, now]
    });
    current.submit();
    setTimeTitle('最近'.concat(String(day), '天'))
    actionRef.current?.reload();
  };

  const columns: ProColumns<OrderItem>[] = [
    {
      title: '渠道',
      dataIndex: 'platformTitle',
      valueType: 'select',
      valueEnum
    },
    {
      title: '订单号',
      dataIndex: 'orderCode',
      search: false,
    },
    {
      title: '商品',
      dataIndex: 'itemTitle',
      search: false,
    },
    {
      title: '用户名',
      dataIndex: 'name',
      search: false,
    },
    {
      title: '手机号',
      dataIndex: 'phone',
      search: false,
    },
    {
      title: '支付时间',
      dataIndex: 'payTime',
      valueType: 'dateRange',
      search: {
        transform: (value: any) => ({ startTime: value[0], endTime: value[1] })
      },
      render: (_, entity) => {
        return entity.payTime
      },
    },
    {
      title: '支付金额',
      dataIndex: 'cost',
      search: false,
      render: (_, entity) => {
        return entity.cost / 100
      },
    }
  ];

  return (
    <PageContainer>
      <div className={styles.card}>
        <Row gutter={24}>
          <Col span={8}>
            <Card title="购买人数" bordered={false}>
              今日人数: {count?.todayBuyer}<br />
              累计人数: {count?.totalBuyer}
            </Card>
          </Col>
          <Col span={8}>
            <Card title="购买订单" bordered={false}>
              今日订单: {count?.todayOrder}<br />
              累计订单: {count?.totalOrder}
            </Card>
          </Col>
          <Col span={8}>
            <Card title="购买金额" bordered={false}>
              今日总额: {count?.todayCost}<br />
              累计总额: {count?.totalCost}
            </Card>
          </Col>
        </Row>
      </div>
      <ProTable<OrderItem>
        actionRef={actionRef}
        formRef={formRef}
        rowKey="orderCode"
        search={{
          labelWidth: 120,
        }}
        request={(params: any) => {
          const { current, platformTitle, ...rest } = params
          setTitle(platformTitle ? valueEnum[platformTitle].text : '')
          const { startTime, endTime } = rest
          if (startTime || endTime)
            setPayTimeParam({ startTime, endTime });
          setPlatformMuParam(platformTitle)
          return page('order', { pageNum: current, platformMu: platformTitle, ...rest })
        }}
        columns={columns}
        toolBarRender={() => [
          <>
            <div key="title" className={styles.title}>
              {title}{timeTitle}
            </div>
            <Button type="primary" key="primary" onClick={() => { handleClick(7) }}>
              最近7天
            </Button>
            <Button type="primary" key="primary" onClick={() => { handleClick(30) }}>
              最近30天
            </Button>
            <Button type="primary" key="primary" onClick={() => { handleClick(90) }}>
              最近90天
            </Button>
          </>
        ]}
      />
    </PageContainer>
  );
};

export default Orders;
