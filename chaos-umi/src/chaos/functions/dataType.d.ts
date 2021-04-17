
// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface ParamsType {

}

export interface PageParamsType extends ParamsType {
    pageSize?: number = 10;
    current?: number = 1;
    data?: any = {};
}





