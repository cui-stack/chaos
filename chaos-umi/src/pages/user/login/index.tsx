import React, { useState } from 'react';
import { Checkbox } from 'antd';
import { Dispatch, connect } from 'umi';
import { StateType } from './model';
import { LoginParamsType } from './service';
import LoginForm from './components/LoginForm';
import styles from './style.less';

const { UserName, Password, Submit } = LoginForm;
interface LoginProps {
  dispatch: Dispatch;
  userAndlogin: StateType;
  submitting?: boolean;
}

const Login: React.FC<LoginProps> = (props) => {
  const { submitting } = props;
  const [autoLogin, setAutoLogin] = useState(true);

  const handleSubmit = (values: LoginParamsType) => {
    const { dispatch } = props;
    dispatch({
      type: 'userAndlogin/login',
      payload: {
        ...values,
      },
    });
  };
  return (
    <div className={styles.main}>
      <LoginForm onSubmit={handleSubmit}>
        <UserName
          name="userName"
          placeholder="用户名: data"
          rules={[
            {
              required: true,
              message: '请输入用户名!',
            },
          ]}
        />
        <Password
          name="password"
          placeholder="密码: data"
          rules={[
            {
              required: true,
              message: '请输入密码！',
            },
          ]}
        />
        <div>
          <Checkbox checked={autoLogin} onChange={(e) => setAutoLogin(e.target.checked)}>
            自动登录
          </Checkbox>
        </div>
        <Submit loading={submitting}>登录</Submit>

      </LoginForm>
    </div>
  );
};

export default connect(
  ({
    userAndlogin,
    loading,
  }: {
    userAndlogin: StateType;
    loading: {
      effects: {
        [key: string]: boolean;
      };
    };
  }) => ({
    userAndlogin,
    submitting: loading.effects['userAndlogin/login'],
  }),
)(Login);
