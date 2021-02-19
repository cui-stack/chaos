import {Message} from 'element-ui';
import {MessageBox} from 'element-ui';

export function success(text) {
    Message({
        type: 'success',
        message: text
    });
}

export function info(text) {
    Message({
        type: 'info',
        message: text
    });
}

export function warn(doCall) {
    MessageBox.confirm('确认执行吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
    }).then(() => {
        doCall()
    }).catch(() => {
        Message({
            type: 'info',
            message: '已取消提交'
        });
    });

}

export default {
    info,
    success,
    warn
}
