export function remove_repeat(a) { // 去重
  var r = [];
  for (var i = 0; i < a.length; i++) {
    var flag = true;
    var temp = a[i];
    for (var j = 0; j < r.length; j++) {
      if (temp === r[j]) {
        flag = false;
        break;
      }
    }
    if (flag) {
      r.push(temp);
    }
  }
  return r;
}

export function intersection(a, b) { // 交集
  var result = [];
  for (var i = 0; i < b.length; i++) {
    var temp = b[i];
    for (var j = 0; j < a.length; j++) {
      if (temp === a[j]) {
        result.push(temp);
        break;
      }
    }
  }
  return remove_repeat(result);
}

export function union(a, b) { // 并集
  return remove_repeat(a.concat(b));
}

export function difference(a, b) { // 差集 a - b
  //clone = a
  var clone = a.slice(0);
  for (var i = 0; i < b.length; i++) {
    var temp = b[i];
    for (var j = 0; j < clone.length; j++) {
      if (temp === clone[j]) {
        //remove clone[j]
        clone.splice(j, 1);
      }
    }
  }
  return remove_repeat(clone);
}

export default {
  remove_repeat,
  intersection,
  union,
  difference
}
