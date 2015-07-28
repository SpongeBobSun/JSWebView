(function(){
    function jswebview(){
        this.chooseImage = function(){
            Base.call('chooseImage',null);
        };

//        this.onChooseImageDone(imagePath){
//            images.put(imagePath);
//        };
    };
    window.jswebview = new jswebview();
})();