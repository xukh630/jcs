package com.jcs.socket.socket.designPattern.decorator;

public class ConcreteComponent extends Component {
    @Override
    public void operation() {
        System.out.println("ConcreteComponent say");
    }
}
