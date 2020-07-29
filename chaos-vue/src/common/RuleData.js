export function data(datas = []) {
  let rules = {}
  for (let index in datas) {
    let msg = datas[index]['msg']
    let min = datas[index]['min']
    let max = datas[index]['max']
    rules[datas[index]['key']] = v(msg, min, max)
    if (datas[index]['validator'])
      rules[datas[index]['key']].push(validator(datas[index]['validator']))
  }
  return rules
}

function v(msg, min = 6, max = 16) {
  return [
    {required: true, message: msg, trigger: 'blur'},
    {
      min: min,
      max: max,
      message: '长度在' + min + '到' + max + '个字符',
      trigger: 'change'
    }
  ]
}

function validator(validator) {
  return {validator: validator, trigger: 'blur'}
}

export default {
  data
}
