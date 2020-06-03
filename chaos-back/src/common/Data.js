import {Message} from 'element-ui';
import {MessageBox} from 'element-ui';
import fetch from '@/axios/fetch'

export function chain(a = []) {
  a.shift()(a)
}

export function search(table, method, pageNum, pageSize, d = {}, afterSearch) {
  const data = {
    pageNum: pageNum,
    pageSize: pageSize,
    data: d
  }
  fetch.post('/manage/' + table + '/' + method, data).then((res) => {
    afterSearch(res)
  })
}

export function page(table, pageNum, pageSize, d = {}, afterSearch) {
  const data = {
    pageNum: pageNum,
    pageSize: pageSize,
    data: d
  }
  fetch.post('/manage/' + table + '/page', data).then((res) => {
    afterSearch(res)
  })
}

export function list(table, d = {}, afterList) {
  fetch.post('/manage/' + table + '/list', d).then((res) => {
    afterList(res)
  })
}

export function one(table, mu, afterOne) {
  fetch.post('/manage/' + table + '/one', {mu: mu}).then((res) => {
    afterOne(res)
  })
}

export function add(table, d = {}, afterAdd) {
  fetch.post('/manage/' + table + '/add', d).then((res) => {
    afterAdd(res)
  })
}

export function update(table, mu, d = {}, afterUpdate) {
  const data = {
    mu: mu,
    data: d
  }
  fetch.post('/manage/' + table + '/update', data).then((res) => {
    afterUpdate(res)
  })
}

export function remove(table, mu, afterRemove) {
  MessageBox.confirm('确认删除吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
    center: true
  }).then(() => {
    fetch.post('/manage/' + table + '/delete', {mu: mu}
    ).then((res) => {
      afterRemove(res)
    })
  }).catch(() => {
    Message({
      type: 'info',
      message: '已取消删除'
    });
  });
}

export default {
  search,
  page,
  list,
  one,
  add,
  update,
  remove,
  chain
}
