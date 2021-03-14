import request from '@/chaos/request';
import { ParamsType, PageParamsType } from '@/chaos/dataType';
import { message } from 'antd';
import { exportExcel } from '@/chaos/execl';

export async function add(domain: string, data: ParamsType = {}) {
    const hide = message.loading('正在添加');
    const res = await request('/manage/'.concat(domain).concat('/add'), {
        method: 'POST',
        data,
    });
    hide();
    if (res) {
        message.success('添加成功');
    }
    return res;
}

export async function update(domain: string, mu: string, data: ParamsType = {}) {
    const hide = message.loading('正在更新');
    const res = await request('/manage/'.concat(domain).concat('/update'), {
        method: 'POST',
        data: {
            mu,
            data
        },
    });
    hide();
    if (res) {
        message.success('更新成功');
    }
    return res;
}

export async function remove(domain: string, mu: string) {
    const hide = message.loading('正在删除');
    const res = await request('/manage/'.concat(domain).concat('/delete'), {
        method: 'POST',
        data: { mu },
    });
    hide();
    message.success('删除成功');
    return res;
}

export async function page(domain: string, params: PageParamsType) {
    const { current, pageSize, data } = params;
    const res = await request('/manage/'.concat(domain).concat('/page'), {
        method: 'POST',
        data: {
            pageNum: current, pageSize, data
        },
    });
    return res;
}

export async function list(domain: string, data: ParamsType = {}) {
    const res = await request('/manage/'.concat(domain).concat('/list'), {
        method: 'POST',
        data
    });
    return res;
}

export function one(domain: string, mu: string) {
    const res = request('/manage/'.concat(domain).concat('/one'), {
        method: 'POST',
        data: { mu },
    });
    return res;
}

export async function submit(method: string, data: ParamsType = {}) {
    const res = await request('/manage/'.concat(method), {
        method: 'POST',
        data
    });
    return res;
}

export async function search(method: string, params: PageParamsType) {
    const { current, pageSize, data } = params;
    const res = await request('/manage/'.concat(method), {
        method: 'POST',
        data: {
            pageNum: current, pageSize, data
        },
    });
    return res;

}

export async function query(method: string, data: ParamsType) {
    const res = await request('/manage/'.concat(method), {
        method: 'POST',
        data: data || {}
    });
    return res;
}


export async function searchExport(domain: string, header: any, filename: string = 'demo', data: ParamsType) {
    let current = 1; let res
    do {
        /* eslint-disable no-await-in-loop */
        res = await request('/manage/'.concat(domain), {
            method: 'POST',
            data: {
                pageNum: current, pageSize: 1000, data
            },
        });
        if (res.data) {
            exportExcel(header, res.data, filename.concat(".xlsx"))
            current += 1
        }
    } while (res.data);
    return true

}
