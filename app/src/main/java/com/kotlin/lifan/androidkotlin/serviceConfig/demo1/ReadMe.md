开启和绑定服务 生命周期


start服务生命周期
01-02 03:22:03.022 6050-6050/com.lifan.servicedemo D/My1Service: onCreate() executed
01-02 03:22:03.022 6050-6050/com.lifan.servicedemo D/My1Service: onStartCommand() executed
01-02 03:22:05.619 6050-6050/com.lifan.servicedemo D/My1Service: onStartCommand() executed
01-02 03:22:07.169 6050-6050/com.lifan.servicedemo D/My1Service: onStartCommand() executed
01-02 03:22:07.719 6050-6050/com.lifan.servicedemo D/My1Service: onStartCommand() executed
01-02 03:22:11.702 6050-6050/com.lifan.servicedemo D/My1Service: onDestroy() executed

bind服务生命周期
01-02 05:51:22.905 17972-17972/com.lifan.servicedemo D/My12Service: onCreate() executed
01-02 05:51:22.905 17972-17972/com.lifan.servicedemo D/My12Service: onBind() executed
01-02 05:51:23.669 17972-17972/com.lifan.servicedemo D/My12Service: onDestroy() executed