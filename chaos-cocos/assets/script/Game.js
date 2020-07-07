cc.Class({
    extends: cc.Component,

    properties: {
        prefab6: {
            default: null,
            type: cc.Prefab
        },
        prefab7: {
            default: null,
            type: cc.Prefab
        },
        prefab8: {
            default: null,
            type: cc.Prefab
        },
        x:4,
        y:3
        
    },

    onLoad () {
        for(var i=0;i<this.x;i++ ){
            for(var j=0;j<this.y;j++ ){
                var prefab;
                if((i+j)%3==0){
                    prefab = cc.instantiate(this.prefab6);
                }else if((i+j)%3==1){
                    prefab = cc.instantiate(this.prefab7);
                }else if((i+j)%3==2){
                    prefab = cc.instantiate(this.prefab8);
                }
                this.node.addChild(prefab);
                prefab.setPosition(this.getPosition(i,j));
                prefab.on('mousedown', function (event) {
                    cc.tween(event.target)
                    .to(1, { position: cc.v2(100, 100), rotation: 360 })
                    .to(1, { scale: 2 })
                    .start()
                });
            }
        }
        
    },

    start () {

    },

    update (dt) {},
    getPosition: function (x,y) {
        var randX = -300+x*200;
        var randY = -200+y*200;
        return cc.v2(randX, randY);
    },
});
