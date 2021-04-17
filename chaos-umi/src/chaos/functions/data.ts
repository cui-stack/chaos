import request from '@/chaos/functions/request';
import type { ParamsType, PageParamsType } from '@/chaos/functions/dataType';
import { message } from 'antd';
import { exportExcel } from '@/chaos/functions/execl';

export async function add(domain: string, data: ParamsType = {}) {
    message.loading('正在添加');
    const res = await request(`/manage/${domain}/add`, { data });
    if (res) {
        message.success('添加成功');
    }
    return res;
}

export async function update(domain: string, mu: string, data: ParamsType = {}) {
    message.loading('正在更新');
    const res = await request(`/manage/${domain}/update`, { data: { mu, data }, });
    if (res) {
        message.success('更新成功');
    }
    return res;
}

export async function remove(domain: string, mu: string) {
    message.loading('正在删除');
    const res = await request(`/manage/${domain}/delete`, { data: { mu }, });
    if (res) {
        message.success('删除成功');
    }
    return res;
}

export async function page(domain: string, params: PageParamsType) {
    const { current: pageNum, pageSize, data } = params;
    return await request(`/manage/${domain}/page`, { data: { pageNum, pageSize, data }, });
}

export async function list(domain: string, data: ParamsType = {}) {
    return await request(`/manage/${domain}/list`, { data });
}

export async function one(domain: string, mu: string) {
    return await request(`/manage/${domain}/one`, { data: { mu }, })
}

export async function submit(method: string, data: ParamsType = {}) {
    return await request(`/manage/${method}`, { data });
}

export async function search(method: string, params: PageParamsType) {
    const { current: pageNum, pageSize, data } = params;
    return await request(`/manage/${method}`, { data: { pageNum, pageSize, data }, });
}

export async function query(method: string, data: ParamsType) {
    return await request(`/manage/${method}`, { data });
}


export async function searchExport(method: string, header: any, filename: string = 'demo', data: ParamsType) {
    let pageNum = 1; let res
    do {
        /* eslint-disable no-await-in-loop */
        res = await request(`/manage/${method}`, { data: { pageNum, pageSize: 1000, data, } });
        if (res.data) {
            exportExcel(header, res.data, filename.concat(".xlsx"))
            pageNum += 1
        }
    } while (res.data);
    return true
}
