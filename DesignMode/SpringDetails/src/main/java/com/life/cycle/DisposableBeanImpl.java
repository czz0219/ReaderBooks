package com.life.cycle;

import org.springframework.beans.factory.DisposableBean;

public class DisposableBeanImpl implements DisposableBean {
    public void destroy() {
        System.out.println("调用接口DisposableBean的 Destroy方法销毁springIoc容器");
    }
}
