import {Message} from 'element-ui';
import {MessageBox} from 'element-ui';
import fetch from '@/axios/fetch'

/*单表*/
/**
 * 单表增
 * @param table
 * @param d
 * @param cb
 */
export function add(table, d = {}, cb) {
  fetch.post('/manage/' + table + '/add', d).then((res) => {
    doCb(cb, res)
  })
}

/**
 * 单表逻辑删除
 * @param table
 * @param mu
 * @param cb
 */
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

/**
 * 单表改
 * @param table
 * @param mu
 * @param d
 * @param cb
 */
export function update(table, mu, d = {}, cb) {
  const data = {
    mu: mu,
    data: d
  }
  fetch.post('/manage/' + table + '/update', data).then((res) => {
    doCb(cb, res)
  })
}

/**
 * 单表根据主键查询
 * @param table
 * @param mu
 * @param cb
 */
export function one(table, mu, cb) {
  fetch.post('/manage/' + table + '/one', {mu: mu}).then((res) => {
    doCb(cb, res)
  })
}

/**
 * 单表列表查询
 * @param table
 * @param d
 * @param cb
 */
export function list(table, d = {}, cb) {
  fetch.post('/manage/' + table + '/list', d).then((res) => {
    doCb(cb, res)
  })
}

/**
 * 单表分页查询
 * @param table
 * @param pageNum
 * @param pageSize
 * @param d
 * @param cb
 */
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

/**
 * 自定义分页查询
 * @param table
 * @param method
 * @param pageNum
 * @param pageSize
 * @param d
 * @param cb
 */
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

/**
 * 自定义列表查询
 * @param table
 * @param method
 * @param data
 * @param cb
 */
export function query(table, method, data = {}, cb) {
  fetch.post('/manage/' + table + '/' + method, data).then((res) => {
    doCb(cb, res)
  })
}

/**
 * 自定义提交
 * @param table
 * @param method
 * @param data
 * @param cb
 */
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

/**
 * 验证后操作
 * @param form
 * @param formName
 * @param f ?add/update/submit
 */
export function validate(form, formName, f) {
  form.$refs[formName].validate((valid) => {
    if (valid) {
      f()
    } else {
      return false;
    }
  });
}

/**
 * 回调标准处理
 * 存在回调,转交业务处理,否则提示执行信息
 * @param cb
 * @param res
 */
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

/**
 * 顺序执行
 * @param a
 */
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
