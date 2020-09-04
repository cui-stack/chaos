export interface XyDataType {
  x: string;
  y: number;
}
export interface UserDataType {
  total:number;
  wyoy:number;
  dyoy:number;
  todayTotal:number;
}
export interface OrderDataType {
  total:number;
  wyoy:number;
  dyoy:number;
  todayTotal:number;
}

export interface PhoneDataType {
  total:number;
}

export interface AnalysisData {
  userData:UserDataType;
  phoneData:PhoneDataType
  registerDatas:XyDataType[]
  orderData:OrderDataType
}

