export function tableData(table, d) {
  return {
    table: table,
    currentPage: 1,
    limit: 20,
    total: 0,
    tableData: [],
    d
  }
}

export function formData(table, d) {
  return {
    table: table,
    form: {},
    d
  }
}

export default {
  tableData,
  formData
}
