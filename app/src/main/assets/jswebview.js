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
        callNative: function(nativeName){
            if (!this.events[nativeName]){
                Base.call(arguments[0]);
                return;
            }
            this.triggerEvent(nativeName);
        },
        //nativeCallBack is the ONLY interface which android native code call js code.
        nativeCallBack: function(eventName){
            this.callbackArgs[eventName] = Array.prototype.slice.call(arguments,1,arguments.length);
            this.triggerEvent(eventName);
        }
    };
    window.jswebview = new jswebview();
    
    //Add js-sdk build in functions.
    window.jswebview.addEvent('test',function(){
        console.log('testEvent');
    });
    window.jswebview.addEvent('chooseImage',function(){
        Base.call('chooseImage',null);
    });
    window.jswebview.addEvent('onChooseImageDone',function(){
        console.log(window.jswebview.callbackArgs['onChooseImageDone']);
    });
    window.jswebview.addEvent('previewImage', function(){
        Base.call('previewImage', window.jswebview.callbackArgs['onChooseImageDone']);
    });
    window.jswebview.addEvent('closeWindow',function(){
        Base.call('closeWindow');
    });
})();