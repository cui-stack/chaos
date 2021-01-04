import request from '@/utils/request';
import { ParamsType, ProTableParams } from '@/define';


export async function add(domain: string, data: ParamsType) {
    const res = await request('/manage/'.concat(domain).concat('/add'), {
        method: 'POST',
        data,
    });
    return res;

}

export async function update(domain: string, mu: string, data: ParamsType) {
    const res = await request('/manage/'.concat(domain).concat('/update'), {
        method: 'POST',
        data: {
            mu,
            data
        },
    });
    return res;
}

export async function remove(domain: string, mu: string) {
    const res = await request('/manage/'.concat(domain).concat('/delete'), {
        method: 'POST',
        data: { mu },
    });
    return res;
}

export async function page(domain: string, params: ProTableParams) {
    const { current, pageSize, ...rest } = params;
    const res = await request('/manage/'.concat(domain).concat('/page'), {
        method: 'POST',
        data: {
            pageNum: current, pageSize, data: { ...rest }
        },
    });
    return res;

}

export async function list(domain: string, data: ParamsType) {
    const res = await request('/manage/'.concat(domain).concat('/list'), {
        method: 'POST',
        data,
    });
    return res;
}

export async function one(domain: string, mu: string) {
    const res = await request('/manage/'.concat(domain).concat('/one'), {
        method: 'POST',
        data: { mu },
    });
    return res;
}

export async function submit(method: string, data: ParamsType) {
    const res = await request('/manage/'.concat(method), {
        method: 'POST',
        data
    });
    return res;
}

export async function search(method: string, data: ProTableParams) {
    const { current, pageSize, ...rest } = data;
    const res = await request('/manage/'.concat(method), {
        method: 'POST',
        data: {
            pageNum: current, pageSize, data: { ...rest }
        },
    });
    return res;

}

export async function query(method: string, data: ParamsType) {
    const res = await request('/manage/'.concat(method), {
        method: 'POST',
        data
    });
    return res;
}