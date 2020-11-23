export function dataData(table, form, updateForm, rules, rest) {
    return {
        table,
        currentPage: 1,
        limit: 20,
        total: 0,
        tableData: [],
        showAddForm: false,
        form,
        showUpdateForm: false,
        updateForm,
        rules,
        ...rest
    }
}

export function tableData(table, rest) {
    return {
        table,
        currentPage: 1,
        limit: 20,
        total: 0,
        tableData: [],
        ...rest
    }
}

export function formData(table, form, rules, rest) {
    return {
        table,
        form,
        showAddForm: false,
        rules,
        ...rest
    }

}

export function pushData(name, data) {
    return {
        name,
        params: data
    }
}

export function pushMu(name, mu) {
    return pushData(name, {mu})
}

export default {
    dataData,
    tableData,
    formData,
    pushData,
    pushMu
}
