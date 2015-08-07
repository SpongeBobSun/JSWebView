(function(){
    
    function jswebview(){
    };
    jswebview.prototype = {
        events: [],
        callbackArgs: [],
        triggerEvent: function(eventName){
            document.dispatchEvent(this.events[eventName]);
        },
        addEvent: function(eventName,callback){
            this.events[eventName] = document.createEvent('Event');
            this.events[eventName].initEvent(eventName, true, true);
            document.addEventListener(eventName,callback,false);
        },
        //callNative is the ONLY interface which js code call android native code.
        callNative: function(nativeName,arg){
            if (!this.events[nativeName]){
                Base.call(nativeName,arg);
                return;
            }
            this.triggerEvent(nativeName);
        },
        //nativeCallBack is the ONLY interface which android native code call js code.
        nativeCallBack: function(eventName, jsonArgs){
            this.callbackArgs[eventName] = jsonArgs;
            this.triggerEvent(eventName);
        }
    };
    window.jswebview = new jswebview();
    
    //Add js-sdk build in functions.
    window.jswebview.addEvent('test',function(){
        console.log('testEvent');
    });
    window.jswebview.addEvent('onChooseImageDone',function(){
        console.log(window.jswebview.callbackArgs['onChooseImageDone']);
    });
    window.jswebview.addEvent('previewImage', function(){
        Base.call('previewImage', window.jswebview.callbackArgs['onChooseImageDone']);
    });
})();