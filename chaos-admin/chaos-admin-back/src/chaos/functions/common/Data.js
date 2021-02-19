import {Message, MessageBox} from 'element-ui';
import fetch from "../axios/fetch";

async function add(domain, data = {}) {
    const res = await fetch.post('/manage/' + domain + '/add', data)
    Message({
        type: 'success',
        message: '新增成功!'
    });
    return res
}

function remove(domain, mu, callback) {
    MessageBox.confirm('确认删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
    }).then(async () => {
        const res = fetch.post('/manage/' + domain + '/delete', {mu})
        if (res) {
            Message({
                type: 'success',
                message: '删除成功!'
            });
            callback()
        } else {
            Message({
                type: 'warning',
                message: '删除失败!'
            });
        }
    }).catch(() => {
        Message({
            type: 'info',
            message: '已取消删除'
        });
    });
}

async function update(domain, mu, data = {}) {
    const res = await fetch.post('/manage/' + domain + '/update', {mu, data})
    if (res) {
        Message({
            type: 'success',
            message: '修改成功!'
        });
    } else {
        Message({
            type: 'warning',
            message: '修改失败!'
        });
    }
    return res
}

function one(domain, mu) {
    return fetch.post('/manage/' + domain + '/one', {mu})
}

function list(domain, data = {}) {
    return fetch.post('/manage/' + domain + '/list', data)
}

function page(domain, pageNum = 1, pageSize = 20, data = {}) {
    return fetch.post('/manage/' + domain + '/page', {
        pageNum,
        pageSize,
        data
    })
}

function search(method, pageNum = 1, pageSize = 20, data = {}) {
    return fetch.post('/manage/' + method, {
        pageNum,
        pageSize,
        data
    })
}

function query(method, data = {}) {
    return fetch.post('/manage/' + method, data)
}

function submit(method, data, callback) {
    MessageBox.confirm('确认提交吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
    }).then(async () => {
        const res = await fetch.post('/manage/' + method, data)
        if (res) {
            callback()
        }
    }).catch(() => {
        Message({
            type: 'info',
            message: '已取消提交'
        });
    });
}

function validate(form, formName, f) {
    form.$refs[formName].validate((valid) => {
        if (valid) {
            f()
        } else {
            return false;
        }
    });
}

export default {
    add,
    remove,
    update,
    one,
    list,
    page,

    validate,
    //分页通用查询
    search,
    //无分页通用查询
    query,
    //提交
    submit,

}
