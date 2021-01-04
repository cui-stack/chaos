import { Dispatch } from 'umi';

export interface ModelType {
    namespace: string;
}

export interface PropsType {
    dispatch: Dispatch;
}

export interface ParamsType {

}

export interface PageParamsType extends ParamsType {
    pageSize?: number = 10;
    pageNum?: number = 1;
}


export interface ProTableParams {
    pageSize?: number;
    current?: number;
    keyword?: string;
}



