import React from 'react';
import classNames from 'classnames';
import { Form } from 'antd';
import { FormInstance } from 'antd/es/form';
import { LoginParamsType } from '../../service';
import LoginItem, { LoginItemProps } from './LoginItem';
import LoginSubmit from './LoginSubmit';

import styles from './index.less';

export interface LoginFormProps {
  style?: React.CSSProperties;
  className?: string;
  form?: FormInstance;
  children: any;
  onSubmit?: (values: LoginParamsType) => void;
}

interface LoginFormType extends React.FC<LoginFormProps> {
  Submit: typeof LoginSubmit;
  UserName: React.FunctionComponent<LoginItemProps>;
  Password: React.FunctionComponent<LoginItemProps>;
}

const LoginForm: LoginFormType = (props) => {
  const { className, children, form, onSubmit } = props;
  return (
    <div className={classNames(className, styles.login)}>
      <Form
        form={form}
        onFinish={(values) => {
          if (onSubmit) {
            onSubmit(values as LoginParamsType);
          }
        }}>
        {children}
      </Form>
    </div>
  );
};

LoginForm.Submit = LoginSubmit;
LoginForm.UserName = LoginItem.UserName;
LoginForm.Password = LoginItem.Password;


export default LoginForm;
