package com.jcs.socket.socket.spi;

import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface Robot {

    void sayHello();
}
