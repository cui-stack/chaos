import {Message, MessageBox} from 'element-ui';
import fetch from "../axios/fetch";

async function add(domain, data = {}) {
    const res = await fetch.post('/manage/' + domain + '/add', data)
    return res
}

function remove(domain, mu) {
    MessageBox.confirm('确认删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
    }).then(async () => {
        const res = await fetch.post('/manage/' + domain + '/delete', {mu})
        return res
    }).catch(() => {
        Message({
            type: 'info',
            message: '已取消删除'
        });
    });
}

async function update(domain, mu, data = {}) {
    const res = await fetch.post('/manage/' + domain + '/update', {mu, data})
    return res
}

async function one(domain, mu) {
    const res = await fetch.post('/manage/' + domain + '/one', {mu})
    return res
}

async function list(domain, data = {}) {
    const res = await fetch.post('/manage/' + domain + '/list', data)
    return res
}

async function page(domain, pageNum = 1, pageSize = 20, data = {}) {
    const res = await fetch.post('/manage/' + domain + '/page', {
        pageNum,
        pageSize,
        data
    })
    return res
}

async function search(method, pageNum = 1, pageSize = 20, data = {}) {
    const res = await fetch.post('/manage/' + method, {
        pageNum,
        pageSize,
        data
    })
    return res
}

async function query(method, data = {}) {
    const res = await fetch.post('/manage/' + method, data)
    return res
}

function submit(method, data) {
    MessageBox.confirm('确认提交吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
    }).then(async () => {
        const res = await fetch.post('/manage/' + method, data)
        return res
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
