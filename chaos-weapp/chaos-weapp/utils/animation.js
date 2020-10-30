function init(ty) {
    let animation = wx.createAnimation({
        duration: 500,
        timingFunction: 'ease',
        delay: 200
    });
    animation.opacity(0.2).translate(ty, 0).step()
    return animation;
}

module.exports = {
    init
}
