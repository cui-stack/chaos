import Data from './data'

export async function one(domain, data) {
  return Data.one(domain, data)
}

export async function add(domain, data) {
  return Data.add(domain, data)
}

export async function list(domain, data) {
  return Data.list(domain, data)
}

export async function page(domain, data, pageNum, pageSize) {
  return Data.page(domain, data, pageNum, pageSize)
}

export async function update(domain, mu, data) {
  return Data.update(domain, mu, data)
}

