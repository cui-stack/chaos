import request from '@/utils/request';
import { ParamsType, PageParamsType } from '@/define';

export async function add(domain: string, data: ParamsType, cb?: Function) {
    const res = await request('/manage/'.concat(domain).concat('/add'), {
        method: 'POST',
        data,
    });
    if (cb) cb(res);

}

export async function update(domain: string, mu: string, data: ParamsType, cb?: Function) {
    const res = await request('/manage/'.concat(domain).concat('/update'), {
        method: 'POST',
        data: {
            mu,
            data
        },
    });
    if (cb) cb(res);
}

export async function remove(domain: string, mu: string, cb?: Function) {
    const res = await request('/manage/'.concat(domain).concat('/delete'), {
        method: 'POST',
        data: { mu },
    });
    if (cb) cb(res);
}

export async function page(domain: string, params: PageParamsType) {
    const { pageNum, pageSize, ...rest } = params;
    return request('/manage/'.concat(domain).concat('/page'), {
        method: 'POST',
        data: {
            pageNum, pageSize, data: { ...rest }
        },
    });
}

export async function list(domain: string, data: ParamsType, cb?: Function) {
    const res = await request('/manage/'.concat(domain).concat('/list'), {
        method: 'POST',
        data,
    });
    if (cb) cb(res);
}

export async function one(domain: string, mu: string, cb?: Function) {
    const res = await request('/manage/'.concat(domain).concat('/one'), {
        method: 'POST',
        data: { mu },
    });
    if (cb) cb(res);
}

export async function submit(method: string, data: ParamsType, cb?: Function) {
    const res = await request('/manage/'.concat(method), {
        method: 'POST',
        data
    });
    if (cb) cb(res);
}

export async function query(method: string, data: PageParamsType, cb?: Function) {
    const { pageNum, pageSize, ...rest } = data;
    const res = await request('/manage/'.concat(method), {
        method: 'POST',
        data: {
            pageNum, pageSize, data: { ...rest }
        },
    });
    if (cb) cb(res);
}