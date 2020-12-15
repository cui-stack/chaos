export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

export function validUsername(str) {

  return true;

  const valid_map = ['admin', 'editor']
  return valid_map.indexOf(str.trim()) >= 0
}
