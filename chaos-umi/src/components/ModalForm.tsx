import React from 'react';
import { Modal } from 'antd';

interface FormProps {
  modalVisible: boolean;
  title: string;
  onCancel: () => void;
}

const ModalForm: React.FC<FormProps> = (props) => {
  const { modalVisible, title, onCancel } = props;

  return (
    <Modal
      destroyOnClose
      title={title}
      visible={modalVisible}
      onCancel={() => onCancel()}
      footer={null}
    >
      {props.children}
    </Modal>
  );
};

export default ModalForm;
