(function(){
    function jswebview(){
        this.chooseImage = function(){
            Base.call('chooseImage',null);
        };
    };
    window.jswebview = new jswebview();
})();