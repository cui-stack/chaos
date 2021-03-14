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
    current?: number = 1;
    data?: any = {};
}





