import React from 'react';
import { Redirect, connect, ConnectProps } from 'umi';
import { PageLoading } from '@ant-design/pro-layout';

import { ConnectState } from '@/models/connect';
import { CurrentUser } from '@/models/user';

interface SecurityLayoutProps extends ConnectProps {
  loading?: boolean;
  currentUser?: CurrentUser;
}

interface SecurityLayoutState {
  isReady: boolean;
}

class SecurityLayout extends React.Component<SecurityLayoutProps, SecurityLayoutState> {
  state: SecurityLayoutState = {
    isReady: false,
  };

  componentDidMount() {
    this.setState({
      isReady: true,
    });

  }

  render() {
    const { isReady } = this.state;
    const { children, loading, } = this.props;
    const user = localStorage.getItem('user')

    const isLogin = !!user

    if ((!isLogin && loading) || !isReady) {
      return <PageLoading />;
    }
    if (!isLogin && window.location.pathname !== '/user/login') {
      return <Redirect to="/user/login" />;
    }
    return children;
  }
}

export default connect(({ user, loading }: ConnectState) => ({
  currentUser: user.currentUser,
  loading: loading.models.user,
}))(SecurityLayout);
