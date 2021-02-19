export function data(datas = []) {
    let rules = {}
    for (let index in datas) {
        let data = datas[index]
        rules[data['key']] = v(data['msg'], data['min'], data['max'])
        if (data['validator'])
            rules[data['key']].push(validator(data['validator']))
    }
    return rules
}

function v(msg, min = 6, max = 16) {
    return [
        {required: true, message: msg, trigger: 'blur'},
        {
            min,
            max,
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
