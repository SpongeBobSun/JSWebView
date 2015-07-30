(function(){
    function jswebview(){
    };
    jswebview.prototype = {
        images: [],
        chooseImage: function(){
            Base.call('chooseImage',null);
        },
        onChooseImageDone: function(imagePath){
            console.log(imagePath);
            images.put(imagePath);
        },
        jsCallBack: function(){
            console.log("Calling from Java side");
        }
    };
    window.jswebview = new jswebview();
})();