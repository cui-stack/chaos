import React from "react";
import { connect, Dispatch } from "umi";
import { Alert } from "antd";
import { ParamsType } from "@/chaos/functions/dataType";
import { ConnectState } from "@/models/connect";
import { UserModelState } from "@/models/user";
import { MobileTwoTone } from "@ant-design/icons";
import ProForm, { ProFormText } from "@ant-design/pro-form";
import styles from "./index.less";

const LoginMessage: React.FC<{ content: string }> = ({ content }) => (
  <Alert style={{ marginBottom: 24 }} message={content} type="error" showIcon />
);

export interface LoginParamsType extends ParamsType {
  username: string;
  password: string;
}

const Login: React.FC<{
  dispatch: Dispatch;
  current: UserModelState;
  submitting?: boolean;
}> = (props) => {
  const { current = {}, submitting, dispatch } = props;
  const { status } = current;

  const handleSubmit = (data: LoginParamsType) => {
    dispatch({ type: "user/login", payload: { ...data } });
  };
  return (
    <div className={styles.main}>
      <ProForm
        initialValues={{ autoLogin: true }}
        submitter={{
          render: (_, dom) => dom.pop(),
          submitButtonProps: {
            loading: submitting,
            size: "large",
            style: { width: "100%" },
          },
        }}
        onFinish={async (values) => { handleSubmit(values); }}
      >
        {status === "error" && !submitting && (<LoginMessage content="用户名或密码错误" />)}
        <ProFormText
          fieldProps={{ size: "large", prefix: <MobileTwoTone className={styles.prefixIcon} />, }}
          name="username"
          placeholder="请输入用户名"
          rules={[{ required: true, message: "请输入用户名！", },]}
        />
        <ProFormText.Password
          fieldProps={{ size: "large", prefix: <MobileTwoTone className={styles.prefixIcon} />, }}
          name="password"
          placeholder="密码"
          rules={[{ required: true, message: "请输入密码", },]}
        />
      </ProForm>
    </div>
  );
};

export default connect(({ user, loading }: ConnectState) => ({
  current: user,
  submitting: loading.effects["user/login"],
}))(Login);
