import React, {Component, Suspense} from 'react';
import {connect, Dispatch} from 'umi';
import {GridContent} from '@ant-design/pro-layout';
import PageLoading from './components/PageLoading';

import {AnalysisData} from './data.d';

const IntroduceRow = React.lazy(() => import('./components/IntroduceRow'));

interface AnalysisProps {
  dashboardAndanalysis: AnalysisData;
  dispatch: Dispatch;
  loading: boolean;
}

interface AnalysisState {
  salesType: 'all' | 'online' | 'stores';
  currentTabKey: string;
}

class Analysis extends Component<AnalysisProps, AnalysisState> {
  state: AnalysisState = {
    salesType: 'all',
    currentTabKey: '',
  };

  reqRef: number = 0;
  timeoutId: number = 0;

  componentDidMount() {
    const {dispatch} = this.props;
    this.reqRef = requestAnimationFrame(() => {
      dispatch({
        type: 'dashboardAndanalysis/fetch',
      });
    });
  }

  componentWillUnmount() {
    const {dispatch} = this.props;
    dispatch({
      type: 'dashboardAndanalysis/clear',
    });
    cancelAnimationFrame(this.reqRef);
    clearTimeout(this.timeoutId);
  }

  render() {
    const {dashboardAndanalysis, loading} = this.props;
    const {
      userData,
      phoneData,
      registerDatas,
      orderData
    } = dashboardAndanalysis;
    return (
      <GridContent>
        <React.Fragment>
          <Suspense fallback={<PageLoading/>}>
            <IntroduceRow loading={loading}
                          userData={userData} phoneData={phoneData}
                          registerDatas={registerDatas} orderData={orderData}/>
          </Suspense>
        </React.Fragment>
      </GridContent>
    );
  }
}

export default connect(
  ({
     dashboardAndanalysis,
     loading,
   }: {
    dashboardAndanalysis: any;
    loading: {
      effects: { [key: string]: boolean };
    };
  }) => ({
    dashboardAndanalysis,
    loading: loading.effects['dashboardAndanalysis/fetch'],
  }),
)(Analysis);
