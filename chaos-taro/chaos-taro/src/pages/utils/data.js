import request from './request'


function add(table, d = {}) {
   return request('/api/' + table + '/add', d, ).data
}

function remove(table, mu) {
  return  request({
        url: '/api/' + table + '/remove',
        data: {
            mu: mu
        }
    })
    
}

function update(table, mu, d = {}) {
  return  request({
        url: '/api/' + table + '/update',
        data: {
            mu: mu,
            data: d
        }
    })
    
}

function one(table, data={}) {
    let d
    if ((typeof data) == 'string') {
        d = {
            mu: data
        }
    } else if ((typeof data) == 'object') {
        d = data
    }
    return    request({
        url: '/api/' + table + '/one',
        data: d
    })

}

function list(table, data = {}) {
     return request({
        url: '/api/' + table + '/list',
        data: data
     })
    
    
}

function page(table, d = {}, pageNum = 1, pageSize = 15) {
    const data = {
        pageNum: pageNum,
        pageSize: pageSize,
        data: d
    }
   return request({
        url: '/api/' + table + '/page',
        data: data
    })
    
}

function search(uri, d = {}, pageNum = 1, pageSize = 15 ) {
    const data = {
        pageNum: pageNum,
        pageSize: pageSize,
        data: d
    }
    return request({
        url: '/api/' + uri,
        data: data
    })
}

function query(uri, data = {}) {
    return request({
        url: '/api/' + uri,
        data: data
    })
}

function submit(uri, data = {}) {
    return request({
        url:  uri,
        data: data
    })
}


export default {
    add,
    remove,
    update,
    one,
    list,
    page,

    search,
    submit,
    query,
 
}

 