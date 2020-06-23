cc.Class({
    extends: cc.Component,

    properties: {
        
    },

    onLoad() {
        cc.director.preloadScene('Opening');
    },

    start() {
        let scaleTo1 = cc.scaleTo(1, 0.8).easing(cc.easeCircleActionOut());
        let scaleTo2 = cc.scaleTo(0.8, 1).easing(cc.easeCircleActionIn());
        let sequence = cc.sequence(scaleTo1, scaleTo2);
        let repeat = cc.repeatForever(sequence);
        this.node.runAction(repeat);
        this.node.on(cc.Node.EventType.TOUCH_START, () => {
            cc.director.loadScene('Opening');
        });
    },
});
