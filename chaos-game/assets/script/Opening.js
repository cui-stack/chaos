cc.Class({
    extends: cc.Component,

    properties: {
        videoPlayer: cc.VideoPlayer,
    },

    onLoad() {
        cc.director.preloadScene('Game');
    },

    start() {
        this.videoPlayer.play();
    },

    onVideoPlayerEvent(sender, event) {
        if (event === cc.VideoPlayer.EventType.COMPLETED) {
            cc.director.loadScene('Game');
        }
    },

});
