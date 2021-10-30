package com.jcs.socket.socket.designPattern.decorator;

public class Decorator extends Component{

    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }


}
