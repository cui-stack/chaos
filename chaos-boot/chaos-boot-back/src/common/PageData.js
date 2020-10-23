export function tableData(table, d) {
  let r = {
    table: table,
    currentPage: 1,
    limit: 20,
    total: 0,
    tableData: [],
  }

  for (var i in d) {
    r[i] = d[i];
  }
  return r
}

export function formData(table, d) {
  let r = {
    table: table,
    form: {},
  }
  for (let i in d) {
    r[i] = d[i];
  }
  return r
}

export function pushData(name, d) {
  return {
    name: name,
    params: d
  }
}

export function pushMu(name, mu) {
  return pushData(name, {mu: mu})
}

export default {
  tableData,
  formData,
  pushData,
  pushMu
}
