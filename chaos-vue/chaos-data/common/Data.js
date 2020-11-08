import {Message} from 'element-ui';
import {MessageBox} from 'element-ui';
import fetch from "../axios/fetch";

export function add(table, d = {}, cb) {
    fetch.post('/manage/' + table + '/add', d).then((res) => {
        doCb(cb, res)
    })
}

export function remove(table, mu, cb) {
    MessageBox.confirm('确认删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
    }).then(() => {
        fetch.post('/manage/' + table + '/delete', {mu: mu}
        ).then((res) => {
            doCb(cb, res)
        })
    }).catch(() => {
        Message({
            type: 'info',
            message: '已取消删除'
        });
    });
}

export function update(table, mu, d = {}, cb) {
    const data = {
        mu: mu,
        data: d
    }
    fetch.post('/manage/' + table + '/update', data).then((res) => {
        doCb(cb, res)
    })
}

export function one(table, mu, cb) {
    fetch.post('/manage/' + table + '/one', {mu: mu}).then((res) => {
        doCb(cb, res)
    })
}

export function list(table, d = {}, cb) {
    fetch.post('/manage/' + table + '/list', d).then((res) => {
        doCb(cb, res)
    })
}

export function page(table, pageNum = 1, pageSize = 20, d = {}, cb) {
    const data = {
        pageNum: pageNum,
        pageSize: pageSize,
        data: d
    }
    fetch.post('/manage/' + table + '/page', data).then((res) => {
        doCb(cb, res)
    })
}

export function search(table, method, pageNum = 1, pageSize = 20, d = {}, cb) {
    const data = {
        pageNum: pageNum,
        pageSize: pageSize,
        data: d
    }
    fetch.post('/manage/' + table + '/' + method, data).then((res) => {
        doCb(cb, res)
    })
}

export function query(table, method, data = {}, cb) {
    fetch.post('/manage/' + table + '/' + method, data).then((res) => {
        doCb(cb, res)
    })
}

export function submit(table, method, data, cb) {
    MessageBox.confirm('确认提交吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
    }).then(() => {
        fetch.post('/manage/' + table + '/' + method, {data: data}
        ).then((res) => {
            doCb(cb, res)
        })
    }).catch(() => {
        Message({
            type: 'info',
            message: '已取消提交'
        });
    });
}

export function validate(form, formName, f) {
    form.$refs[formName].validate((valid) => {
        if (valid) {
            f()
        } else {
            return false;
        }
    });
}

function doCb(cb, res) {
    if (cb) {
        cb(res)
    } else {
        Message({
            type: 'info',
            message: res.msg
        });
    }
}

export function chain(a = []) {
    a.shift()(a)
}

export default {
    add,
    remove,
    update,
    one,
    list,
    page,

    search,
    query,
    validate,
    submit,

    chain,

}
